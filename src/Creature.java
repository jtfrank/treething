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
	
	public int wander(WorldTile[][] planeOfExistence, int gridSize){
		return 0;
	}
	
	public void resetWander(){
		haveWandered = false;
	}
	
}
