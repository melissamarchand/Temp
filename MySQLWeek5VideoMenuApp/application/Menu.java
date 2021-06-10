package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.MemberDao;
import dao.TeamDao;
import entity.Member;
import entity.Team;

public class Menu {

	private TeamDao teamDao = new TeamDao();
	private MemberDao memberDao = new MemberDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList("Display Teams", "Display a Team", "Create a Team", "Delete Team",
			"Create Team Member", "Delete Team Member");

	public void start() throws SQLException {
		String selection = " ";

		do {
			printMenu();
			selection = scanner.nextLine();

			try {
				if (selection.equals("1")) {
					displayTeams();
				} else if (selection.equals("2")) {
					displayTeam();
				} else if (selection.equals("3")) {
					createTeam();
				} else if (selection.equals("4")) {
					deleteTeam();
				} else if (selection.equals("5")) {
					createMember();
				} else if (selection.equals("6")) {
					deleteMember();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("Press enter to continue . . . ");
			scanner.nextLine();
		} while (!selection.equals("-1"));

	}
private void printMenu() {
		System.out.println("Select an option:\n-----------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}

	private void displayTeams() throws SQLException {
		List<Team> teams = teamDao.getTeams();
		for (Team team : teams) {
			System.out.println(team.getTeamId() + ": " + team.getName());
		}
	}

	private void displayTeam() throws SQLException {
		System.out.println("Enter team id: ");
		int id = Integer.parseInt(scanner.nextLine());
		Team team = teamDao.getTeamById(id);
		System.out.println(team.getTeamId() + " : " + team.getName());
		for (Member member : team.getMembers()) {
			System.out.println("\tMemberId: " + member.getMemberId() + " - Name: " + member.getFirstName() + " "
					+ member.getLastName());
		}
	}

	private void createTeam() throws SQLException {
		System.out.println("Enter new team name: ");
		String teamName = scanner.nextLine();
		teamDao.createNewTeam(teamName);
	}

	private void deleteTeam() throws SQLException {
		System.out.println("Enter team id to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		teamDao.deleteTeamById(id);
	}

	private void deleteMember() throws SQLException {
		System.out.println("Enter member id to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		memberDao.deleteMemberById(id);

	}

	private void createMember() throws SQLException {
		System.out.println("Enter first name of new member: ");
		String firstName = scanner.nextLine();
		System.out.println("Enter last name of new member: ");
		String lastName = scanner.nextLine();
		System.out.println("Enter team id of new member: ");
		int teamId = Integer.parseInt(scanner.nextLine());
		memberDao.createNewMember(firstName, lastName, teamId);

	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

}
