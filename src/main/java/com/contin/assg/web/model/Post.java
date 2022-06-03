package com.contin.assg.web.model;

import lombok.Data;

/*
{
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et ..."
}
*/

@Data
public class Post {

	private int userId;
	private int id;
	private String title;
	private String body;
}
