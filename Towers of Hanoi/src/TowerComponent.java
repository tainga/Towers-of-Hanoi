import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * A class that creates a paint component that displays an animation of the solution to the Towers of Hanoi game
 * @author Anastasia Taing
 *
 */
public class TowerComponent extends JComponent{
	private TowersOfHanoi game;
	
	/**
	 * Constructs a component with a new instance of the Towers of Hanoi game with a given number of disks
	 * @param numberOfDisks number of disks to be used in the animation
	 */
	public TowerComponent(int numberOfDisks) {
		game = new TowersOfHanoi(numberOfDisks, this);
	}
	
	/**
	 * Creates a graphic representation of the Towers of Hanoi game
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
		game.draw(g2);
	}
	
	/**
	 * Creates a new thread that contains the animation process that will run in parallel with the main thread
	 */
	public void startAnimation() {
		
		class AnimationRunnable implements Runnable {
			
			public void run() {
				game.solve();
			}
		}
		Runnable r = new AnimationRunnable();
		Thread t = new Thread(r);
		t.start();
	}
}
