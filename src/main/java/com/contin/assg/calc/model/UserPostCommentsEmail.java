package com.contin.assg.calc.model;

import lombok.Data;

@Data
public class UserPostCommentsEmail {
	private int userId;	
	private PostEmails postEmails;
	
	public UserPostCommentsEmail(int userId, PostEmails postEmails) {
		this.userId = userId;
		this.postEmails = postEmails;
	}
	
}
