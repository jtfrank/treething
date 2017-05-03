/*
  They wander the forest much like a lumberjack. Instead of 3 spaces a Bear will
  roam up to 5 spaces. If a bear comes across a Lumberjack he will stop his
  wandering for the month. (For example after 2 moves the bear lands on a space
  with a lumberjack he will not make any more moves for this month)

  Lumberjacks smell like pancakes. Bears love pancakes. Therefore the Bear will
  unfortunately maw and hurt the lumberjack. The lumberjack will be removed from
  the forest (He will go home and shop on wednesdays and have buttered scones
  for tea).

  We will track this as a "Maw" accident. During the course of 12 months if
  there 0 "Maw" accidents then the Bear population will increase by 1. If
  however there are any "Maw" accidents the Lumberjacks will hire a Zoo to trap
  and take a Bear away. Remove 1 random Bear. Note that if your Bear population
  reaches 0 bears then there will be no "Maw" accidents in the next year and so
  you will spawn 1 new Bear next year.

  If there is only 1 lumberjack in the forest and he gets Maw'd. He will be sent
  home. But a new one will be hired immediately and respawned somewhere else in
  the forest. The lumberjack population will not drop below 1.
*/

public class Bear extends Creature {
	
	public Bear(int x, int y){
		super(x, y);
		ageMonths = 36 +(((int)(Math.random() * 1000)) % 600);
	}
	
}