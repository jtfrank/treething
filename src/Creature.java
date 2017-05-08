public class Creature {
	
	int ageMonths;
	int xCoord;
	int yCoord;
	boolean haveWandered = false;
	
	public Creature(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	public int getAge(){
		return ageMonths;
	}
	
	public void wander(WorldTile[][] planeOfExistence, int gridSize){
		if(haveWandered) return;
	}
	
	public void resetWander(){
		haveWandered = false;
	}
	
}
