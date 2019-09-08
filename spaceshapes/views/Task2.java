package spaceshapes.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelEvent;
import spaceshapes.ShapeModelEvent.EventType;
import spaceshapes.ShapeModelListener;

public class Task2 extends Task1 implements ShapeModelListener {

	public Task2(ShapeModel s) {
		super(s);
	}

	@Override
	public void update(ShapeModelEvent event) {
		// Unpack event
		EventType e = event.eventType();
		Shape operand = event.operand();
		CarrierShape parent = event.parent();

		// Generate path list
		List<Shape> pathList = new ArrayList<Shape>();
		if (parent != null) {
			pathList = parent.path();
		} else {
			pathList.add(operand);
		}
		Shape[] path = new Shape[pathList.size()];
		for (int i = 0; i < pathList.size(); i++) {
			path[i] = pathList.get(i);
		}
		
		// Generate index and children array
		int[] index = new int[1];
		Shape[] children = new Shape[1];
		children[0] = operand;
		index[0] = event.index();

		// Convert ShapeModelEvent to TreeModelEvent
		TreeModelEvent t = new TreeModelEvent(_adaptee, path, index, children);

		// Notify registered listeners
		for (TreeModelListener tml : _listeners) {
			if (e.equals(EventType.ShapeAdded)) {
				tml.treeNodesInserted(t);
			} else if (e.equals(EventType.ShapeRemoved)) {
				tml.treeNodesRemoved(t);
			}
		}
	}

}
