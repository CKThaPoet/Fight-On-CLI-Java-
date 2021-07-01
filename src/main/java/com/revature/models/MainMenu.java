package com.revature.models;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.EnemyDao;
import com.revature.daos.RoleDao;

public class MainMenu {

	// we need to instantiate the dao classes to use their methods in this class
	EnemyDao ed = new EnemyDao(); // object of the class
	RoleDao rd = new RoleDao(); // object of the class

	public void display() {

		boolean displayMenu = true;
		Scanner scan = new Scanner(System.in); // scanner object to parse user input
		String newL = System.lineSeparator();// new line
		final Logger log = LogManager.getLogger(MainMenu.class); //create logger import and place method in object must pass in main class

		System.out.println("=====================");
		System.out.println("Welcome to Fight On! ");
		System.out.println("=====================");

		while (displayMenu) {
			System.out.println("-----------------");
			System.out.println("Choose an option from the Main Menu");
			System.out.println("  ");

			// Main Menu options
			System.out.println("Enter: info --> To learn about the game. ");
			System.out.println("Enter: add --> To add your own custom enemies.");
			System.out.println("Enter: change --> To change the role of your custom enemies.");
			System.out.println("Enter: delete --> To delete your custom enemy creations.");
			System.out.println("Enter: view --> view all enemies and their type");
			System.out.println("Enter: play --> To Play Fight On!");
			System.out.println("Enter: exit --> exit application");

			// parse the user input after the choose a menu option and move to the next line
			// also add another method to make user input always be lower case
			String input = scan.nextLine().toLowerCase();
			switch (input) {

			case "info": {
				GameInformation.gameInfo();
			}
			case "add": {
				// we need to prompt the user for the enemy's name, and their role id
				System.out.println("Enter Enemy Name:");
				String enemy_name = scan.nextLine();

				System.out.println("Enter Enemy Role Type: Human, Monster, ,Animal, Undead");
				String role_type = scan.nextLine();

				System.out.println(
						"Enter Role Type Id: -> enter the number that corresponds to the role type = 1)Human 2)Monster 3)Animal 4)Undead ");
				int role_type_fk = scan.nextInt();
				scan.nextLine(); // because without any nextLine, your enter keystroke will be grabbed as the
									// next input

				// Given all this information, we'll create a new Enemy object to send to a DAO
				// method
				// This is using the all-args minus employee_id constructor
				Enemy newEnemy = new Enemy(enemy_name, role_type, role_type_fk);

				// Put the new Enemy into the addEnemy() method in the EnemyDao
				ed.addEnemy(newEnemy);
				log.info("User adding an Enemy"); //place some logs in important areas info type
				break;
			}
			case "change": {
				System.out.println("These are the enemies in the base game: " + newL);
				System.out.println("EnemyID Enemy Name  RoleID  Role Type");
				System.out.println("_______ __________  ______  _________");

				// this is using the already existing getEnemies() method with the list
				List<Enemy> enemies = ed.getEnemies();

				// Print out each Employee from the List one by one for the user to see
				for (Enemy e : enemies) {
					System.out.println(e.getEnemy_id() + ") " + "\t" + e.getEnemy_name() + "     \t"
							+ e.getRole_type_fk() + ") " + "    \t" + e.getRole_type());
				}

				System.out.println("----------------------------------------------------");

				System.out.println("Enter the number of the Enemy ID of the enemy who's role is changing:");
				int idInput = scan.nextInt(); // the user enters the ID of the enemy to change the role of that enemy
				scan.nextLine();

				System.out.println(
						"Type the Role Type you want your enemy changed to: Human, Monster, Animal, Undead or make up your own custom Role Type...");
				String typeInput = scan.nextLine(); // the user enters the ID of the enemy to change the role of that
													// enemy
				System.out.println(
						"Enter the number of the the Role ID of the enemy you are changing to: 1)Human 2)Monster 3)Animal 4)Undeador "
								+ "make up your own custom Role ID to match your Role Type *must be a number*...");
				int roleInput = scan.nextInt();
				scan.nextLine();

				// fool-proofing to prevent user from deleting base enemies
				if (idInput == 1 || idInput == 2 || idInput == 3 || idInput == 4 || idInput == 5 || idInput == 6
						|| idInput == 7) {
					System.out.println("YOU DO NOT HAVE THE POWER TO CHANGE THIS ENEMY!!!");
					 log.warn("User attempted to change enemies in the base game"); // warn type log
				} else {
					ed.changeRole(roleInput, typeInput, idInput);
				}
				break;
			}
			case "delete": {
				System.out.println("These are the emenimes in the game... who will you vanguish?" + newL
						+ "type 0 to return to the main menu." + newL);
				
				System.out.println("EnemyID Enemy Name  RoleID  Role Type");
				System.out.println("_______ __________  ______  _________");

				// this is using the already existing getEnemies() method
				List<Enemy> enemies = ed.getEnemies();

				// Print out each Employee from the List one by one for the user to see
				for (Enemy e : enemies) {
					System.out.println(e.getEnemy_id() + ") " + "\t" + e.getEnemy_name() + "     \t"
							+ e.getRole_type_fk() + ") " + "    \t" + e.getRole_type());
				}

				System.out.println("------------------------------");

				System.out.println("Enter the enemy id of the enemy  you want to VANGUISH:");

				int idInput = scan.nextInt();
				scan.nextLine();

				// example of some fool-proofing, in this case we don't want enemies with
				// example id = 1 BodyGaurd

				if (idInput == 1 || idInput == 2 || idInput == 3 || idInput == 4 || idInput == 5 || idInput == 6
						|| idInput == 7) {
					System.out.println("YOU CANT VANGUISH THIS ENEMY THEY ARE IMMORTAL!!!");
					 log.warn("User attempted to delete enemies in the base game"); // warn type log
				} else {
					ed.removeEnemy(idInput);
				}
				break;
			}
			case "view": {
				 log.info("User viewed all enemies in the database");
				System.out.println("Gathering all enemies..." + newL);
				System.out.println("EnemyID Enemy Name  RoleID  Role Type");
				System.out.println("_______ __________  ______  _________");

				// List of Employees that gets populated by the getEnemies method in our
				// EnemyDao
				List<Enemy> enemies = ed.getEnemies();

				// Print out each Enemy from the List one by one for the user to see using an
				// enhanced for loop
				for (Enemy e : enemies) {
					// a prettier way to return all employees (instead of relying on the toString
					// method, seen in the other cases)
					// making use of a getter and String concatenation
					System.out.println(e.getEnemy_id() + ") " + "\t" + e.getEnemy_name() + "     \t"
							+ e.getRole_type_fk() + ") " + "    \t" + e.getRole_type());
				}

				break;
			}
			case "play": {
				displayMenu = false;
				// fix this so player can go to other switch statement
				Game menu = new Game();
				menu.Play();
				break;
			}
			case "exit": {
				System.out.println("Come back and play again soon");
				scan.close();
				break;
			}
			default: {
				System.out.println("Please try again ¯\\_(..)_/¯ ");
				break;
			}
			}
		}
	}
}
