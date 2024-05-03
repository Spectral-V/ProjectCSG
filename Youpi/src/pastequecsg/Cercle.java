package pastequecsg;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Cercle extends Shape {
	private static final long serialVersionUID = 1L;
	
	public void paint(Graphics g){
    	
    	
    	Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        
    	g2.drawOval( x-(width/2), y-(height/2), width, height);
    	paintComponent(g2);
		};
	
		public void paintComponent(Graphics g){
	
			g.fillOval(x-(width/2), y-(height/2),width,height);}
    	
    	
    public Cercle (int x,int y,int r, int co) {
    	this.setX(x);
    	this.setY(y);
    	this.setWidth(r);
    	this.setHeight(r);
    	this.setCo(co);
    	
    }
    public boolean containt (int a, int b) {
    	return((a - x)*(a-x)+(b - y)*(b-y) < width*height);
    }
    public String toString () {
    	return("Cercle : x: " + this.x + " y: " + this.y + "r: " + this.width + ";");
    }

	public int getCo() {
		return co;
	}

	public void setCo(int co) {
		this.co = co;
	}
	
}
