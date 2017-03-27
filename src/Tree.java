/*
  Every month a Tree has a 10% chance to spawn a new "Sapling". In a random open
  space adjacent to a Tree you have a 10% chance to create a "Sapling". For
  example a Tree in the middle of the forest has 8 other spots around it. One of
  these if they do not have a type of Tree in it will create a "Sapling".

  After 12 months of being in existence a "Sapling" will be upgrade to a "Tree".
  A "Sapling" cannot spawn other trees until it has matured into a "Tree".

  Once a "Sapling" becomes a tree it can spawn other new "Saplings". At this
  point once a "Sapling" matures into a "Tree" it exists and matures. When a
  "Tree" has been around for 120 months (10 years) it will become an "Elder
  Tree".

  Elder Trees have a 20% chance to spawn a new "Sapling" instead of 10%.

  If there are no open adjacent spots to a Tree or Elder Tree it will not spawn
  any new Trees.
*/

public class Tree {
	private int ageMonths;
	int xCoord;
	int yCoord;
	
	public Tree(int x, int y){
		double chance = Math.random();
		int iChance = (int)(chance * 1000);

		if(chance <= .2){
			ageMonths = iChance % 12 + 1;
		}
		else if(chance > .2 && chance <= .9){
			ageMonths = iChance % 108 + 12;
		}
		else{
			ageMonths = iChance % 120 + 120;
		}
		
		xCoord = x;
		yCoord = y;
	}
	
	public Tree(int startAge, int x, int y){
		ageMonths = startAge;
		xCoord = x;
		yCoord = y;
	}

	public void growOlder(){
		ageMonths++;
	}

	public boolean attemptSpawn(){
		double spawnChance;
		if(ageMonths < 12) return false;
		else if(ageMonths < 120) spawnChance = .1;
		else spawnChance = .2;

		if(Math.random() < spawnChance) return true;
		
		return false;
	}
	
	public int getAge(){
		return ageMonths;
	}
}
