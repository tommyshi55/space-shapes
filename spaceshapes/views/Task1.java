package spaceshapes.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;

public class Task1 implements TreeModel{
	protected ShapeModel _adaptee;
	protected List<TreeModelListener> _listeners;
	
	public Task1(ShapeModel s) {
		_adaptee = s;
		_listeners = new ArrayList<TreeModelListener>();
	}

	@Override
	public Object getRoot() {
		return _adaptee.root();
	}

	@Override
	public Object getChild(Object parent, int index) {
		if (!(parent instanceof CarrierShape)) {
			return null;
		}
		CarrierShape parentShape = (CarrierShape) parent;
		try {
			return parentShape.shapeAt(index);
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof CarrierShape) {
			CarrierShape parentShape = (CarrierShape) parent;
			return parentShape.shapeCount();
		} else if (parent instanceof Shape) {
			return 0;
		}
		return 0;
	}

	@Override
	public boolean isLeaf(Object node) {
		if (node instanceof CarrierShape) {
			return false;
		} else if (node instanceof Shape) {
			return true;
		}
		return false;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		return;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if (parent == null || child == null) {
			return -1;
		}
		if (!(parent instanceof CarrierShape) || !(child instanceof Shape)) {
			return -1;
		}
		CarrierShape parentShape = (CarrierShape) parent;
		Shape childShape = (Shape) child;
		return parentShape.indexOf(childShape);
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		_listeners.add(l);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		_listeners.remove(l);
	}

}
