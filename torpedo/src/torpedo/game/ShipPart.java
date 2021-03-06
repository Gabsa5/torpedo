
public class ShipPart {
	
	//Properties of ShipPart
	private boolean isUnshooted;
	private int shipPartIndex;
	
	//Constructor for ShipPart
	public ShipPart(int shipPartIndex){
		this.shipPartIndex=shipPartIndex;
		this.isUnshooted=true;
	}
	
	//Getter for shipPartIndex
	public int getShipPartIndex() {
		return shipPartIndex;
	}

	//Getter for isUnshooted
	public boolean getIsUnshooted() {
		return isUnshooted;
	}

	//Setter for isUnshooted
	public void setIsUnshooted(boolean isUnshooted) {
		this.isUnshooted = isUnshooted;
	}
	
}
