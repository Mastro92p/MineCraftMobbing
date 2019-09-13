package me.mastro.TestUnimet;

import java.util.UUID;

public class PlayerMD  {
	
	public String name;
	public UUID UUID;
	public String rank;
	public PlayerRoll roll;
	
	public PlayerMD(String name, UUID uUID, String rank, PlayerRoll roll) {
		
		this.name = name;
		UUID = uUID;
		this.rank = rank;
		this.roll = roll;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UUID getUUID() {
		return UUID;
	}
	public void setUUID(UUID uUID) {
		UUID = uUID;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

	public PlayerRoll getRoll() {
		return roll;
	}

	public void setRoll(PlayerRoll roll) {
		this.roll = roll;
	}
	
	
	

}
