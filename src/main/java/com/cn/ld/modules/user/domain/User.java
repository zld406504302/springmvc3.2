package com.cn.ld.modules.user.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.enterprise.inject.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cn.ld.base.domain.BaseLongIdDomain;

/**   
 * @filename: User   
 * @description: TODO  
 * @author lida  
 * @date 2013-7-11 下午4:46:45
 */
@Model
public class User extends BaseLongIdDomain implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
    private int age  ;
    private char sex ;
    public User(){
    	super();
    }
    public User(long id ,String name , String password , int age , char sex){
    	super.setId(id);
    	this.name = name ;
    	this.password = password ;
    	this.age = age ;
    	this.sex = sex ;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> re=new ArrayList<GrantedAuthority>();
		re.add(new SimpleGrantedAuthority("ROLE_USER"));
		return re;
	}
	
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
