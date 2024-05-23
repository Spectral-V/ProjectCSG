package forms;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CSGwindows extends JFrame{
	private static final long serialVersionUID = -2654866035346769096L;
	private DrawingPanel drawingArea; 
	private JLabel Modelabel; 
	private String path = "circles.ser"; //Save File
	private ArrayList<Shape> currentlist = new ArrayList<Shape>();
	
	//Location window
	private int wX = 100;
	private int wY = 200;
	
	//Switch between exit and dispose
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
    		deserialize();
    		setLocation(wX,wY);
    		windowCount ++;
    		if(windowCount == 1) {setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}
    		else {setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);}
    		setSize(850,400);
    		setTitle("Pasteque CSG");
    		this.drawingArea = new DrawingPanel(this);
    		
    		
    		JPanel filepanel = new JPanel();
    		JPanel paintpanel = new JPanel();
    		filepanel.setLayout(new BoxLayout(filepanel, BoxLayout.Y_AXIS));
    		JButton btn_P_Circle = new JButton("Cirlce");
    		JButton btn_P_Rect = new JButton("Rectangle");
    		JButton btn_P_Desc = new JButton("Description");
    		JButton btn_P_Move = new JButton("Move");
    		JButton btn_P_Inter = new JButton("Intersection");
    		JButton btn_P_Union = new JButton("Union");
    		JButton btn_P_Diff = new JButton("Difference");
    		JButton btn_P_Resize = new JButton("Resize");
    		
    		JButton btn_F_Save = new JButton("  Save   ");
    		JButton btn_F_Open = new JButton("  Open  ");
    		JButton btn_F_Sava = new JButton("Save As");
    		JButton btn_F_Delet = new JButton(" Delete ");
    		JButton btn_F_Clean = new JButton(" Clean  ");
    		
    		
    		paintpanel.add(btn_P_Rect);
    		paintpanel.add(btn_P_Circle);
    		paintpanel.add(btn_P_Desc);
    		paintpanel.add(btn_P_Move);
    		paintpanel.add(btn_P_Inter);
    		paintpanel.add(btn_P_Union);
    		paintpanel.add(btn_P_Diff);
    		paintpanel.add(btn_P_Resize);
    		
    		
    		
    		filepanel.add(btn_F_Save);
    		filepanel.add(btn_F_Open);
    		filepanel.add(btn_F_Sava);
    		filepanel.add(btn_F_Delet);
    		filepanel.add(btn_F_Clean);
    		
	

    		this.Modelabel = new JLabel("\t Mode: Paint");
    		this.Modelabel.setFont(new Font("SansSerif", Font.BOLD, 10));
    
	
    		add(paintpanel, BorderLayout.NORTH);
    		add(filepanel, BorderLayout.WEST);
    		add(Modelabel, BorderLayout.AFTER_LAST_LINE);
    		add(drawingArea, BorderLayout.CENTER);
    		filepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
    		paintpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
    		Modelabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
    		
    		
    		btn_P_Circle.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
            		Modelabel.setText("Mode: Paint");
            		drawingArea.setAllstate(0);
            		drawingArea.setstate(1);
        		}
    		});
    		
    		btn_P_Rect.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				Modelabel.setText("Mode: Paint");
    				drawingArea.setAllstate(0);
    			}
    		});
   
    		btn_F_Save.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
            		serialize();
    			}
    		});
    		
    		btn_P_Desc.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				Modelabel.setText("Mode: Select");
    				drawingArea.setstate(2);
    				drawingArea.setselectstate(0);
    			}
    		});
    		
    		btn_P_Move.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				Modelabel.setText("Mode: Move");
    				drawingArea.setAllstate(0);
    				drawingArea.setstate(3);
    			}
    		});
    		
    		btn_F_Open.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				deserializeout();
            
    			}
    		});
    		
    		btn_F_Sava.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				saveAs();
    			}
    		});
    		
    		btn_P_Inter.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				Modelabel.setText("Mode: Select");
    				drawingArea.setstate(2);
    				drawingArea.setselectstate(2);
    				drawingArea.setSelectedS(new ArrayList<>());
    			}
    		});
    		
    		btn_P_Union.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				Modelabel.setText("Mode: Select");
    				drawingArea.setstate(2);
            		drawingArea.setselectstate(3);
            		drawingArea.setSelectedS(new ArrayList<>());
    			}
    		});
    		
    		btn_P_Diff.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				Modelabel.setText("Mode: Select");
    				drawingArea.setstate(2);
            		drawingArea.setselectstate(4);
            		drawingArea.setSelectedS(new ArrayList<>());
    			}
    		});
    		
    		btn_P_Resize.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				Modelabel.setText("Mode: Resize");
    				drawingArea.setstate(4);
    			}
    		});
    		
    		btn_F_Delet.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				Modelabel.setText("Mode: Delete");
    				drawingArea.setstate(2);
    				drawingArea.setselectstate(1);
    			}
    		});
    		
    		btn_F_Clean.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				drawingArea.Clean();
    			}
    		});
    		addWindowListener(new WindowAdapter() {
    			@Override
    			public void windowClosing(WindowEvent e) {
    				// TODO Auto-generated method stub
    				checkWindowCount();
    				windowCount --;
    			}
    		});
  
    }
    
    //getter-setter
    public ArrayList<Shape> getcurrentlist() {
		return currentlist;
	};
	 
	public void setpath(String s) {
			this.path = s;
	};
	public String getpath() {
			return this.path;
	};
	
	//Save-Open
    private void serialize() {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.getpath());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(drawingArea.getshapes());
            objectOut.close();
            fileOut.close();
            System.out.println("FormList has been serialize with success.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
	private void deserialize() {
        try {
            FileInputStream fileIn = new FileInputStream(this.getpath());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            currentlist = (ArrayList<Shape>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("FormList has been deserialize with success.");
        } catch (IOException | ClassNotFoundException ex) {
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
                objectOut.writeObject(drawingArea.getshapes());
                objectOut.close();
                fileOut.close();
                System.out.println("La liste de formes a été sérialisée avec succès.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    //Select Operations
    public void showDescription(Shape s) {
        CSGwindows w = this;
        
    	JFrame descriptionFrame = new JFrame("Description of the form");
        descriptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        descriptionFrame.setSize(300, 200);

        JTextArea descriptionLabel = new JTextArea("Selected form : " + "\n +" + s.toString());
        descriptionLabel.setLineWrap(true);
        descriptionLabel.setWrapStyleWord(true);
        descriptionLabel.setEditable(false);

        descriptionFrame.add(descriptionLabel);
        descriptionFrame.setVisible(true);
        descriptionFrame.addWindowListener(new WindowAdapter() {

    			@Override
    			public void windowClosing(WindowEvent e) {
    				Shape stemp = s;
    				stemp.setCo(0);
    				ArrayList<Shape> shapes = w.drawingArea.getshapes();
    				shapes.remove(s);
    				shapes.add(stemp);
    				w.drawingArea.setshapes(shapes);
    				w.drawingArea.repaint();
    				w.drawingArea.setAllstate(0);
    				w.Modelabel.setText("Mode : Paint");
    			}
        });
    }
    
    public void Delete(Shape s) {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure ? This form will be deleted", "Delete", JOptionPane.OK_CANCEL_OPTION);
        
        if (choice == JOptionPane.OK_OPTION) {
			ArrayList<Shape> shapes = this.drawingArea.getshapes();
			shapes.remove(s);
			this.drawingArea.setIndex(this.drawingArea.getIndex() - 1);
			this.drawingArea.setshapes(shapes);
			this.drawingArea.repaint();
			this.Modelabel.setText("Mode : Paint");
        }
        else {
        	ArrayList<Shape> shapes = this.drawingArea.getshapes();
			shapes.remove(s);
			s.setCo(0);
			shapes.add(s);
			this.drawingArea.setshapes(shapes);
			this.drawingArea.repaint();
			this.Modelabel.setText("Mode : Paint");
        }
    }
    
	public void Intersection(Shape s1, Shape s2) {
		ArrayList<Shape> shapes = this.drawingArea.getshapes();
		if ((s1 instanceof Rectangle) && (s2 instanceof Rectangle)) {
			int xIntersection = Math.max(s1.getX(), s2.getX());
			int yIntersection = Math.max(s1.getY(), s2.getY());

			int x2Right = Math.min(s1.getX() + ((Rectangle) s1).getWidth(), s2.getX() + ((Rectangle) s2).getWidth());
			int y2Bottom = Math.min(s1.getY() + ((Rectangle) s1).getHeight(), s2.getY() + ((Rectangle) s2).getHeight());
			
			if ( (x2Right - xIntersection > 0) && (y2Bottom - yIntersection > 0)) {
				int intersectionWidth = Math.max(0, x2Right - xIntersection);
				int intersectionHeight = Math.max(0, y2Bottom - yIntersection);

				Rectangle intersectionRectangle = new Rectangle(xIntersection, yIntersection, intersectionWidth, intersectionHeight, 0);
				shapes.add(intersectionRectangle);
				shapes.remove(s1);
				shapes.remove(s2);
			
				this.drawingArea.setIndex(this.drawingArea.getIndex() - 1);
			}
			else {
				shapes.remove(s1);
				shapes.remove(s2);
				s1.setCo(0);
				s2.setCo(0);
				shapes.add(s1);
				shapes.add(s2);
				
			}
		}
		else {
			shapes.remove(s1);
			shapes.remove(s2);
			s1.setCo(0);
			s2.setCo(0);
			shapes.add(s1);
			shapes.add(s2);
			
		}
		this.drawingArea.setshapes(shapes);
		this.drawingArea.repaint();
		this.Modelabel.setText("Mode : Paint");
		
	}
	public void Union(Shape s1, Shape s2) {
		ArrayList<Shape> shapes = this.drawingArea.getshapes();
		Union u = new Union(s1,s2);
		
		shapes.remove(s1);
		shapes.remove(s2);
		shapes.add(u);
		
		this.drawingArea.setIndex(this.drawingArea.getIndex() - 1);
		this.drawingArea.setshapes(shapes);
		this.drawingArea.repaint();
		this.Modelabel.setText("Mode : Paint");
		
	}
	
	public void difference(Shape s1, Shape s2) {
		ArrayList<Shape> shapes = this.drawingArea.getshapes();
		if ((s1 instanceof Rectangle) && (s2 instanceof Rectangle)) {
			
		    int xIntersection = Math.max(s1.getX(), s2.getX());
			int yIntersection = Math.max(s1.getY(), s2.getY());

			int x2Right = Math.min(s1.getX() + ((Rectangle) s1).getWidth(), s2.getX() + ((Rectangle) s2).getWidth());
			int y2Bottom = Math.min(s1.getY() + ((Rectangle) s1).getHeight(), s2.getY() + ((Rectangle) s2).getHeight());
			
			if ( (x2Right - xIntersection > 0) && (y2Bottom - yIntersection > 0)) {
				 Union complement1 = ComplementaryRectangles(s1, s2);
				 Union complement2 = ComplementaryRectangles(s2, s1);

				 shapes.remove(s1);
				 shapes.remove(s2);
				 shapes.add(complement1);
				 shapes.add(complement2);
			}
			else {
				shapes.remove(s1);
				shapes.remove(s2);
				s1.setCo(0);
				s2.setCo(0);
				shapes.add(s1);
				shapes.add(s2);
			}
		}
		else {
			shapes.remove(s1);
			shapes.remove(s2);
			s1.setCo(0);
			s2.setCo(0);
			shapes.add(s1);
			shapes.add(s2);
		}
		this.drawingArea.setIndex(this.drawingArea.getIndex() + 1);
		this.drawingArea.setshapes(shapes);
		this.drawingArea.repaint();
		this.Modelabel.setText("Mode : Paint");
	}
	
	private Union ComplementaryRectangles(Shape s1, Shape s2) {
        Rectangle left = new Rectangle(s1.getX(), s1.getY(), s2.getX() - s1.getX(), s1.getHeight(),0);
        Rectangle right = new Rectangle(s2.getX() + s2.getWidth(), s1.getY(),s1.getX() + s1.getWidth() - (s2.getX() + s2.getWidth()), s1.getHeight(),0);
        Rectangle top = new Rectangle(s1.getX(), s1.getY(), s1.getWidth(), s2.getY() - s1.getY(),0);
        Rectangle bottom = new Rectangle(s1.getX(), s2.getY() + s2.getHeight(),s1.getWidth(), s1.getY() + s1.getHeight() - (s2.getY() + s2.getHeight()),0);
        
        Union u1 = new Union(left,top);
        Union u2 = new Union(right,bottom);
        Union u = new Union(u1,u2);
        
        return u;
        
    }
   
}


