package com.contin.assg.web.model;

import lombok.Data;

/*
{
    "postId": 1,
    "id": 1,
    "name": "id labore ex et quam laborum",
    "email": "Eliseo@gardner.biz",
    "body": "laudantium enim ..."
}

*/
@Data
public class Comment {
	private int postId;
	private int id;
	private String name;
	private String email;
	private String body;
}
