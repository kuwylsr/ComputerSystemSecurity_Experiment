package system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class Bank {
	
	
	    
	private Set<User> SetUser = new HashSet<>();
	public Set<Worker> SetWorker = new HashSet<>();
	public Manager manager;
	
	private Map<User,Double> UToMoney = new HashMap<>();
	
	public Bank initBank(Bank bank) throws IOException {
		File file = new File("src/system/bank.txt");
		FileReader in = new FileReader(file);
		BufferedReader reader = new BufferedReader(in);
		String line = null;
		int flag = 0;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
			if(line.equals("用户")) {
				flag = 1;
				continue;
			}else if(line.equals("前台")) {
				flag = 2;
				continue;
			}else if(line.equals("经理")) {
				flag = 3;
				continue;
			}
			if((flag == 1)&&(!line.equals(""))) {
				String[] content = line.split("\t");
				User u = new User(content[0].split(":")[1], content[1].split(":")[1]);
				bank.SetUser.add(u);
				double money = Double.parseDouble(content[2].split(":")[1]);
				bank.UToMoney.put(u, money);
			}else if((flag == 2)&&(!line.equals(""))) {
				String[] content = line.split("\t");
				Worker w = new Worker(content[0].split(":")[1], content[1].split(":")[1],content[2].split(":")[1]);
				bank.SetWorker.add(w);
			}else if((flag == 3)&&(!line.equals(""))) {
				String[] content = line.split("\t");
				bank.manager = new Manager(content[0].split(":")[1], content[1].split(":")[1]);
			}
		}
		return bank;
	}
	
	public Set<User> getUsers(){
		return this.SetUser;
	}
	public Map<User,Double> getMoneySystem() {
		return this.UToMoney;
	}
	
	public User UserLogin(String name,String password) {
		Iterator<User> it = SetUser.iterator(); 
		while(it.hasNext()) {
			User u = it.next();
			if(u.getName().equals(name)&&u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
	public Worker WorkLogin(String name,String password) {
		Iterator<Worker> it = SetWorker.iterator(); 
		while(it.hasNext()) {
			Worker w = it.next();
			if(w.getName().equals(name)&&w.getPassword().equals(password)) {
				return w;
			}
		}
		return null;
	}
	
	public Manager BossLogin(String name,String password) {
		
		if(manager.getName().equals(name)&&manager.getPasswd().equals(password)) {
			return manager;
		}
		return null;
	}
//	public boolean ManageLogin(String name,String password) {
//		Iterator<User> it = SetUser.iterator(); 
//		while(it.hasNext()) {
//			User u = it.next();
//			if(u.getName().equals(name)&&u.getPassword().equals(password)) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	public static void main(String[] args) throws IOException {
		Bank bank = new Bank();
		bank.initBank(bank);
		System.out.println(bank.UToMoney);
		System.out.println(bank.getMoneySystem());
	}

}
