package entity;

public class Member {

	private int memberId;
	private String firstName;
	private String lastName;

	public Member(int memberId, String firstName, String lastName) {
		this.setMemberId(memberId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
