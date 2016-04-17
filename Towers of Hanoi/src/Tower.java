import java.util.Stack;
/**
 * A class for a tower to be used in the Towers of Hanoi game
 * @author Anastasia Taing
 *
 */
public class Tower {
    private String towerName;
    private Stack<Disk> disks;
    
    /**
     * Constructs an empty tower
     * @param name tower name
     */
    public Tower(String name) {
        disks = new Stack<>();
        this.towerName = name;
    }
     
    /**
     * Constructs a tower with a given number of disks
     * @param numOfDisks number of disks
     * @param towerName name of the tower
     */
    public Tower(int numOfDisks, String towerName)
    {
        this.towerName = towerName;
        disks = new Stack<Disk>();
        for(int i = numOfDisks; i >= 1; i--)
        {
            Disk discos = new Disk(i);
            disks.push(discos);
        }
    }
     
    /**
     * Adds a disk to the tower
     * @param disk the disk to be added
     */
    public void addDisk(Disk disk) {
            disks.push(disk);
    }
     
    /**
     * Removes the topmost disk from the tower
     * @return the disk that has been removed
     */
    public Disk removeDisk() {
        return disks.pop();
    }
    
    /**
     * Gets a list of the disks that this tower contains
     * @return a stack containing the disks
     */
    public Stack<Disk> getDisks() {
    	return (Stack<Disk>)disks.clone();
    }
     
    /**
     * Gets the name of the tower
     * @return tower name
     */
    public String getName() {
        return towerName;
    }
}
