package Bureau;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Demo extends JFrame {
	ArrayList<Cercle> cs =new ArrayList<Cercle>();
	private int state = 0;
    public static void main(String[] args) {
        Demo f = new Demo();
    }
    

    public Demo(){
        setLocation(100,200);
        setSize(300,400);
        setTitle("Démo");
        setExtendedState(this.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
       
        addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (state==0) {
					Cercle c=new Cercle(e.getX(),e.getY(),50,0);
					cs.add(c);
					repaint();
					}
				if (state==1) {
					for (Cercle c:cs) {
						if (c.containt(e.getX(),e.getY() )) {
							Cercle nc=new Cercle(c.x,c.y,c.r,(c.co+1)%2);
							cs.add(nc);
							repaint();	
						}
					}
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated me
				
	        		if (e.getKeyChar()=='q')System.exit(0);
	        		if (e.getKeyChar()=='s')state=(state+1)%2;
	        	
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}});
    
        this.addWindowListener(new WindowListener() {
        	
        	

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}});
    }
    public void paint (Graphics g) {
    	for (Cercle c:cs) {
    		
    		
    		c.paint(g);}
    	};
    
}
