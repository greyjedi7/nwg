package com.example.demo.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;
	private String name;
	private String mail;


	private String password;


	private Double balance;

	public User(int userid, String name, String mail, String password,  Double balance) {
		this.userid = userid;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.balance = balance;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "Registration{" +
				"userid=" + userid +
				", name='" + name + '\'' +
				", mail='" + mail + '\'' +
				", password='" + password + '\'' +
				", balance=" + balance +
				'}';
	}

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
	
	public User()
	{}
	
	public User(int userid, String name, String mail, String password)
	{
		super();
		this.userid=userid;
		this.name=name;
		this.mail=mail;
		this.password=password;
	}

}
