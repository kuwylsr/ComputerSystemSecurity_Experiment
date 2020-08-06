package system;

import java.util.HashMap;
import java.util.Map;

public class Worker {

	private final String name;
	private final String password;
	private final String id;
	
	public Map<Worker,User> map = new HashMap<>();
	private String message = "";
	private String answer = "";

	public Worker(String name, String password, String id) {
		this.name = name;
		this.password = password;
		this.id = id;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void accept() {
		this.answer = "ok!";
	}
	
	public void refuse() {
		this.answer = "no!";
	}
	
	public void setAnswer(String ans) {
		this.answer = "";
	}
	
	public void handleRequest(Manager m,Bank bank) {
		User u = this.map.get(this);
		String request = getMessage();
		String answerFromM = m.getAnswer();
		if(answerFromM.equals("ok!")&&this.answer.equals("ok!")) {
			Map<User,Double> Bmap = bank.getMoneySystem();
			if(request.split("\n")[0].equals("Get Money Now!")) {
				Bmap.put(u, Bmap.get(u) - Double.parseDouble(request.split("\n")[1]));
				u.setResult("Successfull for getting money! Remianing: " +Bmap.get(u));
			}else if(request.split("\n")[0].equals("Put Money Now!")) {
				Bmap.put(u, Bmap.get(u) + Double.parseDouble(request.split("\n")[1]));
				u.setResult("Successfull for putting money! Remianing: " +Bmap.get(u));
			}else if(request.equals("Look Money Now!")){
				u.setResult("Successfull for viewing money! Remianing: " +Bmap.get(u));
			}
		}else {
			u.setResult("Your apply is declined!");
		}
		this.setMessage("");
		this.setAnswer("");
		m.setAnswer("");
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getid() {
		return this.id;
	}
}
