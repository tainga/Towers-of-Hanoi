import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JComponent;
import javax.swing.JPanel;
/**
 * A class to represent the Towers of Hanoi game
 * @author Anastasia Taing
 *
 */
public class TowersOfHanoi extends JPanel {
	
	private Tower A;
	private Tower B;
	private Tower C;
	private int num;
	private String solution;
	private JComponent component;
	private ReentrantLock stateLock;
	
	/**
	 * Constructs a new Towers of Hanoi game with the given number of disks
	 * @param numberOfDisks number of disks
	 * @param component the component that displays the graphics
	 */
	public TowersOfHanoi(int numberOfDisks, JComponent component)
	{
		num = numberOfDisks;
		A = new Tower(num, "A");
		B = new Tower("B");
		C = new Tower("C");
		solution = "";
		this.component = component;
		//creates a lock object that prevents different threads to make changes to the same process at the same time
		stateLock = new ReentrantLock();
	}
	
	/**
	 * Solves the Towers of Hanoi puzzle
	 */
	public void solve()
	{
		if (num == 0) {
			return;
		}
		pause();
		solve(num, A, B, C);
	}
	
	/**
	 * Solves the Towers of Hanoi puzzle
	 * @param num number of disks
	 * @param x the tower where the disks are located
	 * @param y the helper tower
	 * @param z the destination tower
	 */
    private void solve(int num, Tower x, Tower y, Tower z) {
        int n = num;
        if (n == 1)
        {
        	stateLock.lock();
        	try {
	            z.addDisk(x.removeDisk());
	            solution += x.getName() + " -> " + z.getName() + "\n";
        	}
        	finally {
        		stateLock.unlock();
        	}
        	pause();
        }
        else
        {
            solve(n - 1, x, z, y);
            stateLock.lock();
            try {
	            z.addDisk(x.removeDisk());
	            solution += x.getName() + " -> " + z.getName() + "\n";
            }
            finally {
        		stateLock.unlock();
        	}
        	pause();
	        solve(n - 1, y, x, z);
        }
    }
    
    /**
     * Creates the graphical representation of the solution
     * @param g2 the Graphics2D object that displays the result
     */
    public void draw(Graphics2D g2) {
    	
	     stateLock.lock();
	     try {
	    	 setBackground(Color.white);
	    	 int width = 700;
	         int height = 300;
	            
	         int xUnit = width/10;
	         int yUnit = height/10;
	         
	         Rectangle2D base = new Rectangle2D.Double(xUnit, yUnit * 9, xUnit * 8, yUnit * 2);
	         g2.setColor(new Color(86, 47, 14));
	         g2.fill(base);
	            
	         Rectangle2D tower1 = new Rectangle2D.Double(xUnit * 2.5 - xUnit/10, yUnit * 2, xUnit / 5, yUnit * 7);
	         g2.fill(tower1);
	           
	         Rectangle2D tower2 = new Rectangle2D.Double(xUnit * 5 - xUnit/10, yUnit * 2, xUnit / 5, yUnit * 7);
	         g2.fill(tower2);
	           
	         Rectangle2D tower3 = new Rectangle2D.Double(xUnit * 7.5 - xUnit/10, yUnit * 2, xUnit / 5, yUnit * 7);
	         g2.fill(tower3);
	         
	         double diskHeight = yUnit * .8;
	         
	    	Stack<Disk> disks = A.getDisks();
         	int numberOfDisks = disks.size();

         	for (int i = 0; i < numberOfDisks; i++) {
       		  Disk d = disks.pop();
       		  g2.setColor(d.getColor());
       		  g2.fill(d.shape(xUnit * 2.5, yUnit * 9.8  - diskHeight * numberOfDisks + diskHeight * i, diskHeight));
         	}
         	
         	disks = B.getDisks();
         	numberOfDisks = disks.size();

         	for (int i = 0; i < numberOfDisks; i++) {
       		  Disk d = disks.pop();
       		  g2.setColor(d.getColor());
       		  g2.fill(d.shape(xUnit * 5, yUnit * 9.8  - diskHeight * numberOfDisks + diskHeight * i, diskHeight));
         	}
         	
         	disks = C.getDisks();
         	numberOfDisks = disks.size();

         	for (int i = 0; i < numberOfDisks; i++) {
       		  Disk d = disks.pop();
       		  g2.setColor(d.getColor());
       		  g2.fill(d.shape(xUnit * 7.5, yUnit * 9.8  - diskHeight * numberOfDisks + diskHeight * i, diskHeight));
         	}
	     }
		 finally {
		    stateLock.unlock();
		 }
    }
     
    /**
     * Gets the string representation of the solution
     * @return
     */
    public String getSolution() {
        return solution;
    }
     
    /**
     * Gets the minimum number of moves required to solve the puzzle
     * @return the number of moves
     */
    public int minMoves() {
        return (int)(Math.pow(2, num) - 1);
    }

    /**
     * Pauses the thread for 2 seconds to slow down the animation
     */
    public void pause() {
	     component.repaint();
	     try {
			Thread.sleep(2000);
		 } catch (InterruptedException e) {
			e.printStackTrace();
		 }
    }
}
