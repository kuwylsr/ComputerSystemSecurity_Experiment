package system;

import java.util.HashMap;
import java.util.Map;

public class User {
	
	private final String name;
	private final String password;
	private String result = "";
	public Map<User,Worker> map = new HashMap<>();
	
	public User(String name,String password) {
		this.name = name;
		this.password = password;
	}
	
	public void getMoney(String money) {
		Worker w = this.map.get(this);
		String request = "Get Money Now!";
		this.result = "";
		w.setMessage(request + "\n" + money);
	}
	
	public void putMoney(String money) {
		Worker w = this.map.get(this);
		String request = "Put Money Now!";
		this.result = "";
		w.setMessage(request + "\n" + money);
	}
	
	public void viewMoney() {
		Worker w = this.map.get(this);
		String request = "Look Money Now!";
		this.result = "";
		w.setMessage(request);
	}
	
	public void setResult(String reply) {
		this.result = reply;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
}
