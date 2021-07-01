package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.MainMenu;

import com.revature.utils.ConnectionUtil;

/**
 * @author Chani Kinsler
 *
 */
public class FightOnLauncher {

	public static void main(String[] args) {
		// here we will test weather our connection from the ConectionUtil Class is
		// successful
		try (Connection connect = ConnectionUtil.getConnection()) {
			System.out.println("*Connection Successful*");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("======================");
		System.out.println(" ");

		// instantiate a new object so we can use the display menu
		MainMenu menu = new MainMenu();

		menu.display();
	}
}
