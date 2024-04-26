package Test;
import javax.swing.*;

public class Main extends JFrame {
	private static final long serialVersionUID = 1255797295121940654L;
	public int state = 0;
	
    public static void main(String[] args) {
        Demo f = new Demo("circles.ser");
        f.setVisible(true);
        
    }
    
}
