package com.revature.models;

public class Player {

	private int player_id;
	private String player_name;
	private int role_type_fk;
	private String role_type;

	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Player(int player_id, String player_name, int role_type_fk, String role_type) {
		super();
		this.player_id = player_id;
		this.player_name = player_name;
		this.role_type_fk = role_type_fk;
		this.role_type = role_type;
	}

	public Player(String player_name, int role_type_fk, String role_type) {
		super();
		this.player_name = player_name;
		this.role_type_fk = role_type_fk;
		this.role_type = role_type;
	}

	public Player(String player_name, String role_type) {
		super();
		this.player_name = player_name;
		this.role_type = role_type;
	}

	public Player(String player_name) {
		super();
		this.player_name = player_name;
	}

	@Override
	public String toString() {
		return "Player [player_id=" + player_id + ", player_name=" + player_name + ", role_type_fk=" + role_type_fk
				+ ", role_type=" + role_type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + player_id;
		result = prime * result + ((player_name == null) ? 0 : player_name.hashCode());
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
		Player other = (Player) obj;
		if (player_id != other.player_id)
			return false;
		if (player_name == null) {
			if (other.player_name != null)
				return false;
		} else if (!player_name.equals(other.player_name))
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

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
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
