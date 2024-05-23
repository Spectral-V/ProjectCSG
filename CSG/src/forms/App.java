package forms;
import javax.swing.JFrame;

public class App extends JFrame {
	private static final long serialVersionUID = 1255797295121940654L;
	
    public static void main(String[] args) {
        CSGwindows f = new CSGwindows("circles.ser");
        f.setVisible(true);
        
    }
    
}
