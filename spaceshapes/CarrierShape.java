package spaceshapes;

import java.util.ArrayList;
import java.util.List;

public class CarrierShape extends Shape{

	protected List<Shape> _children = new ArrayList<Shape>();
	/**
	 * Create a CarrierShape object with default values for state.
	 */
	public CarrierShape() {
		super();
	}

	/**
	 * Creates a CarrierShape object with specified location values,
	 * default values for other state items.
	 */
	public CarrierShape(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Creates a CarrierShape with specified values for location, velocity
	 * and direction. Non-specified state items take on default values.
	 */
	public CarrierShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}
	
	/**
	 * Creates a CarrierShape with specified values for location, velocity
	 * direction, width and height
	 */
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}
	
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
	
	/**
	 * Moves a CarrierShape object (including its children) within the bounds
	 * specified by arguments width and height
	 */
	public void move(int width, int height) {
		super.move(width, height);
		for (Shape s : _children) {
			s.move(_width, _height);
		}
	}
	
	/**
	 * Paints a CarrierShape object by drawing a rectangle around the edge of
	 * its bounding box. The CarrierShape object's children are then painted.
	 */
	protected void doPaint(Painter painter) {
		painter.drawRect(_x, _y, _width, _height);
		painter.translate(_x, _y);
		for (Shape s : _children) {
			s.paint(painter);
		}
		painter.translate(-_x, -_y);
		
	}

	void add(Shape shape) throws IllegalArgumentException {
		if (_children.contains(shape) || shape.parent() != null) {
			throw new IllegalArgumentException();
		}
		if (_width < shape._width + shape._x || _height < shape._height + shape._y) {
			throw new IllegalArgumentException();
		}
		_children.add(shape);
		shape._parent = this;
	}
	
	void remove(Shape shape) {
		if (!_children.contains(shape)) {
			return;
		}
		_children.remove(shape);
		shape._parent = null;
	}
	
	public Shape shapeAt(int index) throws IndexOutOfBoundsException {
		return _children.get(index);
	}
	
	public int shapeCount() {
		return _children.size();
	}
	
	public int indexOf(Shape shape) {
		return _children.indexOf(shape);
	}
	
	public boolean contains(Shape shape) {
		return _children.contains(shape);
	}
}
