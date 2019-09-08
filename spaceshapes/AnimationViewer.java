package spaceshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * Simple GUI program to show an animation of shapes in a confined space. Class AnimationViewer is
 * a special kind of GUI component (JPanel), and as such an instance of 
 * AnimationViewer can be added to a JFrame object. A JFrame object is a 
 * window that can be closed, minimised, and maximised. The state of an
 * AnimationViewer object comprises a list of Shapes and a Timer object. An
 * AnimationViewer instance subscribes to events that are published by a Timer.
 * In response to receiving an event from the Timer, the AnimationViewer iterates 
 * through a list of Shapes requesting that each Shape paints and moves itself.
 * 
 * @author Tommy Shi
 * 
 */
@SuppressWarnings("serial")
public class AnimationViewer extends JPanel implements ActionListener {
	// Frequency in milliseconds for the Timer to generate events.
	private static final int DELAY = 20;

	// Collection of Shapes to animate.
	private List<Shape> _shapes;

	private Timer _timer = new Timer(DELAY, this);

	/**
	 * Creates an AnimationViewer instance with a list of Shape objects and 
	 * starts the animation.
	 */
	public AnimationViewer() {
		this.setBackground(Color.BLACK);
		_shapes = new ArrayList<Shape>();
	
		// Populate the list of Shapes.
		_shapes.add(new RectangleShape(0, 0, 2, 3));
		_shapes.add(new GemShape(10, 10, 4, 3, 80, 50, "Assignment3"));
		_shapes.add(new OvalShape(20,20,3, 5));
		_shapes.add(new GemShape(100,100,3, 5));
		_shapes.add(new GemShape(70,100,3, 3, 70, 50));
		_shapes.add(new DynamicRectangleShape(300,300,-3, 1, 50, 50, new Color(0, 255, 0)));

		CarrierShape topLevelNest = new CarrierShape(0, 0, 2, 2, 150, 150);
		CarrierShape midLevelNest = new CarrierShape(0, 0, 2, 2, 50, 50);
		CarrierShape bottomLevelNest = new CarrierShape(5, 5, 2, 2, 20, 20);
		DynamicRectangleShape simpleShape = new DynamicRectangleShape(1, 1, 1, 2, 20, 20, new Color(255, 0, 0));
		
		midLevelNest.add(bottomLevelNest);
		midLevelNest.add(simpleShape);
		topLevelNest.add(midLevelNest);
		_shapes.add(topLevelNest);
		
		_shapes.add(new RectangleShape(250, 300, 2, 4, 40, 50, "Hello, world"));
		_shapes.add(new OvalShape(250, 250, 2, 4, 20, 50, "SOFTENG251"));
		_shapes.add(new DynamicRectangleShape(400, 250, 4, 3, 60, 60, "SOFTENG251", new Color(0, 0, 255)));
		
		// Start the animation.
		_timer.start();
	}

	/**
	 * Called by the Swing framework whenever this AnimationViewer object
	 * should be repainted. This can happen, for example, after an explicit 
	 * repaint() call or after the window that contains this AnimationViewer 
	 * object has been opened, exposed or moved.
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);
		
		// Calculate bounds of animation screen area.
		int width = getSize().width;
		int height = getSize().height;
		
		// Create a GraphicsPainter that Shape objects will use for drawing.
		// The GraphicsPainter delegates painting to a basic Graphics object.
		Painter painter = new GraphicsPainter(g);
		
		// Progress the animation.
		for(Shape s : _shapes) {
			s.paint(painter);
			s.move(width, height);
		}
	}

	/**
	 * Notifies this AnimationViewer object of an ActionEvent. ActionEvents are
	 * received by the Timer.
	 */
	public void actionPerformed(ActionEvent e) {
		// Request that the AnimationViewer repaints itself. The call to 
		// repaint() will cause the AnimationViewer's paintComponent() method 
		// to be called.
		repaint();
	}
	
	
	/**
	 * Main program method to create an AnimationViewer object and display this
	 * within a JFrame window.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Animation viewer");
				frame.add(new AnimationViewer());
		
				// Set window properties.
				frame.setSize(500, 500);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}
