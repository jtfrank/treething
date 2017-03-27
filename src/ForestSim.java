public class ForestSim{

	public static void main(String[] args){
		System.out.println("Welcome to ForestSim!");
		ForestSim world = new ForestSim();
		ArgBoat theBoat = world.parseInput(args);

		Forest mirkwood = new Forest(theBoat.gridSize);
		
		mirkwood.printTreeDistribution();
		
		for(int i = 0; i < 100; i++){
			mirkwood.passOneMonth();
		}
		
		mirkwood.printTreeDistribution();
	}

	/*  TODO
		parses input, passes back info using ArgBoat
	*/
	ArgBoat parseInput(String[] input){
		return new ArgBoat();
	}

	/*
	    The plan is to take in several arguments
	  	  - Grid Size (-n)
		  - Lumberjack start percentage (-l)
		  - Bear start percentage (-b)
		  - Tree start percentage (-t)
	    with sane defaults if any are left blank. This class is for passing back
	    the results from parsing the list of input arguments.
	*/
	class ArgBoat {
		int gridSize;
		int lumberjackPerc;
		int bearPerc;
		int treePerc;
	
		ArgBoat(){
			gridSize = 10;
			lumberjackPerc = 10;
			bearPerc = 2;
			treePerc = 50;
		}
	}
}
