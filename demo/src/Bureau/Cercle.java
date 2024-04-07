package Bureau;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Cercle {
	public int x;
	public int y;
	public int r;
	public  int co=0;
	
	public void paint(Graphics g){
    	
    	
    	Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        if (co==1) {
			g2.setColor(Color.green);}
		if (co==0) {
			g2.setColor(Color.red);}
    	g2.drawOval( x-(r/2), y-(r/2), r, r);
    	};
    	
    	
    public Cercle (int x,int y,int r, int co) {
    	this.x=x;
    	this.y=y;
    	this.r=r;
    	this.co=co;
    	
    }
    public boolean containt (int a, int b) {
    	return((a - x)*(a-x)+(b - y)*(b-y) < r*r);
    }
}
