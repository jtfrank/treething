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
	
	public void printForestStatus(){
		int totalSpaces = gridSize*gridSize;
		int numSaplings = 0;
		int numTrees = 0;
		int numElders = 0;
		int numBlank = 0;
		int numLumberjacks = 0;
		int numBears = 0;
		
		double sapPercent, treePercent, elderPercent, blankPercent, lumberjackPercent, bearPercent = 0;
		
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
					if(currTree == null){
						numBlank--;
					}
				}
			}
		}
		
		sapPercent = (double)numSaplings/totalSpaces;
		treePercent = (double)numTrees/totalSpaces;
		elderPercent = (double)numElders/totalSpaces;
		blankPercent = (double)numBlank/totalSpaces;
		lumberjackPercent = (double)numLumberjacks/totalSpaces;
		bearPercent = (double)numBears/totalSpaces;
		
		numSaplings = (int) (sapPercent * 50);
		numTrees = (int) (treePercent * 50);
		numElders = (int) (elderPercent * 50);
		numBlank = (int) (blankPercent * 50);
		numLumberjacks = (int) (lumberjackPercent * 50);
		numBears = (int) (bearPercent * 50);
		
		while(numSaplings + numTrees + numElders + numBlank + numLumberjacks + numBears < 50){
			numBlank++;
		}
		
		System.out.print("[");
		for(int i = 0; i < numSaplings; i++){
			System.out.print("S");
		}
		for(int i = 0; i < numTrees; i++){
			System.out.print("T");
		}
		for(int i = 0; i < numElders; i++){
			System.out.print("E");
		}
		for(int i = 0; i < numLumberjacks; i++){
			System.out.print("L");
		}
		for(int i = 0; i < numBears; i++){
			System.out.print("B");
		}
		for(int i = 0; i < numBlank; i++){
			System.out.print(".");
		}
		System.out.println("]");
	}
	
	public void printForest(){
		System.out.print("+");
		for(int i = 0; i < ((gridSize * 2) + 1); i++){
			System.out.print("-");
		}
		System.out.println("+");
		
		for(int i = 0; i < gridSize; i++){
			System.out.print("| ");
			for(int j = 0; j < gridSize; j++){
				Creature currCreature;
				if((currCreature = planeOfExistence[i][j].hereCreature) != null){
					if(Lumberjack.class.isInstance(currCreature)){
						System.out.print("L ");
					}
					else{
						System.out.print("B ");
					}
					continue;
				}
				
				int tempAge;
				Tree currTree;
				if((currTree = planeOfExistence[i][j].hereTree) != null){
					tempAge = currTree.getAge();
					if(tempAge < 12){
						System.out.print("S ");
					}
					else if(tempAge < 120){
						System.out.print("T ");
					}
					else{
						System.out.print("E ");
					}
				}
				else{
					System.out.print(". ");
				}
				
			}
			System.out.println("|");
		}
		
		System.out.print("+");
		for(int i = 0; i < ((gridSize * 2) + 1); i++){
			System.out.print("-");
		}
		System.out.println("+");
	}
	
	public MonthlyResults passOneMonth(){
		MonthlyResults results = new MonthlyResults();
		
		for(int i = 0; i < gridSize; i++){
			for(int j = 0; j < gridSize; j++){
				Tree currTree = planeOfExistence[i][j].hereTree;
				Creature currCreature = planeOfExistence[i][j].hereCreature;
				if(currTree != null){
					currTree.growOlder();
					tryNewTree(currTree);
				}
				if(currCreature != null){
					int retval = 0;
					retval = currCreature.wander(planeOfExistence, gridSize);
					if(currCreature instanceof Lumberjack){
						results.lumberHarvested += retval;
					}
					if(currCreature instanceof Bear){
						results.mawIncidents += retval;
					}
				}
			}
		}
		
		for(int i = 0; i < gridSize; i++){
			for(int j = 0; j < gridSize; j++){
				Creature currCreature = planeOfExistence[i][j].hereCreature;
				if(currCreature != null){
					currCreature.resetWander();
				}
			}
		}
		
		if(WorldTile.masterLumberjackList.size() == 0){
			addRandomLumberjack();
		}
		
		return results; 
	}
	
	public void passOneYear(){
		int lumberHarvested = 0;
		int mawIncidents = 0;
		MonthlyResults results;
		for(int i = 0; i < 12; i ++){
			results = this.passOneMonth();
			lumberHarvested += results.lumberHarvested;
			mawIncidents += results.mawIncidents;
		}
		if(mawIncidents > 0){
			removeRandomBear();
		}
		else{
			addRandomBear();
		}
		
		int numLumberjacks = WorldTile.masterLumberjackList.size();
		if(lumberHarvested > numLumberjacks){
			int tempLumber = lumberHarvested;
			while(tempLumber > numLumberjacks){
				addRandomLumberjack();
				tempLumber -= 25;
			}
		}
		else{
			int tempLumber = lumberHarvested;
			while(tempLumber < numLumberjacks){
				removeRandomLumberjack();
				tempLumber += 1;
			}
		}
		
		this.printForestStatus();
	}
	
	void tryNewTree(Tree sourceTree){
		boolean spawn = sourceTree.attemptSpawn();
		if(spawn){
			int startX = sourceTree.xCoord;
			int startY = sourceTree.yCoord;
			int chance = ((int)(Math.random() * 1000)) % 8;
			ArrayList<WorldTile> nearbySpaces = WorldTile.generateNearbySpaces(startX, startY, planeOfExistence, gridSize);
			
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
	
	void populateMasterLists(){
		for(int i = 0; i < gridSize; i++){
			for(int j = 0; j < gridSize; j++){
				Creature currCreature = planeOfExistence[i][j].hereCreature;
				if(currCreature != null){
					if(currCreature instanceof Lumberjack){
						WorldTile.masterLumberjackList.add((Lumberjack)currCreature);
					}
					if(currCreature instanceof Bear){
						WorldTile.masterBearList.add((Bear)currCreature);
					}
				}
			}
		}	
	}
	
	Bear chooseRandomBear(){
		int numBears = WorldTile.masterBearList.size();
		int numberOfTheBeast = ((int)(Math.random() * 1000)) % numBears;
		return WorldTile.masterBearList.get(numberOfTheBeast);
	}
	
	Lumberjack chooseRandomLumberjack(){
		int numLumberjacks = WorldTile.masterLumberjackList.size();
		int numberOfTheMan = ((int)(Math.random() * 1000)) % numLumberjacks;
		return WorldTile.masterLumberjackList.get(numberOfTheMan);
	}
	
	WorldTile chooseRandomWorldTile(){
		int randNum1 = ((int)(Math.random() * 1000)) % gridSize;
		int randNum2  = ((int)(Math.random() * 1000)) % gridSize;
		return planeOfExistence[randNum1][randNum2];
	}
	
	void addRandomLumberjack(){
		WorldTile theTile = chooseRandomWorldTile();
		int i = 0;
		while(theTile.hereCreature != null && i < (gridSize*gridSize)){
			theTile = chooseRandomWorldTile();
			i++;
		}
		theTile.hereCreature = new Lumberjack(theTile.xCoord, theTile.yCoord);
	}
	
	void addRandomBear(){
		WorldTile theTile = chooseRandomWorldTile();
		int i = 0;
		while(theTile.hereCreature != null && i < (gridSize*gridSize)){
			theTile = chooseRandomWorldTile();
			i++;
		}
		theTile.hereCreature = new Bear(theTile.xCoord, theTile.yCoord);
	}
	
	void removeRandomBear(){
		Bear unluckyBear = chooseRandomBear();
		WorldTile.masterBearList.remove(unluckyBear);
		planeOfExistence[unluckyBear.xCoord][unluckyBear.yCoord].hereCreature = null;
	}
	
	void removeRandomLumberjack(){
		Lumberjack unluckyJack= chooseRandomLumberjack();
		WorldTile.masterLumberjackList.remove(unluckyJack);
		planeOfExistence[unluckyJack.xCoord][unluckyJack.yCoord].hereCreature = null;
	}
	
	class MonthlyResults {
		int lumberHarvested;
		int mawIncidents;
		
		MonthlyResults(){
			lumberHarvested = 0;
			mawIncidents = 0;
		}
	}
	
}
