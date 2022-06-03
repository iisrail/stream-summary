package com.contin.assg.web.model;

import lombok.Data;

/*
"userId": 1,
"id": 1,
"title": "delectus aut autem",
"completed": false
*/

@Data
public class Todo {
	private int userId;
	private int id;
	private String title;
	private boolean completed;
}
