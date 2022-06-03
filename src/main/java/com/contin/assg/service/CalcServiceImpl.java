package com.contin.assg.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.contin.assg.calc.model.PostEmails;
import com.contin.assg.calc.model.UserPostCommentsEmail;
import com.contin.assg.calc.model.UserSetPostEmails;
import com.contin.assg.web.model.Album;
import com.contin.assg.web.model.Comment;
import com.contin.assg.web.model.Photo;
import com.contin.assg.web.model.Post;
import com.contin.assg.web.model.Todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalcServiceImpl {
	
	private final FakeRestClient fakeClient;
	
	public Post getPost(int postId) {
		Post p = fakeClient.getPost(postId);
		return p;
	}

	public Map<Integer, Long> calcUserTodoSummary() {
		Map<Integer, Long> summaryUsers = fakeClient.getUncompleteTodos().stream()
				.collect(Collectors.groupingBy(Todo::getUserId, Collectors.counting()));

		return summaryUsers;
	}
	
	public List<Todo> getUserTodos(int userId) {
		List<Todo> todosList = fakeClient.getUncompleteTodosForUser(userId);		
		return todosList;
	}

	public List<UserSetPostEmails> calcUserPostCommentsEmail() {
		List<Post> posts = fakeClient.getPosts();
		List<Comment> comments = fakeClient.getComments();
		
		Map<Integer, Set<String>> commentsByPost = 
				comments.stream().collect(
						Collectors.groupingBy(Comment::getPostId,
							Collectors.mapping(Comment::getEmail, Collectors.toSet())
						)	
				);
		

		
		List<UserPostCommentsEmail> userPostEmails = 
				posts.stream()
				.map(p-> new UserPostCommentsEmail(
								p.getUserId(),new PostEmails(p.getId(), commentsByPost.get(p.getId()))
							)
						)
				.collect(Collectors.toList());
		
		Map<Integer, Set<PostEmails>> userSetPosts = userPostEmails.stream().collect(
				Collectors.groupingBy(UserPostCommentsEmail::getUserId,
						Collectors.mapping(UserPostCommentsEmail::getPostEmails, Collectors.toSet())
						)
				);
		
		List<UserSetPostEmails> userSetPostList = 
				userSetPosts.entrySet().stream()
				.map(entry -> new UserSetPostEmails(entry.getKey(),entry.getValue()))
				.collect(Collectors.toList());
		return userSetPostList;
	}

	public List<Album> summaryUserAlbums(int userId, int threshold) {
		List<Album> onlyBigAlbums = 
				fakeClient.getAlbums(userId)
				.stream().collect(
                Collectors.toMap(x -> x, x -> fakeClient.getPhotos(x.getId()).size()  ))
         		.entrySet().stream().filter(m-> (m.getValue()>threshold))
         		.map(Map.Entry::getKey)
         		.collect(Collectors.toList());
         		
		
	
		return onlyBigAlbums;
	}
	
}
