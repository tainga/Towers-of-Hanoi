import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
/**
 * A class for a disk to be used in the Towers of Hanoi game
 * @author Anastasia Taing
 *
 */
public class Disk {
     
    private int size;
    private Color color;
     
    /**
     * Constructs a disk of a given size and assigns it a random color
     * @param size the size of the disk
     */
    public Disk(int size)
    {
        this.size = size;
        Random rand = new Random();
  	  	int r = rand.nextInt(256);
  	  	int g = rand.nextInt(256);
  	  	int b = rand.nextInt(256);
  	  	color = new Color(r, g, b);
    }
     
    /**
     * Gets the size of the disk
     * @return disk size
     */
    public int getSize() {
        return size;
    }
     
    /**
     * Creates a Shape object to be used to draw the disk
     * @param x the x-coordinate of the center of the disk base line
     * @param y the y-coordinate of the center of the disk base line
     * @param height disk height
     * @return the graphical representation of the disk
     */
    public Shape shape(double x, double y, double height) {
        Rectangle2D middle = new Rectangle2D.Double(0, 0, size * height, height);
        Ellipse2D left = new Ellipse2D.Double(height / -2, 0, height, height);
        Ellipse2D right = new Ellipse2D.Double(size * height - height / 2, 0, height, height);
        Area shape = new Area(middle);
        shape.add(new Area(left));
        shape.add(new Area(right));
        AffineTransform tr = new AffineTransform();
        tr.translate(- size * height / 2, -height);
        tr.translate(x, y);
        Shape sh = tr.createTransformedShape(shape);
        return sh;
    }
    
    /**
     * Gets the color of the disk
     * @return disk color
     */
    public Color getColor() {
    	return color;
    }
     
}
     
