package spaceshapes;

import java.awt.Color;

public class DynamicRectangleShape extends Shape {
	protected static final Color DEFAULT_COLOR = new Color(255, 255, 255);
	
	protected Color _colour;
	protected boolean _filled;

	public DynamicRectangleShape() {
		super();
		_colour = DEFAULT_COLOR;
	}

	public DynamicRectangleShape(int x, int y) {
		super(x, y);
		_colour = DEFAULT_COLOR;
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
		_colour = DEFAULT_COLOR;
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
		_colour = DEFAULT_COLOR;
	}

	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color colour) {
		super(x, y, deltaX, deltaY, width, height);
		_colour = colour;
	}
	
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color colour) {
		super(x, y, deltaX, deltaY, width, height, text);
		_colour = colour;
	}
	
	@Override
	protected void doPaint(Painter painter) {
		if (_filled) {
			Color previousColour = painter.getColor();
			painter.setColor(_colour);
			painter.fillRect(_x, _y, _width, _height);
			painter.setColor(previousColour);
		} else {
			painter.drawRect(_x,_y,_width,_height);
		}
	}
	
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		if (nextY <= 0) {
			_filled = false;
			nextY = 0;
			_deltaY = -_deltaY;
		} else if (nextY + _height >= height) {
			_filled = false;
			nextY = height - _height;
			_deltaY = -_deltaY;
		}
		
		if (nextX <= 0) {
			_filled = true;
			nextX = 0;
			_deltaX = -_deltaX;
		} else if (nextX + _width >= width) {
			_filled = true;
			nextX = width - _width;
			_deltaX = -_deltaX;
		}

		_x = nextX;
		_y = nextY;
	}

}
