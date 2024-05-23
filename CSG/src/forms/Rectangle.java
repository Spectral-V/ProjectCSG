package forms;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends Shape {
	private static final long serialVersionUID = -692201574334875499L;
	
	public void paint(Graphics g){
    	
    	
    	Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        
    	g2.drawRect(x, y, width, height);
    	paintComponent(g2);
	}
	
	public void paintComponent(Graphics g){
	
		g.fillRect(x, y, width, height);
	}
    	
    	
    public Rectangle (int x,int y,int width,int height, int co) {
    	this.setX(x);
    	this.setY(y);
    	this.setWidth(width);
    	this.setHeight(height);
    	this.setCo(co);
    	
    }
    

	public boolean containt (int a, int b) {
    	return((a <= x + width && a >= x) && (b <= y + height && b >= y) );
    }
    public String toString () {
    	return("Rectangle : x: " + this.getX() + " y: " + this.getY() + " w: " + this.getWidth() + " h: " + this.getHeight() + " ;");
    }	
	
}
