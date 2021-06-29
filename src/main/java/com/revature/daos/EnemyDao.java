package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Enemy;
import com.revature.utils.ConnectionUtil;

public class EnemyDao implements EnemyDaoInterface {

	@Override
	public List<Enemy> getEnemies() {
		try (Connection connect = ConnectionUtil.getConnection()) { // try to establish a DB connection, so we can run a query

			// initialize an empty ResultSet that will store the results of our query
			ResultSet rs = null; // we need this for select statements, so that the returned data can get stored

			// write the query, assign it to a String variable
			String sql = "SELECT * FROM \"FightOn\".enemies;";

			// creating an object to send the query to our DB using our Connection object's createStatement() method
			Statement s = connect.createStatement();

			// execute the query (sql) using our Statement object (s), put it in our
			// ResultSet (rs)
			rs = s.executeQuery(sql); // again, the ResultSet just holds all the data we get back from the select
										// statement

			List<Enemy> enemyList = new ArrayList<>(); // create a List that will be populated with the returned enemies

			while (rs.next()) { // while there are results left in the ResultSet (rs)

				// Create a new Enemy Object from each returned record
				// This is the Enemy Class's all args constructor
				// And we're filling it with each column of the Employee record
				Enemy enemy = new Enemy(rs.getInt("enemy_id"), rs.getString("enemy_name"), rs.getInt("role_type_fk"),
						rs.getString("role_type"));

				// add the newly created Enemy object into the ArrayList of Employees
				enemyList.add(enemy);
			}
			return enemyList; // Finally, if successful, return the List of Enemies

		} catch (SQLException e) { // if something goes wrong accessing our data, it will get caught
			System.out.println("Something went wrong when trying to access the DataBase");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Enemy> getEnemies(String enemy_name, String role_type) {
		try (Connection connect = ConnectionUtil.getConnection()) { // try to establish a DB connection, so we can run a query
			// initialize an empty ResultSet that will store the results of our query
			ResultSet rs = null; // we need this for select statements, so that the returned data can get stored

			// write the query, assign it to a String variable
			String sql = "SELECT enemy_name, role_type FROM \"FightOn\".enemies;";

			// creating an object to send the query to our DB using our Connection object's createStatement() method
			Statement s = connect.createStatement();

			// execute the query (sql) using our Statement object (s), put it in our ResultSet (rs) 
			rs = s.executeQuery(sql); // again, the ResultSet just holds all the data we get back from the select statement

			List<Enemy> enemyList = new ArrayList<>(); // create a List that will be populated with the returned enemies

			while (rs.next()) { // while there are results left in the ResultSet (rs)

				Enemy enemy = new Enemy(rs.getString("enemy_name"), rs.getString("role_type"));

				// add the newly created Enemy object into the ArrayList of Employees
				enemyList.add(enemy);
			}
			return enemyList; // Finally, if successful, return the List of Enemies

		} catch (SQLException e) { // if something goes wrong accessing our data, it will get caught
			System.out.println("Something went wrong when trying to access the DataBase");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addEnemy(Enemy eNPC) {
		try (Connection connect = ConnectionUtil.getConnection()) {

			// we're going to create a SQL statement using parameters to insert a new Enemy
			String sql = "INSERT INTO \"FightOn\".enemies (enemy_name , role_type_fk, role_type) " 
					+ "VALUES (?, ?, ?);"; // these are parameters!!! We have to now specify the value of each "?"

			PreparedStatement ps = connect.prepareStatement(sql); // we use PreparedStatements for SQL commands with parameters

			// use the PreparedStatement object's methods to insert values into the SQL query's ?s
			// the values will come from the Employee object we sent in
			// this requires two arguments, the number of the "?", and the value to give it
			ps.setString(1, eNPC.getEnemy_name());
			ps.setInt(2, eNPC.getRole_type_fk());
			ps.setString(3, eNPC.getRole_type());

			// this method actually sends and executes the SQL command that we built
			ps.executeUpdate(); // we use executeUpdate for inserts, updates, and deletes.

			// send confirmation to the console if successful
			System.out.println("You have spawned " + eNPC.getEnemy_name() + " they are prepared to Fight On! ");

		} catch (SQLException e) {
			System.out.println("Add enemy failed!");
			e.printStackTrace();
		}
	}

	@Override
	public void changeRole(int role_type_fk, String role_type, int enemy_Id) {
		try (Connection connect = ConnectionUtil.getConnection()) {

			// notice how there are no ResultSet object in methods that don't include select statements...
			// because we aren't returning anything! Only changing stuff in the DB, not getting data from it.
			
			String sql = "UPDATE \"FightOn\".enemies SET role_type_fk = ?, role_type = ? WHERE enemy_id = ?;";

			PreparedStatement ps = connect.prepareStatement(sql); // make a PreparedStatement using the SQL String we made

			// adding values to the wildcard parameters based on the user's input
			ps.setInt(1, role_type_fk);
			ps.setString(2, role_type);
			ps.setInt(3, enemy_Id);

			ps.executeUpdate(); // Run the PreparedStatement now that we've given values to its parameters

			System.out.println("Enemy role_id changed to: " + enemy_Id);

		} catch (SQLException e) {
			System.out.println("Change role failed!");
			e.printStackTrace();
		}

	}

	@Override
	public void removeEnemy(int enemyId) {
		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM \"FightOn\".enemies WHERE enemy_id = ?;";

			PreparedStatement ps = connect.prepareStatement(sql);

			ps.setInt(1, enemyId);

			ps.executeUpdate();

			System.out.println("Enemy with the an id of " + enemyId + " WAS VANGUISHED!");

		} catch (SQLException e) {
			System.out.println("Delete enemy failed... they live to Fight On!");
			e.printStackTrace();
		}

	}

	@Override
	public List<Enemy> getEnemiesByRole(String type) {
		try (Connection connect = ConnectionUtil.getConnection()) {
			ResultSet rs = null;

			String sql = "select * from \"FightOn\".enemies join roles "
					+ "on enemies.role_id = roles.role_id where roles.role_type = ?;";

			PreparedStatement ps = connect.prepareStatement(sql);

			ps.setString(1, type); // insert the method's argument as the first (and only) variable in the query

			rs = ps.executeQuery();

			List<Enemy> enemyList = new ArrayList<>();

			while (rs.next()) { // while there are results in the result set...

				Enemy e = new Enemy( // create a new Employee Object from each returned row..
						rs.getInt("enemy_id"), rs.getString("enemy_name"), rs.getInt("role_type_fk"),
						rs.getString("role_type"));

				enemyList.add(e); // and populate the ArrayList with each created Role Object
			}

			return enemyList; // Finally, return the populated List of Roles.

		} catch (SQLException e) {
			System.out.println("Something went wrong with your SQL!");
			e.printStackTrace();
		}
		return null;
	}
}
