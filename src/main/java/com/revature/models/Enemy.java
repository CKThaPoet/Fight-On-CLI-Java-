package com.revature.models;

public class Enemy {

	private int enemy_id;
	private String enemy_name;
	private int role_type_fk;
	private String role_type;

	public Enemy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enemy(int enemy_id, String enemy_name, int role_type_fk, String role_type) {
		super();
		this.enemy_id = enemy_id;
		this.enemy_name = enemy_name;
		this.role_type_fk = role_type_fk;
		this.role_type = role_type;
	}

	public Enemy(String enemy_name, int role_type_fk, String role_type) {
		super();
		this.enemy_name = enemy_name;
		this.role_type_fk = role_type_fk;
		this.role_type = role_type;
	}

	public Enemy(String enemy_name, String role_type) {
		super();
		this.enemy_name = enemy_name;
		this.role_type = role_type;
	}

	public Enemy(String role_type, int role_type_fk) {
		super();
		this.role_type_fk = role_type_fk;
		this.role_type = role_type;
	}

	public Enemy(String enemy_name, String role_type, int role_type_fk) {
		super();
		this.enemy_name = enemy_name;
		this.role_type_fk = role_type_fk;
		this.role_type = role_type;
	}

	@Override
	public String toString() {
		return "Enemy [enemy_id=" + enemy_id + ", enemy_name=" + enemy_name + ", role_type_fk=" + role_type_fk
				+ ", role_type=" + role_type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + enemy_id;
		result = prime * result + ((enemy_name == null) ? 0 : enemy_name.hashCode());
		result = prime * result + ((role_type == null) ? 0 : role_type.hashCode());
		result = prime * result + role_type_fk;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enemy other = (Enemy) obj;
		if (enemy_id != other.enemy_id)
			return false;
		if (enemy_name == null) {
			if (other.enemy_name != null)
				return false;
		} else if (!enemy_name.equals(other.enemy_name))
			return false;
		if (role_type == null) {
			if (other.role_type != null)
				return false;
		} else if (!role_type.equals(other.role_type))
			return false;
		if (role_type_fk != other.role_type_fk)
			return false;
		return true;
	}

	public int getEnemy_id() {
		return enemy_id;
	}

	public void setEnemy_id(int enemy_id) {
		this.enemy_id = enemy_id;
	}

	public String getEnemy_name() {
		return enemy_name;
	}

	public void setEnemy_name(String enemy_name) {
		this.enemy_name = enemy_name;
	}

	public int getRole_type_fk() {
		return role_type_fk;
	}

	public void setRole_type_fk(int role_type_fk) {
		this.role_type_fk = role_type_fk;
	}

	public String getRole_type() {
		return role_type;
	}

	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}

}