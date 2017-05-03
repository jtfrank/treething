/*
  They cut down trees, they skip and jump they like to press wild flowers.

  Lumberjacks each month will wander. They will move up to 3 times to a randomly
  picked spot that is adjacent in any direction. So for example a Lumberjack in
  the middle of your grid has 8 spots to move to. He will wander to a random
  spot. Then again. And finally for a third time.

  When the lumberjack moves if he encounters a Tree (not a sapling) he will stop
  and his wandering for that month comes to an end. He will then harvest the
  Tree for lumber. Remove the tree. Gain 1 piece of lumber. Lumberjacks will not
  harvest "Sapling". They will harvest an Elder Tree. Elder Trees are worth 2
  pieces of lumber.

  Every 12 months the amount of lumber harvested is compared to the number of
  lumberjacks in the forest. If the lumber collected equals or exceeds the
  amount of lumberjacks in the forest a new lumberjack is hired and randomly
  spawned in the forest. Actually a math formula is used to determine if we hire
  1 or many lumberjacks. We hire a number of new lumberjacks based on lumber
  gathered. Let us say you have 10 lumberjacks. If you harvest 10-19 pieces of
  lumber you would hire 1 lumberjack. But if you harvest 20-29 pieces of lumber
  you would hire 2 lumberjacks. If you harvest 30-39 you would gain 3
  lumberjacks. And so forth.

  However if after a 12 month span the amount of lumber collected is below the
  number of lumberjacks then a lumberjack is let go to save money and 1 random
  lumberjack is removed from the forest. However you will never reduce your
  Lumberjack labor force below 0.
 */

public class Lumberjack extends Creature {
	
	public Lumberjack(int x, int y){
		super(x, y);
		ageMonths = 216 + (((int)(Math.random() * 1000)) % 984);
	}
	
}