package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

public class RoleDao implements RoleDaoInterface {

	@Override
	public List<Role> getRoleType(int enemyId, String type) {
		try (Connection connect = ConnectionUtil.getConnection()) {

			// initialize an empty ResultSet that will store the results of our query
			ResultSet rs = null; // we need this for select statements, so that the returned data can get stored

			// write the query, assign it to a String variable
			String sql = "SELECT * FROM \"FightOn\".enemies where role_type = ?;";

			// creating an object to send the query to our DB using our Connection object's createStatement() method
			PreparedStatement ps = connect.prepareStatement(sql);

			// set the wildcard to the title the user inputs
			ps.setInt(1, enemyId);
			ps.setString(2, type);

			// execute the query (sql) using our PreparedStatement object (ps), put it in our ResultSet (rs)
			rs = ps.executeQuery(); // again, the ResultSet just holds all the data we get back from the select statement


			List<Role> returnedRole = new ArrayList<>(); // create a List that will be populated with the returned role

			while (rs.next()) { // while there are results left in the ResultSet (rs)

				// Create a new role Object from each returned record
				// This is the role Class's all args constructor
				// And we're filling it with each column of the role table
				Role role = new Role(rs.getInt("role_id"), rs.getString("role_type"));

				// add the newly created role object into the ArrayList of role
				returnedRole.add(role);
			}
			return returnedRole; // Finally, if successful, return the List of Employees

		} catch (SQLException e) {
			System.out.println("get role by type failed");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRoleType(int enemyId, String type) {
		try (Connection connect = ConnectionUtil.getConnection()) {

			String sql = "update \"FightOn\".roles set enemy_id = ? where role_type = ?;"; // write out the SQL query

			PreparedStatement ps = connect.prepareStatement(sql); // put the SQL query into a PreparedStatement

			// set the values in the prepared statement with the values inputed by the user
			ps.setInt(1, enemyId);
			ps.setString(2, type);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("WE have failed :(");
			e.printStackTrace();
		}
	}

}
