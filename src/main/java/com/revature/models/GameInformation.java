package com.revature.models;

import java.util.Scanner;

public class GameInformation {
	
	public static void gameInfo() {
		String newL = System.lineSeparator();
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("Welcome to Fight On! " + newL + "This a Command Line Interface texted based game made by Chani Kinsler using JAVA program language, MAVEN the project object management"
				+ newL + "tool to add extra functionality to our program using the POM.XML, some of the features are log4j for loggin important events in the application, Junit for testing methods"
				+ newL + "and pending features, and I can add more properties and dependencies in the future as needed." 
				+ newL + "ProstgresSQL is also used for JDBC database handling in this project it is using a one to many relationship." 
				+ newL + "This game is about you the player fighting until your last breath so let's play..." 
				+ newL + "You have the option of adding your own custom enemies to the game, changing their roles, and deleting the ones you add... " 
				+ newL + "while playing the game you can stop at any time and return to the main menu." + newL);
		
		System.out.println("===============================================================");
		
		System.out.println("These are the Role Types in the game that you or the base game enemy can be " + newL +
				"( Human )" + newL + "( Monster )" + newL + "( Animal )" + newL +"( Undead )" + newL);
		
		System.out.println("These are the the role types the player can play as" + newL +
				"The Conqueror(Human)"+ newL+ "The Savage(Monster)"+ newL+ "The Beast(Animal)"+ newL+ "The Ghoul(Undead)" + newL);
		
		System.out.println("These are the current enemies in the base game " +newL+
				"Dog" +newL+ "Ninja" +newL+ "BodyGuard" +newL+ "Brawler" +newL+ "Troll" +newL+ "Skeleton" +newL+ "Orc" + newL);
		
		System.out.println("Thank you for reading the Game Infomation");
		System.out.println("Enter: back -->  to return to the main menu");
		String input = scan.nextLine().toLowerCase();
		//add an ability here to return to the switch statement
		if(input.equals("back")) {
		MainMenu menu = new MainMenu();
		menu.display();
		}
		scan.close();
	}
}
