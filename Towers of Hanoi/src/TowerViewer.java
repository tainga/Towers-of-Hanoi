import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * A class to create a window that lets the user enter the number of disks and then animates the solution to the Towers of Hanoi puzzle
 * @author Anastasia Taing
 *
 */
public class TowerViewer {
	
	private JLabel discoLabel;
	private JLabel movesToSolve;
    private JTextField discoField;
    private JButton button;
    private int numberOfDisks;
    private TowerComponent component;
	
    /**
     * Constructs a frame
     */
    public TowerViewer() {
		JFrame frame = new JFrame();
		
		final int FRAME_WIDTH = 700;
		final int FRAME_HEIGHT = 400;
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Towers of Hanoi");
		component = new TowerComponent(0);
		frame.add(component, BorderLayout.CENTER);
		frame.add(createSolvePanel(frame), BorderLayout.SOUTH);
		frame.setVisible(true);
		component.startAnimation();
	}    
    
	/**
	 * Creates a bottom panel with the input field and solve button
	 * @param frame the frame that will contain the panel
	 * @return the panel
	 */
	private JPanel createSolvePanel(JFrame frame) {
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        JPanel panel2 = new JPanel();
        discoLabel = new JLabel("Enter the number of disks: ");
        discoField = new JTextField(2);
        button = new JButton("Solve");
        ActionListener listen = new SolveListener(frame);
        movesToSolve = new JLabel("?");
        panel.add(discoLabel);
        panel.add(discoField);
        panel.add(button);
        panel2.add(movesToSolve);
        button.addActionListener(listen);
        panel1.add(panel2, BorderLayout.SOUTH);
        panel1.add(panel, BorderLayout.NORTH); 
        return panel1;
    }
	
	/**
	 * An inner class that starts the animation when the solve button is pressed
	 * @author Group 1
	 *
	 */
    class SolveListener implements ActionListener
    {
    	private JFrame frame;
    	public SolveListener(JFrame frame) {
    		this.frame = frame;
    	}
    	public void actionPerformed(ActionEvent event)
    	{
    		try {
				String input = discoField.getText();
				if(input != null && !input.equals(""))
				{
					int number = Integer.parseInt (discoField.getText());
					if (number > 9 || number < 1) {
						movesToSolve.setText("Please enter a valid number (1-9)");
						return;
					}
					numberOfDisks = number;
					movesToSolve.setText("Minimum number of moves to solve: " + (int)(Math.pow(2, number) - 1));
					frame.remove(component);
					component = new TowerComponent(numberOfDisks);
					frame.add(component, BorderLayout.CENTER);
					component.startAnimation();
					component.repaint();
				}
			} catch (NumberFormatException e) {
				movesToSolve.setText("Please enter a valid number (1-9)");
			}
    	}
    }
}

	