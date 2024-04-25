package Test;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Cercle implements Serializable {
	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	public int r;
	private int co=0;
	
	public void paint(Graphics g){
    	
    	
    	Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        
    	g2.drawOval( x-(r/2), y-(r/2), r, r);
    	paintComponent(g2);
		};
	
		public void paintComponent(Graphics g){
	
			g.fillOval(x-(r/2), y-(r/2),r,r);}
    	
    	
    public Cercle (int x,int y,int r, int co) {
    	this.x=x;
    	this.y=y;
    	this.r=r;
    	this.setCo(co);
    	
    }
    public boolean containt (int a, int b) {
    	return((a - x)*(a-x)+(b - y)*(b-y) < r*r);
    }
    public String toString () {
    	return("Cercle : x: " + this.x + " y: " + this.y + "r: " + this.r + ";");
    }

	public int getCo() {
		return co;
	}

	public void setCo(int co) {
		this.co = co;
	}
}
