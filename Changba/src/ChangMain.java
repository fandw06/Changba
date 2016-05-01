

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.SwingUtilities;

public class ChangMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void createAndShowGUI() {
		
		JFrame window = new JFrame("Haochang");
		ChangGUI gui = new ChangGUI();
		window.setSize(new Dimension(600, 580));
		window.setLocation(500, 100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
		
		window.add(gui.getOKSPanel());
		window.setJMenuBar(gui.getOKSMenubar());
	}

}
