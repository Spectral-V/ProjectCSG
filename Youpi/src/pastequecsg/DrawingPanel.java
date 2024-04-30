package pastequecsg;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;




public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 770683246678913545L;
	int index = -1;
	int startX;
	int startY;
	private CSGwindows currentdemo;
	private ArrayList<Shape> cs;
	private ArrayList<Shape> SelectedS;
	private int state = 0;
	private int state2 = 0;
	private int supstate = 0;
	
	public DrawingPanel(CSGwindows d) {
		 cs = new ArrayList<>();
		 SelectedS = new ArrayList<>();
	     cs = d.getcurrentlist();
	     this.setcurrentdemo(d);
	     addMouseListener((MouseListener) new MouseAdapter() {
	            public void mousePressed(MouseEvent e) {
	                startX = e.getX();
	                startY = e.getY();
	                
	                if ((state ==0 && state2 ==0) | (state ==2 && state2 ==0)) {
	                	index += 1;
	                }
	           
	            }
	            
	        });

	        addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter() {
	            public void mouseDragged(MouseEvent e) {
	                
	                if (state == 0) {
	                if (state2==0) {
	                	
						Cercle c=new Cercle(startX,startY,(int) Math.sqrt(Math.pow(e.getX() - startX, 2) + Math.pow(e.getY() - startY, 2)),0);
						ArrayList<Cercle> cst = new ArrayList<Cercle>();
						cst.add(c);
						//System.out.println(cst.toString());
						repaint();
						for (Cercle ce:cst) {
							if (((ce.getX()==c.getX()) && (ce.getY()==c.getY()))&&(ce.getR()<c.getR())){
								System.out.println("Marche");
							cs.remove(ce);
							}
						}
						System.out.println("cs:");
						System.out.println(cs.toString());
						cs.addAll(cst);
						if (cs.size()>index+1) {
							cs.remove(index);
						}
						repaint();
						}
					if (state2==1) {
						for (Shape c:cs) {
							if (c.containt(startX,startY )) {
								c.setX(e.getX());
			                    c.setY(e.getY());
								repaint();	
							}
							System.out.println("cs:");
							System.out.println(cs.toString());
						}
	                repaint();
					}
	            }
	                if (state == 2) {
		                if (state2==0) {
		                	int h=0;
		                	int w = 0;
		                	if (e.getX() < startX) {
		                	    w = Math.abs(startX - e.getX());
		                	}
		                	else { w = Math.abs(e.getX() - startX);}
		                	if (e.getY() < startY) {
		                	    h = Math.abs(startY - e.getY());
		                	}
		                	else { h = Math.abs(e.getY() - startY);}
							Rectangle r=new Rectangle(startX,startY,w , h, 0);
							ArrayList<Rectangle> cst = new ArrayList<Rectangle>();
							cst.add(r);
							//System.out.println(cst.toString());
							repaint();
							for (Rectangle ce:cst) {
								if (((ce.getX()==r.getX()) && (ce.getY()==r.getY()))&&(ce.getWidth()<r.getWidth())&&(ce.getHeight()<r.getHeight())){
									System.out.println("Marche");
								cs.remove(ce);
								}
							}
							System.out.println("cs:");
							System.out.println(cs.toString());
							cs.addAll(cst);
							if (cs.size()>index+1) {
								cs.remove(index);
							}
							repaint();
							}
						if (state2==1) {
							for (Shape c:cs) {
								if (c.containt(startX,startY )) {
									c.setX(e.getX());
				                    c.setY(e.getY());
									repaint();	
								}
								System.out.println("cs:");
								System.out.println(cs.toString());
							}
		                repaint();
						}
		            }
	            }
	        });
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Click");
				if (state==1) {
					selectCircle(e.getX(), e.getY());

					
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
	        for (Shape c : cs) {
	        	if (c.getCo() == 0) {
	        		offScreenGraphics.setColor(Color.RED); // Couleur par défaut
	        		c.paint(offScreenGraphics);
	            }
	            if (c.getCo() == 1){
	            	offScreenGraphics.setColor(Color.GREEN); // Couleur de sélection
	            	c.paint(offScreenGraphics);
	            }
	        
	        }
	        
	        // Render the off-screen buffer on the screen
	        g.drawImage(offScreenBuffer, 0, 0, this);
	    };
	   
	  public ArrayList<Shape> getcs() {
		return cs;
	  };
	public void setcs(ArrayList<Shape> cs) {
			this.cs = cs;
		   };
	public void setstate(int i) {
			this.state = i;
				};
	public void setstate2(int i) {
			this.state2 = i;
				};
	public void setAllstate(int i) {
			this.state = i;
			this.state2 = i;
			this.supstate = i;
				};
	public int getstate() {
			return this.state;
				};
	public int getstate2() {
			return this.state2;
				}
	public int getSupstate() {
		return supstate;
	}
	public void setSupstate(int supstate) {
		this.supstate = supstate;
	}

	public void setcurrentdemo(CSGwindows d) {
			this.currentdemo = d;
			   };
	public CSGwindows getcurrentdemo() {
			return this.currentdemo;
	};
		
	private void selectCircle(int x, int y) {
		List<Shape> copyCs = new ArrayList<>(cs);
		for (Shape c : copyCs) {
			     if (c.containt(x, y)) {
			            	if(supstate == 0) {
			            		Shape ct = c;
			            		cs.remove(c);
			            		ct.setCo(1);
			            		cs.add(ct);
			            		currentdemo.showCircleDescription(c); 
			            		repaint();
			                
			            		break;
			            }
			            	if(supstate == 1) {
				    	 		Shape ct = c;
				            	cs.remove(c);
				            	ct.setCo(1);
				            	cs.add(ct);
				            	repaint();
				            	currentdemo.Delete(c); 
				            	this.setstate(0);
				                this.setSupstate(0); 
				            	break;
				            
				             
				           
				        }
			            	if(supstate == 2) {
			            		Shape ct = c;
				            	cs.remove(c);
				            	ct.setCo(1);
				            	cs.add(ct);
				            	SelectedS.add(ct);
				            	repaint();
				            	System.out.println("cst:");
			            		System.out.println(SelectedS.toString());
				            	if (SelectedS.size() == 2) {
				            	currentdemo.Intersection(SelectedS.get(0),SelectedS.get(1)); 
				            	this.setstate(0);
				                this.setSupstate(0); 
				            	break;}
				            
				             
				           
				        }
			        }
				}
			
			}
	public void Clean() {
		cs.removeAll(cs);
		System.out.println("cs:");
		System.out.println(cs.toString());
		this.index =-1;
		repaint();
		
		}



	public void setSelectedS(ArrayList<Shape> arrayList) {
		this.SelectedS = arrayList;
		
	}
        
}