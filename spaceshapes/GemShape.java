package spaceshapes;

public class GemShape extends Shape {

	public GemShape() {
		super();
	}

	public GemShape(int x, int y) {
		super(x, y);
	}

	public GemShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}

	public GemShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}

	public GemShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
	
	@Override
	protected void doPaint(Painter painter) {
		if (_width < 40) {
			painter.drawLine(_x, _y + _height / 2, _x + _width / 2, _y);
			painter.drawLine(_x + _width / 2, _y, _x + _width, _y + _height / 2);
			painter.drawLine(_x + _width, _y + _height / 2, _x + _width / 2, _y + _height);
			painter.drawLine(_x + _width / 2, _y + _height, _x, _y + _height / 2);
		} else {
			painter.drawLine(_x, _y + _height / 2, _x + 20, _y);
			painter.drawLine(_x + 20, _y, _x + _width - 20, _y);
			painter.drawLine(_x + _width - 20, _y, _x + _width, _y + _height / 2);
			painter.drawLine(_x + _width, _y + _height / 2, _x + _width - 20, _y + _height);
			painter.drawLine(_x + _width - 20, _y + _height, _x + 20, _y + _height);
			painter.drawLine(_x + 20, _y + _height, _x, _y + _height / 2);

		}
	}

}
