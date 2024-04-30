package pastequecsg;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CSGwindows extends JFrame{
	private static final long serialVersionUID = -2654866035346769096L;
	private DrawingPanel drawingArea;
	private JLabel Modelabel;
	private String path = "circles.ser";
	private ArrayList<Shape> currentlist = new ArrayList<Shape>();
	private int wX = 100;
	private int wY = 200;
	private static int windowCount = 0;
	private static void checkWindowCount() {
	    if (windowCount == 1) {
	        for (Frame frame : Frame.getFrames()) {
	            if (frame instanceof JFrame) {
	                ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            }
	        }
	    }
	}
    public CSGwindows(String path) {
    this.setpath(path);
    deserializeCercles();
	setLocation(wX,wY);
    windowCount ++;
	if(windowCount == 1) {setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}
	else {setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}
	setSize(700,400);
    setTitle("Pasteque CSG");
    this.drawingArea = new DrawingPanel(this);
    JPanel toolBar = new JPanel();
    toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.Y_AXIS));
    JPanel filepanel = new JPanel();
    JPanel paintpanel = new JPanel();
    JButton btn_0 = new JButton("Cercle");
	JButton btn = new JButton("Save");
	JButton btn_1 = new JButton("Desc");
	JButton btn_2 = new JButton("Move");
	JButton btn_3 = new JButton("Open");
	JButton btn_4 = new JButton("Save As");
	JButton btn_5 = new JButton("Supr");
	JButton btn_6 = new JButton("Clean");
	JButton btn_7 = new JButton("Rect");
	JButton btn_8 = new JButton("Inter");
	paintpanel.add(btn_0);
	paintpanel.add(btn_7);
	filepanel.add(btn);
	paintpanel.add(btn_1);
	paintpanel.add(btn_2);
	filepanel.add(btn_3);
	paintpanel.add(btn_8);
	filepanel.add(btn_4);
	paintpanel.add(btn_5);
	paintpanel.add(btn_6);
	toolBar.add(filepanel,BorderLayout.LINE_START);
	toolBar.add(paintpanel,BorderLayout.LINE_END);
	

	this.Modelabel = new JLabel("Mode: Paint");
	this.Modelabel.setFont(new Font("SansSerif", Font.BOLD, 10));
    
	drawingArea.setBorder(BorderFactory.createLineBorder(Color.black));
	add(toolBar, BorderLayout.NORTH);
	add(Modelabel, BorderLayout.AFTER_LAST_LINE);
	add(drawingArea, BorderLayout.CENTER);
	
	btn_0.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Paint");
            Modelabel.setText("Mode: Paint");
            drawingArea.setAllstate(0);
        }
    });
	btn_7.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Paint");
            Modelabel.setText("Mode: Paint");
            drawingArea.setAllstate(0);
            drawingArea.setstate(2);
        }
    });
   
	btn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("save");
            serializeCercles();
        }
    });
	btn_1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Select");
            Modelabel.setText("Mode: Select");
            drawingArea.setstate((drawingArea.getstate()+1)%2);
        }
    });
	btn_2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Move");
            Modelabel.setText("Mode: Move");
            drawingArea.setstate2((drawingArea.getstate2()+1)%2);
        }
    });
	btn_3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("open");
            deserializeout();
            
        }
    });
	btn_4.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("saveas");
            saveAs();
            
        }
    });
	btn_8.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Inter");
            Modelabel.setText("Mode: Select");
            drawingArea.setstate(1);
            drawingArea.setSupstate(2);
            drawingArea.setSelectedS(new ArrayList<>());
            
        }
    });
	btn_5.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	System.out.println("Delete");
        	Modelabel.setText("Mode: Delete");
        	drawingArea.setstate((drawingArea.getstate()+1)%2);
            drawingArea.setSupstate((drawingArea.getSupstate()+1)%2);
            if (drawingArea.getstate2()==1) {
            	drawingArea.setstate2((drawingArea.getstate2()+1)%2);
            }
            
        }
    });
	btn_6.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clean");
            drawingArea.Clean();
            
        }
    });
    this.addKeyListener(new KeyListener(){

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated me
			if (e.getKeyChar() == 's') { 
				System.out.println("s");
			}
			System.out.println(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyChar()=='q') { System.exit(0);}
			if (e.getKeyChar()=='s') { 
				System.out.println("s");
			}
			System.out.println(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyChar()=='s') { 
				System.out.println("s");
			}
		}});
    addWindowListener(new WindowListener() {
    	
    	

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			checkWindowCount();
			windowCount --;
		}

		@Override
		public void windowClosed(WindowEvent e) {
			
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
			
		}
	});

    
    }
    public ArrayList<Shape> getcurrentlist() {
		return currentlist;
	  };
	 
	public void setpath(String s) {
			this.path = s;
			};
	public String getpath() {
			return this.path;
			};
    private void serializeCercles() {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.getpath());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(drawingArea.getcs());
            objectOut.close();
            fileOut.close();
            System.out.println("La liste de cercles a été sérialisée avec succès.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
	private void deserializeCercles() {
        try {
            FileInputStream fileIn = new FileInputStream(this.getpath());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            currentlist = (ArrayList<Shape>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("La liste de cercles a été désérialisée avec succès.");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    private void deserializeout() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Deserialization File");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(200, 100);

            JButton chooseFileButton = new JButton("Choose File");
            chooseFileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                       
                        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                     
                        frame.dispose();
                        
                            

                        openNewWindow(selectedFile.getAbsolutePath()); 
                        
                        
                    }
                }
            });

            frame.add(chooseFileButton, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
    private void openNewWindow(String path) {
        wX = wX + 30;
        wY = wY + 30;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	CSGwindows newdemo = new CSGwindows(path);
    	newdemo.setLocation(wX,wY);
        newdemo.setVisible(true);
        System.out.println(windowCount);
    }
    private void saveAs() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files (*.ser)", "ser"); 
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();             
            
            try {
                FileOutputStream fileOut = new FileOutputStream(filePath);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(drawingArea.getcs());
                objectOut.close();
                fileOut.close();
                System.out.println("La liste de cercles a été sérialisée avec succès.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void showCircleDescription(Shape c) {
        CSGwindows d = this;
    	JFrame descriptionFrame = new JFrame("Description du Cercle");
        descriptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        descriptionFrame.setSize(300, 200);

        JLabel descriptionLabel = new JLabel("Cercle sélectionné : " + c.toString());
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        descriptionFrame.add(descriptionLabel);
        descriptionFrame.setVisible(true);
        descriptionFrame.addWindowListener(new WindowListener() {
   
    		@Override
    		public void windowOpened(WindowEvent e) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void windowClosing(WindowEvent e) {
    			Shape ct = c;
    			ct.setCo(0);
    			ArrayList<Shape> cs = d.drawingArea.getcs();
    			cs.remove(c);
    			cs.add(ct);
    			d.drawingArea.setcs(cs);
    			d.drawingArea.repaint();
    			d.drawingArea.setAllstate(0);
    			d.Modelabel.setText("Mode : Paint");
    		}

    		@Override
    		public void windowClosed(WindowEvent e) {
    			
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
    			
    		}
    	});
    }
    public void Delete(Shape c) {
        int choice = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce cercle?", "Supprimer cercle", JOptionPane.OK_CANCEL_OPTION);
        
        if (choice == JOptionPane.OK_OPTION) {
			ArrayList<Shape> cs = this.drawingArea.getcs();
			ArrayList<Shape> cst = new ArrayList<>();
			for (Shape ce:cs) {
				if(ce instanceof Cercle) {
				if (((ce.getX()==c.getX()) && (ce.getY()==c.getY()))&&(((Cercle) ce).getR()==((Cercle) c).getR())){
				System.out.println("Trouvé");
				}
				else {
					cst.add(ce);
				}
				}
				if(ce instanceof Rectangle) {
				if (((ce.getX()==c.getX()) && (ce.getY()==c.getY()))&&(((Rectangle) ce).getWidth()==((Rectangle) c).getWidth())){
				System.out.println("Trouvé");
				}
				else {
					cst.add(ce);
				}
				}
			}
			this.drawingArea.index -=1;
			this.drawingArea.setcs(cst);
			this.drawingArea.repaint();
			this.Modelabel.setText("Mode : Paint");
        }
    }
	public void Intersection(Shape shape, Shape shape2) {
		ArrayList<Shape> cs = this.drawingArea.getcs();
		int xIntersection = Math.max(shape.getX(), shape2.getX());
		int yIntersection = Math.max(shape.getY(), shape2.getY());

		int x2Right = Math.min(shape.getX() + ((Rectangle) shape).getWidth(), shape2.getX() + ((Rectangle) shape2).getWidth());
		int y2Bottom = Math.min(shape.getY() + ((Rectangle) shape).getHeight(), shape2.getY() + ((Rectangle) shape2).getHeight());
		if ( (x2Right - xIntersection > 0) && (y2Bottom - yIntersection > 0)) {
		int intersectionWidth = Math.max(0, x2Right - xIntersection);
		int intersectionHeight = Math.max(0, y2Bottom - yIntersection);

		Rectangle intersectionRectangle = new Rectangle(xIntersection, yIntersection, intersectionWidth, intersectionHeight, 0);
		cs.add(intersectionRectangle);
		cs.remove(shape);
		cs.remove(shape2);
		
		this.drawingArea.index -=1;
		this.drawingArea.setcs(cs);
		}
		else {
			cs.remove(shape);
			cs.remove(shape2);
			shape.setCo(0);
			shape2.setCo(0);
			cs.add(shape);
			cs.add(shape2);
			this.drawingArea.setcs(cs);
		}
		this.drawingArea.repaint();
		this.Modelabel.setText("Mode : Paint");
		
	}
   
}


