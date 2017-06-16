import java.util.ArrayList;

public class WorldTile {
	public static ArrayList<Lumberjack> masterLumberjackList = new ArrayList<Lumberjack>();
	public static ArrayList<Bear> masterBearList = new ArrayList<Bear>();
	
	Tree hereTree = null;
	Creature hereCreature = null;
	int xCoord;
	int yCoord;
	
	public WorldTile(int x, int y){
		if(Math.random() <= .5){
			hereTree = new Tree(x, y);
		}
		if(Math.random() <= .1){
			hereCreature = new Lumberjack(x, y);
		}
		if(Math.random() <= .02){
			hereCreature = new Bear(x, y);
		}
		
		xCoord = x;
		yCoord = y;
	}
	
	void spawnNewTree(int startAge, int x, int y){
		hereTree = new Tree(startAge, x, y);
	}
	
	static ArrayList<WorldTile> generateNearbySpaces(int initX, int initY, WorldTile[][] planeOfExistence, int gridSize){
		ArrayList<WorldTile> listOfSpaces = new ArrayList<WorldTile>(8);
		int up, down, left, right;
		
		if(initX == 0){
			left = gridSize - 1;
			right = initX + 1;
		}
		else if(initX == (gridSize - 1)){
			right = 0;
			left = initX - 1;
		}
		else{
			left = initX -1;
			right = initX + 1;
		}
		
		if(initY == 0){
			up = gridSize - 1;
			down = initY + 1;
		}
		else if(initY == (gridSize - 1)){
			down = 0;
			up = initY - 1;
		}
		else{
			up = initY - 1;
			down = initY + 1;
		}
		
		listOfSpaces.add(planeOfExistence[initX][up]);
		listOfSpaces.add(planeOfExistence[right][up]);
		listOfSpaces.add(planeOfExistence[right][initY]);
		listOfSpaces.add(planeOfExistence[right][down]);
		listOfSpaces.add(planeOfExistence[initX][down]);
		listOfSpaces.add(planeOfExistence[left][down]);
		listOfSpaces.add(planeOfExistence[left][initY]);
		listOfSpaces.add(planeOfExistence[left][up]);
		
		return listOfSpaces;
	}
}