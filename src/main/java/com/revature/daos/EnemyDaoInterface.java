package com.revature.daos;

import java.util.List;

import com.revature.models.Enemy;

public interface EnemyDaoInterface {

	public List<Enemy> getEnemies(); // return a List of all enemies

	public List<Enemy> getEnemies(String enemy_name, String role_type); // for game

	public void addEnemy(Enemy eNPC); // take a new Enemy object, add it to the database

	public void changeRole(int role_type_fk, String role_type, int enemy_Id); // change an enemie's role_id given their enemy id

	public void removeEnemy(int enemyId); // delete an enemy

	public List<Enemy> getEnemiesByRole(String type); // return a List of enemies of a certain role
}