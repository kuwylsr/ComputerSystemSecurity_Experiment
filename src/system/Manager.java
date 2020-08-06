package system;

public class Manager {

	private final String name;
	private final String password;
	
	private String answer = "";
	
	public Manager(String name , String password) {
		this.name = name;
		this.password = password;
	}
	
	public void accept() {
		this.answer = "ok!";
	}
	
	public void refuse() {
		this.answer = "no!";
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public void setAnswer(String ans) {
		this.answer = ans;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPasswd() {
		return this.password;
	}
}
