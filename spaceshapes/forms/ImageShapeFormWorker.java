package spaceshapes.forms;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import spaceshapes.CarrierShape;
import spaceshapes.ImageRectangleShape;
import spaceshapes.ShapeModel;
import spaceshapes.forms.util.Form;

public class ImageShapeFormWorker extends SwingWorker<ImageRectangleShape, Void> {
	private ShapeModel _model;
	private CarrierShape _parentOfNewShape;
	private Form _form;

	public ImageShapeFormWorker(ShapeModel s, CarrierShape parent, Form form) {
		_model = s;
		_parentOfNewShape = parent;
		_form = form;
	}

	@Override
	protected ImageRectangleShape doInBackground() throws Exception {
		// Read field values from the form.
		File imageFile = (File)_form.getFieldValue(File.class, ImageFormElement.IMAGE);
		int width = _form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
		int deltaX = _form.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
		int deltaY = _form.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);


		// Load the original image (ImageIO.read() is a blocking call).
		BufferedImage fullImage = null;
		try {
			fullImage = ImageIO.read(imageFile);
		} catch(IOException e) {
			System.out.println("Error loading image.");
		}

		int fullImageWidth = fullImage.getWidth();
		int fullImageHeight = fullImage.getHeight();

		BufferedImage scaledImage = fullImage;

		// Scale the image if necessary.
		if(fullImageWidth > width) {
			double scaleFactor = (double)width / (double)fullImageWidth;
			int height = (int)((double)fullImageHeight * scaleFactor);

			scaledImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); 
			Graphics2D g = scaledImage.createGraphics();

			// Method drawImage() scales an already loaded image. The 
			// ImageObserver argument is null because we don't need to monitor 
			// the scaling operation.
			g.drawImage(fullImage, 0, 0, width, height, null);
		}

		// Create the new Shape and return to the ED thread.
		ImageRectangleShape imageShape = new ImageRectangleShape(deltaX, deltaY, scaledImage);
		return imageShape;
	}

	public void done() {
		ImageRectangleShape imageShape = null;
		try {
			imageShape = get();
		} catch (InterruptedException e) {

		} catch (ExecutionException e) {

		}
		// add to the model.
		_model.add(imageShape, _parentOfNewShape);
	}

}
