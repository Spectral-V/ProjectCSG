package forms;

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
    	
    	if(shape1 instanceof Union) {
    		for (Shape c:((Union) shape1).getComponent()) {
   				comps.add(c);
   			}
   		}
    	else comps.add(shape1);
    	
    	if(shape2 instanceof Union) {
    		for (Shape c:((Union) shape2).getComponent()) {
    			comps.add(c);
    		}
   		}
    	else comps.add(shape2);
    	
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
			s += "\n    +" + c.toString();
	   	}
	   	
		return(s + "\n  ]");
	}

	public void setX(int x) {
		
		for (Shape c:this.component) {
			if(c.getX() != this.getX()) {
				int diff = c.getX() - this.getX();
				c.setX(x+diff);
			}
			else c.setX(x);
		}
		this.x=x;
	}

	public void setY(int y) {
		
		for (Shape c:this.component) {
			if(c.getY() != this.getY()) {
				int diff = c.getY() - this.getY();
				c.setY(y+diff);
			}
			else c.setY(y);
		}
		this.y=y;
	}
	public void resize(int dx, int dy) {
		for (Shape c:this.component) {
				int diffwidth = c.getWidth() - this.getWidth();
				int diffheight = c.getHeight() - this.getHeight();
				c.resize(dx + diffwidth, dy + diffheight);
		}
		this.setWidth(dx);
		this.setHeight(dy);
	}
	
	public boolean containt (int a, int b) {
		return((a <= x + width && a >= x) && (b <= y + height && b >= y) );
    }

}
