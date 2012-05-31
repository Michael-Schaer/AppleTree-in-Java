import javax.swing.JFrame;

public class Launcher 
{
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 650;

	/**
	 * Launch the application, open a Frame
	 */
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		MyPanel panel = new MyPanel();
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Apple Tree");
		frame.setVisible(true);
	}
}