package Test;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JPanel;




public class DrawingPanel extends JPanel {
	int startX;
	int startY;
	 
	ArrayList<Cercle> cs;
	private int state = 0;
	private int state2 = 0;
	public DrawingPanel() {
		 cs = new ArrayList<>(); 
	     deserializeCercles();
	     addMouseListener((MouseListener) new MouseAdapter() {
	            public void mousePressed(MouseEvent e) {
	                startX = e.getX();
	                startY = e.getY();
	            }
	        });

	        addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter() {
	            public void mouseDragged(MouseEvent e) {
	                
	                
	                if (state2==0) {
						Cercle c=new Cercle(startX,startY,(int) Math.sqrt(Math.pow(e.getX() - startX, 2) + Math.pow(e.getY() - startY, 2)),0);
						cs.add(c);
						
						repaint();
						for (Cercle ce:cs) {
							if (((ce.x==c.x) && (ce.y==c.y))&&(ce.r<c.r)){
							cs.remove(ce);
							}
						
						}
						repaint();
						}
					if (state2==1) {
						for (Cercle c:cs) {
							if (c.containt(startX,startY )) {
								c.x = e.getX();
			                    c.y = e.getY();
								repaint();	
								
								
							}
						}
	                repaint();
					}
	            }
	        });
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				/*if (state==0) {
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
				*/
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
	        	
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	 public void paint (Graphics g) {

		// Create off-screen buffer
	        Image offScreenBuffer = createImage(getWidth(), getHeight());
	        Graphics offScreenGraphics = offScreenBuffer.getGraphics();
	        
	        // Clear the off-screen buffer
	        offScreenGraphics.setColor(getBackground());
	        offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());
	        
	        // Draw circles on the off-screen buffer
	        for (Cercle c : cs) {
	            c.paint(offScreenGraphics);
	        }
	        
	        // Render the off-screen buffer on the screen
	        g.drawImage(offScreenBuffer, 0, 0, this);
	    };
	   
	  public ArrayList<Cercle> getcs() {
		return cs;
		   };
		   public void setstate(int i) {
				this.state = i;
				   };
		public void setstate2(int i) {
				this.state2 = i;
				};
		public int getstate() {
					return this.state;
					   };
		public int getstate2() {
			return this.state2;
					};
	    	private void serializeCercles() {
	            try {
	                FileOutputStream fileOut = new FileOutputStream("circles.ser");
	                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	                objectOut.writeObject(cs);
	                objectOut.close();
	                fileOut.close();
	                System.out.println("La liste de cercles a été sérialisée avec succès.");
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	        private void deserializeCercles() {
	            try {
	                FileInputStream fileIn = new FileInputStream("circles.ser");
	                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	                cs = (ArrayList<Cercle>) objectIn.readObject();
	                objectIn.close();
	                fileIn.close();
	                System.out.println("La liste de cercles a été désérialisée avec succès.");
	            } catch (IOException | ClassNotFoundException ex) {
	                ex.printStackTrace();
	            }
	        }
}