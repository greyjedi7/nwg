package com.example.demo.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Base64;

@Entity
public class Registration {

	@Id
	private int userid;
	private String name;
	private String mail;
	private String password;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return Base64.getDecoder().decode(password).toString();
	}
	public void setPassword(String password) {
		this.password = Base64.getEncoder().encodeToString(password.getBytes());
	}
	
	public Registration()
	{}
	
	public Registration(int userid, String name, String mail, String password)
	{
		super();
		this.userid=userid;
		this.name=name;
		this.mail=mail;
		this.password=password;
	}
	
@Override

public String toString()

{
	return "Registration[userid = " + userid + ", name=" + name + ", mail = " + mail + " password = " + password+ "]";
	
}

}


