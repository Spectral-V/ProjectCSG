package forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 770683246678913545L;
	
	//index
	private int index = -1;
	private int ci; //Selected index
	
	// Position of the mouse
	private int startX; 
	private int startY;
	
	
	private CSGwindows currentwindow; //Parent Window
	private ArrayList<Shape> shapes; //List of Shapes painted
	private ArrayList<Shape> SelectedS; // List of Shapes selected
	
	private int state = 0; // 0: Rectangle 1: Circle 2: Selected 3: Move 4: Resize
	private int selectstate = 0; // 0 : Description 1 : Delete 2:Intersection 3:Union 4:Difference
	
	public DrawingPanel(CSGwindows w) {
		 shapes = new ArrayList<>();
		 SelectedS = new ArrayList<>();
	     shapes = w.getcurrentlist();
	     this.setcurrentwindow(w);
	     setBorder(BorderFactory.createLineBorder(Color.black));
	     addMouseListener((MouseListener) new MouseAdapter() {
	            public void mousePressed(MouseEvent e) {
	                startX = e.getX();
	                startY = e.getY();
	                
	                if ((state ==0) | (state ==1)) {
	                	setIndex(getIndex() + 1);
	                }
	                
	                if (state == 3) {
	                	int i =-1;
						for (Shape s:shapes) {
							i++;
							if (s.containt(startX,startY )) {
								ci =i;
								move(s);
								}
							}
	                }
	                if (state == 4) {
	                	int i = -1;
	                	for (Shape s:shapes) {
	                		i++;
							if (s.containt(startX,startY )) {
								ci = i;
								resize(s);
								}
							}
	                	
	                }
	           
	            }
	            @SuppressWarnings("unused")
				public void mouseClicked(MouseEvent e) {
					if (state==2) {
						selectShape(e.getX(), e.getY());
					}
				}
	            
	        });

	        addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter() {
	            public void mouseDragged(MouseEvent e) {
	                
	                if (state == 1) {
						Circle c=new Circle(startX,startY,(int) Math.sqrt(Math.pow(e.getX() - startX, 2) + Math.pow(e.getY() - startY, 2)),0);
						ArrayList<Circle> cst = new ArrayList<Circle>();
						cst.add(c);
						shapes.addAll(cst);
						
						if (shapes.size()>getIndex()+1) {
							shapes.remove(getIndex());
						}
						
						repaint();
	                }
	                
	                if (state == 0) {
	                	int w = Math.abs(e.getX() - startX);
		               	int h = Math.abs(e.getY() - startY);
						Rectangle r=new Rectangle(startX,startY,w , h, 0);
						ArrayList<Rectangle> cst = new ArrayList<Rectangle>();
						cst.add(r);
						shapes.addAll(cst);
						
						if (shapes.size()>getIndex()+1) {
							shapes.remove(getIndex());
						}
							
						repaint();
		            }  
	            }
	            
	        });	
	}
	
	//Paint Operation
	public void paint (Graphics g) {
		Image offScreenBuffer = createImage(getWidth(), getHeight());
		Graphics offScreenGraphics = offScreenBuffer.getGraphics();
	    
		offScreenGraphics.setColor(getBackground());
		offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());
		
	    for (Shape c : shapes) {
	       	if (c.getCo() == 0) {
	       		offScreenGraphics.setColor(Color.BLACK); // Default color
	       		c.paint(offScreenGraphics);
	       	}
	        if (c.getCo() == 1){
	           	offScreenGraphics.setColor(Color.GREEN); // Select color
	           	c.paint(offScreenGraphics);
	        }
	        
	    }
	        
	    g.drawImage(offScreenBuffer, 0, 0, this);
	}
	
	//getter-setter
	public ArrayList<Shape> getshapes() {
		return shapes;
	}
	public void setshapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	public void setstate(int i) {
		this.state = i;
	}
	public void setAllstate(int i) {
		this.state = i;
		this.selectstate = i;
	}
	public int getstate() {
			return this.state;
	}
	public int getselectstate() {
		return selectstate;
	}
	public void setselectstate(int selectstate) {
		this.selectstate = selectstate;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setcurrentwindow(CSGwindows w) {
		this.currentwindow = w;
	};
	public CSGwindows getcurrentwindow() {
		return this.currentwindow;
	};
	public void setSelectedS(ArrayList<Shape> arrayList) {
		this.SelectedS = arrayList;
		
	}	
	
	
	private void selectShape(int x, int y) {
		List<Shape> copySs = new ArrayList<>(shapes);
		for (Shape s : copySs) {
			     if (s.containt(x, y)) {
			    	 if(selectstate == 0) {
			            	Shape st = s;
			            	shapes.remove(s);
			           		st.setCo(1);
			           		shapes.add(st);
			           		currentwindow.showDescription(s); 
			           		repaint();
			               
			           		break;
			    	 }
			    	 if(selectstate == 1) {
				    	 	Shape st = s;
				           	shapes.remove(s);
				           	st.setCo(1);
				           	shapes.add(st);
				           	repaint();
				           	currentwindow.Delete(s); 
				           	this.setstate(0);
				            this.setselectstate(0); 
				            
				           	break; 
				     }
			    	 if(selectstate == 2) {
			            	Shape st = s;
				           	shapes.remove(s);
				           	st.setCo(1);
				           	shapes.add(st);
				           	SelectedS.add(st);
				           	repaint();
				           	
				           	if (SelectedS.size() == 2) {
				           		currentwindow.Intersection(SelectedS.get(0),SelectedS.get(1)); 
				           		this.setstate(0);
				           		this.setselectstate(0); 
				           		
				           		break;
				           	}  
				           
				        }
			            if(selectstate == 3) {
			            	Shape st = s;
				           	shapes.remove(s);
				           	st.setCo(1);
				           	shapes.add(st);
				           	SelectedS.add(st);
				           	repaint();

				           	if (SelectedS.size() == 2) {
				           		currentwindow.Union(SelectedS.get(0),SelectedS.get(1)); 
				           		this.setstate(0);
				           		this.setselectstate(0); 
				           		
				           		break;
				           	}
  
				        }
			            if(selectstate == 4) {
			            	Shape st = s;
				            shapes.remove(s);
				          	st.setCo(1);
				           	shapes.add(st);
				           	SelectedS.add(st);
				            repaint();
				            
				            if (SelectedS.size() == 2) {			            
				            	currentwindow.difference(SelectedS.get(0),SelectedS.get(1)); 
				           		this.setstate(0);
				            	this.setselectstate(0); 
				            	
				           		break;
				           	}
				        }   
			        }
				}
			
			}
	
	public void Clean() {
		shapes.removeAll(shapes);
		this.setIndex(-1);
		repaint();
	}




    public void move(Shape s) {
    	 MouseMotionListener motionListener = new MouseMotionAdapter() {
	            public void mouseDragged(MouseEvent e) {
	            	ArrayList<Shape> shapestemp = new ArrayList<Shape>();
	            	s.setX(e.getX());
	            	s.setY(e.getY());
	            	shapestemp.add(s);
	            	shapes.addAll(shapestemp);
	                			
	            	if (shapes.size()>getIndex()+1) {
	            		shapes.remove(ci);
	            	}
	                			
	            	ci = getIndex();
	            	repaint();
	            }
	            
	            @SuppressWarnings("unused")
				public void mouseReleased(MouseEvent e) {
	   	            setAllstate(0);
	   	        }
	            
	     };
	     
    	 addMouseMotionListener(motionListener);
    	 
    	    addMouseListener(new MouseAdapter() {
    	        public void mouseReleased(MouseEvent e) {
    	            removeMouseMotionListener(motionListener);
    	        }
    	    });  	 
    }
    
    public void resize(Shape s) {
    	MouseMotionListener motionListener = new MouseMotionAdapter() {
    		public void mouseDragged(MouseEvent e) {
    			ArrayList<Shape> shapestemp = new ArrayList<Shape>();
	            s.resize(e.getX(),e.getY());
	            shapestemp.add(s);
	            shapes.addAll(shapestemp);
	            if (shapes.size()>getIndex()+1) {
	            	shapes.remove(ci);
	            }
	            
	            ci = getIndex();
	            repaint();
	        }
	            @SuppressWarnings("unused")
				public void mouseReleased(MouseEvent e) {
	   	            setAllstate(0);
	   	        }  
	        };
	        
   	 addMouseMotionListener(motionListener);
   	 
   	    addMouseListener(new MouseAdapter() {
   	        public void mouseReleased(MouseEvent e) {
   	            removeMouseMotionListener(motionListener);
   	        }
   	    });
   }
    

}