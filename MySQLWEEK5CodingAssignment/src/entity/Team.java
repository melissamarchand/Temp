package entity;

import java.util.List;

public class Team {

	private int teamId;
	private String name;
	private List<Member> members;

	public Team(int teamId, String name, List<Member> members) {
		this.setTeamId(teamId);
		this.setName(name);
		this.setMembers(members);
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}
