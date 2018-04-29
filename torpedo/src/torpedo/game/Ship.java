import java.util.ArrayList;

/* ToDos
 * 10-es paramétereket kicserélni max board sizera
 * JavaDoc írás
 */

public class Ship {
	
	//Properties of Ship class
	private int shipSizeDiff;
	private int shipSize;
	private int shipStartIndex;
	private int	shipEndIndex;
	private ArrayList<ShipPart> shipParts;
	
	//Constructor
	public Ship(int shipStartIndex, int shipEndIndex){
		this.shipStartIndex =shipStartIndex;
		this.shipEndIndex = shipEndIndex;
		
		setShipSizeDiff();
		setShipSize();
		setShipParts();	
	}	
	
	//Getter for shipSizeDiff 
	public int getShipSizeDiff() {
		return shipSizeDiff;
	}
	
	/*Setter for shipSizeDiff
	 * shipEndIndex must be higher than shipStartIndex, must have an error handler!!!
	 */
	public void setShipSizeDiff() {
		this.shipSizeDiff = this.shipEndIndex - this.shipStartIndex;
	}
	
	//Getter for shipSize
	public int getShipSize() {
		return shipSize;
	}
	
	//Setter for shipSize
	public void setShipSize() {
		this.shipSize = this.shipSizeDiff/10+this.shipSizeDiff%10+1;
	}
	
	//Getter for shipParts
	public ArrayList<ShipPart> getShipParts() {
		return shipParts;
	}
	
	//Setter for shipParts
	public void setShipParts() {
		this.shipParts = new ArrayList<ShipPart>();
		for(int i=0; i<this.shipSizeDiff%10+1; i++){
			
			for(int j=0; j<this.shipSizeDiff/10+1; j++){
				this.shipParts.add(new ShipPart(shipStartIndex+i+j*10));
			}		
		}
	}
	
	//Gives back the number of the healthy parts of the ship 
	public int getUnshootedParts() {
		int unshootedParts = 0;
		for(int i=0; i<this.shipSize; i++)
		{
			if(this.shipParts.get(i).getIsUnshooted()==true)
				unshootedParts++;
		}			
		return unshootedParts;
	}
	
	public boolean isDead(){
		boolean deadShip=false;
		if(this.getUnshootedParts()==0)
			deadShip = true;
		return deadShip;
	}
	
	public void shootShip(int index){
		if(this.shipSizeDiff>10)
			this.shipParts.get((index-this.shipStartIndex)/10).setIsUnshooted(false);
		else
			this.shipParts.get(index-this.shipStartIndex).setIsUnshooted(false);
	}
}
