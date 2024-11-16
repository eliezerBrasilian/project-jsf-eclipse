package app.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import app.entities.UserEntity;
import app.repositories.UserRepository;

import javax.faces.bean.ViewScoped;

import lua.reader.jsftemplateapp.LuaReader;

@ViewScoped
@ManagedBean(name = "appBean")
public class AppBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String appVersion;
	private LuaReader luaReader;
	//private UserRepository userRepository = new UserRepository();
	
	private int numberCount = 0;
	
	public AppBean() {
		 System.out.println("application started");
		
		 
		 try {
			 luaReader = LuaReader.getInstance();
			 System.out.println(luaReader.appVersion);
		     System.out.println(luaReader.dependenciesMap);
		     setAppVersion(luaReader.appVersion);
			 
		 }catch(Exception e) {
			 System.out.println("erro: " + e.getMessage());
		 }
	}


	public void incrementCounter(ActionEvent event) {
		numberCount ++;
		
		try {
			UserRepository userRepository = new UserRepository();
			
			List<UserEntity> users = userRepository.findAll();
			
			users.forEach(i->System.out.println(i.getEmail()));
			
		}catch(Exception e) {
			 System.out.println("erro: " + e.getMessage());
		 }
			
	}
	
	public void decrementCounter(ActionEvent event) {
		numberCount --;
	}
	
	public int getNumberCount() {
		return numberCount;
	}

	public void setNumberCount(int numberCount) {
		this.numberCount = numberCount;
	}


	public String getAppVersion() {
		return appVersion;
	}


	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
}


