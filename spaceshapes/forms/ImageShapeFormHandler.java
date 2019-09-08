package spaceshapes.forms;

import spaceshapes.CarrierShape;
import spaceshapes.ShapeModel;
import spaceshapes.forms.util.Form;
import spaceshapes.forms.util.FormHandler;

public class ImageShapeFormHandler implements FormHandler {
	private ShapeModel _model;
	private CarrierShape _parentOfNewShape;

	public ImageShapeFormHandler(ShapeModel s, CarrierShape parent) {
		_model = s;
		_parentOfNewShape = parent;
	}

	@Override
	public void processForm(Form form) {
		long startTime = System.currentTimeMillis();
		ImageShapeFormWorker worker = new ImageShapeFormWorker(_model, _parentOfNewShape, form);
		worker.execute();
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Image loading and scaling took " + elapsedTime + "ms.");
	}
}
