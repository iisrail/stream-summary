package com.contin.assg.calc.model;

import java.util.Set;

import lombok.Data;

@Data
public class PostEmails {

	private int postId;
	private Set<String> emails;
	
	public PostEmails(Integer key, Set<String> value) {
		this.postId = key;
		this.emails = value;
	}
}
