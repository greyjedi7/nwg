package com.example.demo.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Registration {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
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
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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


