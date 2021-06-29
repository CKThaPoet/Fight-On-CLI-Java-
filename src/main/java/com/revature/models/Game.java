package com.revature.models;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.revature.daos.EnemyDao;

public class Game extends EnemyDao {

	public void Play() {

		boolean gameMenu = true;
		// boolean move = true;

		Scanner in = new Scanner(System.in); // scanner object to parse user input
		Random rand = new Random(); // used to get tthe random number for the health, damage, and enemy in the list
		String newL = System.lineSeparator(); // universal line separator for those on other operating systems it will
												// be used in place of \n for spacing

		// Make String array for enemies
		// String[] enemies = { "Dog", "Ninja", "BodyGuard", "Brawler", "Troll",
		// "Skeleton", "Orc" };
		// variables assigned for the enemy
		int enemy_health = 100;
		int enemy_attack_dmg = 35;

		// variables assigned for the player
		int player_health = 100; // Max health of enemies or player
		int player_attack_dmg = 50; // Max attack damage they can do
		int heal_items = 3; // Max health packs can hold
		int healAmount = 25; // amount health packs health for
		int healPackDropChance = 25; // percentage heal pack can drop

		// player name from database with their type placed in an array
		String[] playerRole = { " The Conqueror(Human)", " The Savage(Monster)", " The Beast(Animal)",
				" The Ghoul(Undead)" };

		// use a scanner to ask the user their name and place it in an object
		System.out.println("What is your name: ");
		String name = in.nextLine();

		// ask the role the player wants to play then use the scanner to get a number input
		System.out.println(
				"What player role do you want to be: Select enter the number that cooresponds with the role type" + newL
						+ "0 -The Conqueror(Human), 1 -The Savage(Monster), 2 -The Beast(Animal), 3 -The Ghoul(Undead)");
		int type = in.nextInt();

		// concatenate the object that holds the name the user entered and array namewith []
		// to enter the object that holds the number they entered so we can get the index of the array
		System.out.println(name + type);
		String player = new String(name + playerRole[type]);

		System.out.println("===============");
		System.out.println("Let's Fight On!");
		System.out.println("===============");

		// create an object of the class method that has access to the the database for the enemies to get the enemies
		// use the same array list to get the enemies for the view all from table to allow the game to get the enemies from the database
		EnemyDao ed = new EnemyDao();
		List<Enemy> enemies = ed.getEnemies();

		// use a label called whatever we you like to use to loop the game back to here use with break or continue
		// while true the game runs
		PLAY: while (gameMenu) {
			// use the get enemy name from constructor use i can show just the enemy name from the list in the database
			// use the object created from the random class above to get next random number from the enemy-Heath initialized
			// variable place in a object
			int enemyHealth = rand.nextInt(enemy_health); 
															
			// use the list variable to get the random number from the index place in an object
			Enemy Enemy = enemies.get(rand.nextInt(enemies.size())); 
																		 
			System.out.println(
					"\t> " + Enemy.getEnemy_name() + " " + Enemy.getRole_type() + "/Type" + " Appeared ! < " + newL);

			// while the enemy health is zero run code below
			// use escape sequence \t to tab from the left and instead of \n for line space
			// use object made with lineSeparator method

			// while in the other while loop another while will run as well
			while (enemyHealth > 0) {
				System.out.println(player + "\tyour HP: " + player_health);
				System.out.println("\t" + Enemy.getEnemy_name() + "'s HP " + enemyHealth);

				System.out.println("--------------------------");
				System.out.println("What would you like to do?");
				System.out.println(player + newL);

				// GameMenu options
				System.out.println("Enter 1 --> ATTACK*");
				System.out.println("Enter 2 --> HEAL++");
				System.out.println("Enter 3 --> RUN AWAY>>>");
				System.out.println("Enter 4 --> Returns you to the main menu");

				// parse the user input after the choose a menu option and move to the next line
				// also add another method to make user input always be lower case
				int input = in.nextInt();

				// take user input and use with switch case for the gameMenu options
				switch (input) {

				case 1: {
					// random number from number initialized for the player attack assigned to this object
					int damageDealt = rand.nextInt(player_attack_dmg); 
					// random number from number initialized for the enemy attack assigned to this object												
					int damageTaken = rand.nextInt(enemy_attack_dmg);  

					enemyHealth -= damageDealt; // player damage minus enemy health
					player_health -= damageTaken; // enemy damage minus player health

					System.out.println(player + "\t*  Hit the " + Enemy.getEnemy_name() + " for " + damageDealt
							+ " damage *" + newL);
					System.out.println(player + "\treceieved " + damageTaken + " as they counter attacked!" + newL);

					if (player_health < 0) {
						System.out.println(player + "\t! you have taken too much damage, you have met your end !");
						System.out.println("\t!GAME OVER!");
						// loop back to the beginning of the start of the game
						Play();
					}
					if (enemyHealth <= 0) {
						System.out.println("--------------------------");
						System.out.println("> " + Enemy.getEnemy_name() + " was slain ! <");
						System.out.println(player + " has " + player_health + " HP left. #");
						System.out.println("----------------------------------->");
					}
					// random number from 100 if less than the number assigned to the heal pack drop
					// chance increment by one
					if (rand.nextInt(100) < healPackDropChance) {
						heal_items++;
						System.out.println("+ The " + Enemy.getEnemy_name() + " dropped a health pack! +");
						System.out.println(player + " you now have " + heal_items + " in your inventory. ^++");
						System.out.println("----------------------------------");
						System.out.println(" Continue your onslaught... ATTACK");
						System.out.println(" +HEAL+");
						System.out.println(" RUN>>>");
						System.out.println(" Exit the game :(");
						System.out.println("----------------------------------->");
					}
					break;
				}
				case 2: {
					if (heal_items > 0) {
						player_health += healAmount; // heal amount plus player heal then decrement heal items carrying
						heal_items--;
						System.out.println("++You have healed yourself for " + healAmount + " ++ " + newL
								+ "\t> You now have " + player_health + " HP." + newL + "\t> You have " + heal_items
								+ " Health Packs in your inventory." + newL);
						System.out.println("FIGHT ON>>>>" + newL);
					} else {
						System.out.println(
								"\t You have no more heals left! Defeat an enemey for a chance of them dropping a heal pack"
										+ newL);
					}
					break;
				}
				case 3: {
					System.out.println("\t You ran away from the " + Enemy.getEnemy_name() + "!" + newL);
					continue PLAY;
				}

				case 4: {
					System.out.println("Come back and play again soon " + player);
					MainMenu menu = new MainMenu(); // makes an object from the class we are returning to
					menu.display(); // uses the object from the class we are returning to with the method where the
									// loop is located for the main menu
					break;
				}
				default: {
					System.out.println("Please try again " + player + " ¯\\_(..)_/¯");
					break;
				}
				}
			}
		}
		in.close();
	}
}
