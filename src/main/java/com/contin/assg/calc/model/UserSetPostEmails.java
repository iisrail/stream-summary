package com.contin.assg.calc.model;

import java.util.Set;

import lombok.Data;

@Data
public class UserSetPostEmails {
	private int userId;	
	private Set<PostEmails> postEmails;
	
	public UserSetPostEmails(int userId, Set<PostEmails> postEmails) {
		this.userId = userId;
		this.postEmails = postEmails;
	}
	
}
