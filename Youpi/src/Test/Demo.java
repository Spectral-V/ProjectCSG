package Test;

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

public class Demo extends JFrame{
	private static final long serialVersionUID = -2654866035346769096L;
	private DrawingPanel drawingArea;
	private JLabel Modelabel;
	private String path = "circles.ser";
	private ArrayList<Cercle> currentlist = new ArrayList<Cercle>();
	private int wX = 100;
	private int wY = 200;
	
    public Demo(String path) {
    this.setpath(path);
    deserializeCercles();
	setLocation(wX,wY);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(700,400);
    setTitle("Démo");
    this.drawingArea = new DrawingPanel(this);
    JMenuBar toolBar = new JMenuBar();
    JButton btn_0 = new JButton("Cercle");
	JButton btn = new JButton("Save");
	JButton btn_1 = new JButton("Desc");
	JButton btn_2 = new JButton("Move");
	JButton btn_3 = new JButton("Open");
	JButton btn_4 = new JButton("Save As");
	JButton btn_5 = new JButton("Supr");
	toolBar.add(btn_0);
	toolBar.add(btn);
	toolBar.add(btn_1);
	toolBar.add(btn_2);
	toolBar.add(btn_3);
	toolBar.add(btn_4);
	toolBar.add(btn_5);
	setJMenuBar(toolBar);
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
    public ArrayList<Cercle> getcurrentlist() {
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
            currentlist = (ArrayList<Cercle>) objectIn.readObject();
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
    	Demo newdemo = new Demo(path);
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
                objectOut.writeObject(drawingArea.getcs());
                objectOut.close();
                fileOut.close();
                System.out.println("La liste de cercles a été sérialisée avec succès.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void showCircleDescription(Cercle c) {
        Demo d = this;
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
    			Cercle ct = c;
    			ct.setCo(0);
    			ArrayList<Cercle> cs = d.drawingArea.getcs();
    			cs.remove(c);
    			cs.add(ct);
    			d.drawingArea.setcs(cs);
    			d.drawingArea.repaint();
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
    public void Delete(Cercle c) {
        int choice = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce cercle?", "Supprimer cercle", JOptionPane.OK_CANCEL_OPTION);
        
        if (choice == JOptionPane.OK_OPTION) {
			ArrayList<Cercle> cs = this.drawingArea.getcs();
			ArrayList<Cercle> cst = new ArrayList<Cercle>();
			for (Cercle ce:cs) {
				if (((ce.getX()==c.getX()) && (ce.getY()==c.getY()))&&(ce.getR()==c.getR())){
				System.out.println("Trouvé");
				}
				else {
					cst.add(ce);
				}
			}
			this.drawingArea.index -=1;
			this.drawingArea.setcs(cst);
			this.drawingArea.repaint();
			this.Modelabel.setText("Mode : Paint");
        }
    }
   
}


