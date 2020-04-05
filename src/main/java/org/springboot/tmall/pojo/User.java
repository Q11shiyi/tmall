package org.springboot.tmall.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String password;
	private String salt;
	
	@Transient//忽略该属性,该属性是匿名,用于前端显示名字时保护用户个人信息安全
	private String anonymousName;
	
	
	
	public String getAnonymousName() {
		if(anonymousName!=null) {
			return anonymousName;
		}
		if(name==null) {
			anonymousName=null;
		}else if(name.length()<=1) {
			anonymousName="*";
		}else if(name.length()==2) {
			anonymousName=name.substring(0, 1)+"*";
		}else {
			char[] cs=name.toCharArray();
			for(int i=0;i<cs.length-1;i++) {
				cs[i]='*';
			}
			anonymousName=new String(cs);
		}
		
		return anonymousName;
	}
	public void setAnonymousName(String anonymousName) {
		this.anonymousName = anonymousName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}
