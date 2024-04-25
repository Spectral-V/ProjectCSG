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

public class Demo extends JFrame{
	private DrawingPanel drawingArea;
	private String path = "circles.ser";
	private ArrayList<Cercle> currentlist = new ArrayList<Cercle>();
	
    public Demo(String path) {
    this.setpath(path);
    deserializeCercles();
	setLocation(100,200);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(700,400);
    setTitle("Démo");
    this.drawingArea = new DrawingPanel(this);
    JMenuBar toolBar = new JMenuBar();
    
	JButton btn = new JButton("Save");
	JButton btn_1 = new JButton("S");
	JButton btn_2 = new JButton("Move");
	JButton btn_3 = new JButton("Open");
	toolBar.add(btn);
	toolBar.add(btn_1);
	toolBar.add(btn_2);
	toolBar.add(btn_3);
	setJMenuBar(toolBar);
	drawingArea.setBorder(BorderFactory.createLineBorder(Color.black));
	add(toolBar, BorderLayout.NORTH);
	add(drawingArea, BorderLayout.CENTER);
	
	
   
	btn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Test");
            serializeCercles();
        }
    });
	btn_1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Test");
            drawingArea.setstate((drawingArea.getstate()+1)%2);
        }
    });
	btn_2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Test");
            drawingArea.setstate2((drawingArea.getstate2()+1)%2);
        }
    });
	btn_3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Test");
            deserializeout();
            
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
                        // Use the selectedFile for deserialization
                        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                     
                        frame.dispose();
                        
                            

                        openNewWindow(selectedFile.getAbsolutePath()); // Open the new window with the deserialized circles
                        
                        
                    }
                }
            });

            frame.add(chooseFileButton, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
    private void openNewWindow(String path) {
        Demo newdemo = new Demo(path);
        // Set the loaded circles
        newdemo.setVisible(true);
    }
   
}


