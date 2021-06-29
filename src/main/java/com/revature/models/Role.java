package com.revature.models;

public class Role {

	private int role_id;
	private String role_type;

	// no args constructor
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

//constructor with args
	public Role(int role_id, String role_type) {
		super();
		this.role_id = role_id;
		this.role_type = role_type;
	}

	// constructor with args minus the role id
	public Role(String role_type) {
		super();
		this.role_type = role_type;
	}

	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_type=" + role_type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + role_id;
		result = prime * result + ((role_type == null) ? 0 : role_type.hashCode());
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
		Role other = (Role) obj;
		if (role_id != other.role_id)
			return false;
		if (role_type == null) {
			if (other.role_type != null)
				return false;
		} else if (!role_type.equals(other.role_type))
			return false;
		return true;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_type() {
		return role_type;
	}

	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
}