package Test;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

public class Demo extends JFrame{
	private DrawingPanel drawingArea;
    public Demo() {
	setLocation(100,200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(700,400);
    setTitle("Démo");
    this.drawingArea = new DrawingPanel();
    JMenuBar toolBar = new JMenuBar();
    
	JButton btn = new JButton("S");
	JButton btn_1 = new JButton("S");
	JButton btn_2 = new JButton("D");
	JButton btn_3 = new JButton("V");
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
    private void serializeCercles() {
        try {
            FileOutputStream fileOut = new FileOutputStream("circles.ser");
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

