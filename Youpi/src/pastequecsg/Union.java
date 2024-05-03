package pastequecsg;

import java.awt.Graphics;
import java.util.ArrayList;

public class Union extends Shape {
	private static final long serialVersionUID = -7053607443061757604L;
	private ArrayList<Shape> component = new ArrayList<>();
	public Union (Shape shape1, Shape shape2) {
		int xUnion = Math.min(shape1.getX(), shape2.getX());
		int yUnion = Math.min(shape1.getY(), shape2.getY());
		int x2Right = Math.max(shape1.getX() + shape1.getWidth(), shape2.getX() + shape2.getWidth());
		int y2Bottom = Math.max(shape1.getY() + shape1.getHeight(), shape2.getY() + shape2.getHeight());
		int unionWidth = Math.max(0, x2Right - xUnion);
		int unionHeight = Math.max(0, y2Bottom - yUnion);
		this.setX(xUnion);
		this.setY(yUnion);
    		this.setWidth(unionWidth);
    		this.setHeight(unionHeight);
    		ArrayList<Shape> comps = new ArrayList<>();
    		comps.add(shape1);
    		comps.add(shape2);
    		this.setcomponent(comps);
    }
	public void paint(Graphics g){
		for (Shape s:this.component) {
			s.paint(g);
		}
	}
	
	public void setcomponent(ArrayList<Shape> comps) {
		this.component = comps;
	}

	public ArrayList<Shape> getComponent() {
		return component;
	}
	public String toString () {
	    	String s = "Union : [ ";
	    	for (Shape c:this.component) {
				s += "\n" + c.toString();
			}
		return(s + "]");
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		
		for (Shape c:this.component) {
			if(c.x != this.x) {
				int diff = c.x - this.x;
				c.setX(x+diff);
			}
			else c.setX(x);
		}
		this.x = x;
	}
	public int getY() {
		return y;
	}

	public void setY(int y) {
		
		for (Shape c:this.component) {
			if(c.y != this.y) {
				int diff = c.y - this.y;
				c.setY(y+diff);
			}
			else c.setX(y);
		}
		this.y = y;
	}
}
