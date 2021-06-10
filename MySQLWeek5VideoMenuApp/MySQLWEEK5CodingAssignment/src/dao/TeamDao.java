package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Team;

public class TeamDao {

	private Connection connection;
	private MemberDao memberDao;
	private final String GET_TEAMS_QUERY = "SELECT * FROM teams";
	private final String GET_TEAM_BY_ID_QUERY = "SELECT * FROM teams WHERE id = ?";
	private final String CREATE_NEW_TEAM_QUERY = "INSERT INTO teams(name) VALUES (?)";
	private final String DELETE_TEAM_BY_ID_QUERY = "DELETE FROM teams WHERE id = ?";

	public TeamDao() {
		connection = DBConnection.getConnection();
		memberDao = new MemberDao();
	}

	public List<Team> getTeams() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_TEAMS_QUERY).executeQuery();
		List<Team> teams = new ArrayList<Team>();

		while (rs.next()) {
			teams.add(populateTeam(rs.getInt(1), rs.getString(2)));
		}
		return teams;
	}

	public Team getTeamById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_TEAM_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateTeam(rs.getInt(1), rs.getString(2));
	}

	public void createNewTeam(String teamName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_TEAM_QUERY);
		ps.setString(1, teamName);
		ps.executeUpdate();
	}

	private Team populateTeam(int id, String name) throws SQLException {
		return new Team(id, name, memberDao.getMembersByTeamId(id));
	}

	public void deleteTeamById(int id) throws SQLException {
		memberDao.deleteMembersByTeamId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_TEAM_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
}


	


