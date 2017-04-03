import java.util.*;

public class Forest{
	WorldTile[][] planeOfExistence;
	int gridSize;

	public Forest(int size){
		planeOfExistence = new WorldTile[size][size];
		gridSize = size;

		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				planeOfExistence[i][j] = new WorldTile(i, j);
			}
		}
	}
	
	public void printTreeDistribution(){
		int totalSpaces = gridSize*gridSize;
		int numSaplings = 0;
		int numTrees = 0;
		int numElders = 0;
		int numBlank = 0;
		int numLumberjacks = 0;
		int numBears = 0;
		
		for(int i = 0; i < gridSize; i++){
			for(int j = 0; j < gridSize; j++){
				int tempAge;
				Tree currTree;
				if((currTree = planeOfExistence[i][j].hereTree) != null){
					tempAge = currTree.getAge();
					if(tempAge < 12){
						numSaplings++;
					}
					else if(tempAge < 120){
						numTrees++;
					}
					else{
						numElders++;
					}
				}
				else{
					numBlank++;
				}
				
				Creature currCreature;
				if((currCreature = planeOfExistence[i][j].hereCreature) != null){
					if(Lumberjack.class.isInstance(currCreature)){
						numLumberjacks++;
					}
					else{
						numBears++;
					}
				}
			}
		}
		
		System.out.println("Number of Saplings: " + numSaplings + "/" + totalSpaces);
		System.out.println("Number of Trees: " + numTrees + "/" + totalSpaces);
		System.out.println("Number of Elder Trees: " + numElders + "/" + totalSpaces);
		System.out.println("Number of Open Spaces: " + numBlank + "/" + totalSpaces);
		System.out.println("Number of Lumberjacks: " + numLumberjacks);
		System.out.println("Number of Bears: " + numBears);

	}
	
	public void passOneMonth(){
		for(int i = 0; i < gridSize; i++){
			for(int j = 0; j < gridSize; j++){
				Tree currTree = planeOfExistence[i][j].hereTree;
				if(currTree != null){
					currTree.growOlder();
					tryNewTree(currTree);
				}
			}
		}
	}
	
	void tryNewTree(Tree sourceTree){
		boolean spawn = sourceTree.attemptSpawn();
		if(spawn){
			int startX = sourceTree.xCoord;
			int startY = sourceTree.yCoord;
			int chance = (((int)Math.random()) * 1000) % 8;
			ArrayList<WorldTile> nearbySpaces = generateNearbySpaces(startX, startY);
			
			int tries = 0;
			while(tries < 8){
				tries++;
				WorldTile wt = nearbySpaces.get(chance);
				
				if(wt.hereTree == null){
					wt.spawnNewTree(1, wt.xCoord, wt.yCoord);
					break;
				}
				if(chance < 7){
					chance++;
				}
				else{
					chance = 0;
				}
			}
		}
	}
	
	ArrayList<WorldTile> generateNearbySpaces(int initX, int initY){
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
