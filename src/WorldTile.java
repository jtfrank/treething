public class WorldTile {
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
}