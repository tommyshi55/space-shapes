package spaceshapes;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;


/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of classes Shape and RectangleShape.
 * 
 * @author Tommy Shi (Original Author - Ian Warren)
 * 
 */
public class TestShape {
	// Fixture object that is used by the tests.
	private MockPainter _painter;

	/**
	 * This method is called automatically by the JUnit test-runner immediately
	 * before each @Test method is executed. setUp() recreates the fixture so 
	 * that there no side effects from running individual tests.
	 */
	@Before
	public void setUp() {
		_painter = new MockPainter();
	}

	@Test
	public void testHexagonCreation() {
		// Draw diamond
		GemShape a = new GemShape(100, 100, 5, 5, 30, 30);
		a.paint(_painter);
		
		// Draw a normal polygon
		GemShape b = new GemShape(200, 200, 2, 2, 50, 50);
		b.paint(_painter);
		
		assertEquals("(line 100,115,115,100)(line 115,100,130,115)(line 130,115,115,130)"
				+ "(line 115,130,100,115)(line 200,225,220,200)(line 220,200,230,200)(line 230,200,250,225)"
				+ "(line 250,225,230,250)(line 230,250,220,250)(line 220,250,200,225)", _painter.toString());
	}
	
	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a
	 * Shape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.paint(_painter);
		shape.move(500, 500);
		shape.paint(_painter);
		
		OvalShape oval = new OvalShape(100, 100, 10, 10);
		oval.paint(_painter);
		oval.move(500, 500);
		oval.paint(_painter);
		
		// Diamond
		GemShape gem = new GemShape(70, 100, 10, 10, 30, 30);
		gem.paint(_painter);
		gem.move(500, 500);
		gem.paint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 112,35,25,35)(oval 100,100,25,35)(oval 110,110,25,35)"
				+ "(line 70,115,85,100)(line 85,100,100,115)(line 100,115,85,130)(line 85,130,70,115)"
				+ "(line 80,125,95,110)(line 95,110,110,125)(line 110,125,95,140)(line 95,140,80,125)", 
				_painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffRight() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		
		OvalShape oval = new OvalShape(100, 100, 12, 15);
		oval.paint(_painter);
		oval.move(135, 10000);
		oval.paint(_painter);
		oval.move(135, 10000);
		oval.paint(_painter);
		
		GemShape gem = new GemShape(100, 100, 12, 15, 20, 20);
		gem.paint(_painter);
		gem.move(135, 100000);
		gem.paint(_painter);
		gem.move(135, 100000);
		gem.paint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 110,35,25,35)"
				+ "(rectangle 98,50,25,35)(oval 100,100,25,35)(oval 110,115,25,35)(oval 98,130,25,35)"
				+ "(line 100,110,110,100)(line 110,100,120,110)(line 120,110,110,120)(line 110,120,100,110)"
				+ "(line 112,125,122,115)(line 122,115,132,125)(line 132,125,122,135)(line 122,135,112,125)"
				+ "(line 115,140,125,130)(line 125,130,135,140)(line 135,140,125,150)(line 125,150,115,140)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		RectangleShape shape = new RectangleShape(10, 20, -12, 15);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		
		OvalShape oval = new OvalShape(10, 100, -12, 15);
		oval.paint(_painter);
		oval.move(10000, 10000);
		oval.paint(_painter);
		oval.move(10000, 10000);
		oval.paint(_painter);
		
		GemShape gem = new GemShape(10, 100, -12, 15, 20, 20);
		gem.paint(_painter);
		gem.move(10000, 100000);
		gem.paint(_painter);
		gem.move(10000, 100000);
		gem.paint(_painter);
		
		assertEquals("(rectangle 10,20,25,35)(rectangle 0,35,25,35)"
				+ "(rectangle 12,50,25,35)(oval 10,100,25,35)(oval 0,115,25,35)(oval 12,130,25,35)"
				+ "(line 10,110,20,100)(line 20,100,30,110)(line 30,110,20,120)(line 20,120,10,110)"
				+ "(line 0,125,10,115)(line 10,115,20,125)(line 20,125,10,135)(line 10,135,0,125)"
				+ "(line 12,140,22,130)(line 22,130,32,140)(line 32,140,22,150)(line 22,150,12,140)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the bottom right corner and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndRight() {
		RectangleShape shape = new RectangleShape(10, 90, -12, 15);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		
		OvalShape oval = new OvalShape(10, 100, -12, 15);
		oval.paint(_painter);
		oval.move(125, 135);
		oval.paint(_painter);
		oval.move(125, 135);
		oval.paint(_painter);
		
		GemShape gem = new GemShape(10, 90, -12, 15);
		gem.paint(_painter);
		gem.move(125, 135);
		gem.paint(_painter);
		gem.move(125, 135);
		gem.paint(_painter);
		
		assertEquals("(rectangle 10,90,25,35)(rectangle 0,100,25,35)"
				+ "(rectangle 12,85,25,35)(oval 10,100,25,35)(oval 0,100,25,35)(oval 12,85,25,35)"
				+ "(line 10,107,22,90)(line 22,90,35,107)(line 35,107,22,125)(line 22,125,10,107)"
				+ "(line 0,117,12,100)(line 12,100,25,117)(line 25,117,12,135)(line 12,135,0,117)"
				+ "(line 12,102,24,85)(line 24,85,37,102)(line 37,102,24,120)(line 24,120,12,102)", _painter.toString());
	}
	
	@Test
	public void testDynamicShapeOffLeftAndTop() {
		DynamicRectangleShape shape = new DynamicRectangleShape(10, 20, -10, -10, 10, 10);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		
		assertEquals("(rectangle 10,20,10,10)(rectangle 0,10,10,10,java.awt.Color[r=255,g=255,b=255])"
				+ "(rectangle 10,0,10,10)", _painter.toString());
	}
	
	@Test
	public void testDynamicShapeOffRightAndTop() {
		DynamicRectangleShape shape = new DynamicRectangleShape(100, 20, 10, -10, 10, 10);
		shape.paint(_painter);
		shape.move(120, 10000);
		shape.paint(_painter);
		shape.move(120, 10000);
		shape.paint(_painter);
		
		assertEquals("(rectangle 100,20,10,10)(rectangle 110,10,10,10,java.awt.Color[r=255,g=255,b=255])"
				+ "(rectangle 100,0,10,10)", _painter.toString());
	}
	
	@Test
	public void testDynamicShapeOffBottomAndLeft() {
		DynamicRectangleShape shape = new DynamicRectangleShape(20, 100, -10, 10, 10, 10);
		shape.paint(_painter);
		shape.move(10000, 120);
		shape.paint(_painter);
		shape.move(10000, 120);
		shape.paint(_painter);
		
		assertEquals("(rectangle 20,100,10,10)(rectangle 10,110,10,10)"
				+ "(rectangle 0,100,10,10,java.awt.Color[r=255,g=255,b=255])", _painter.toString());
	}
	
	@Test
	public void testDynamicShapeOffBottomAndRight() {
		DynamicRectangleShape shape = new DynamicRectangleShape(90, 100, 10, 10, 10, 10);
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		shape.move(120, 120);
		shape.paint(_painter);
		
		assertEquals("(rectangle 90,100,10,10)(rectangle 100,110,10,10)"
				+ "(rectangle 110,100,10,10,java.awt.Color[r=255,g=255,b=255])", _painter.toString());
	}
	
	@Test
	public void testDynamicShapeBounceOffRight() {
		DynamicRectangleShape shape = new DynamicRectangleShape(100, 20, 12, 15, 20, 20, new Color(255, 0, 0));
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		
		assertEquals("(rectangle 100,20,20,20)(rectangle 112,35,20,20)"
				+ "(rectangle 115,50,20,20,java.awt.Color[r=255,g=0,b=0])", _painter.toString());
	}
	
	@Test
	public void testDynamicShapeBounceOffLeft() {
		DynamicRectangleShape shape = new DynamicRectangleShape(10, 20, -12, 15, 20, 20, new Color(255, 0, 0));
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		
		assertEquals("(rectangle 10,20,20,20)(rectangle 0,35,20,20,java.awt.Color[r=255,g=0,b=0])"
				+ "(rectangle 12,50,20,20,java.awt.Color[r=255,g=0,b=0])", _painter.toString());
	}
	
	@Test
	public void testDynamicShapeBounceOffTop() {
		DynamicRectangleShape shape = new DynamicRectangleShape(100, 10, 12, -15, 20, 20, new Color(255, 0, 0));
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		
		assertEquals("(rectangle 100,10,20,20)(rectangle 112,0,20,20)(rectangle 124,15,20,20)", _painter.toString());
	}
	
	@Test
	public void testDynamicShapeBounceOffBottom() {
		DynamicRectangleShape shape = new DynamicRectangleShape(100, 100, 12, 15, 20, 20, new Color(255, 0, 0));
		shape.paint(_painter);
		shape.move(10000, 135);
		shape.paint(_painter);
		shape.move(10000, 135);
		shape.paint(_painter);
		
		assertEquals("(rectangle 100,100,20,20)(rectangle 112,115,20,20)(rectangle 124,100,20,20)", _painter.toString());
	}
}
