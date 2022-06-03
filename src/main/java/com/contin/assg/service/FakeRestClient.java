package com.contin.assg.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contin.assg.web.model.Album;
import com.contin.assg.web.model.Comment;
import com.contin.assg.web.model.Photo;
import com.contin.assg.web.model.Post;
import com.contin.assg.web.model.Todo;

@FeignClient(name = "jplaceholder", url = "${feign.url}")
public interface FakeRestClient {
	@RequestMapping(method = RequestMethod.GET, value = "/posts/{postId}")
	public Post getPost(@PathVariable("postId") int postId);

	@RequestMapping(method = RequestMethod.GET, value = "/posts")
	List<Post> getPosts();
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos")
	List<Todo> getTodos();	
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos?completed=false")
	List<Todo> getUncompleteTodos();
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos/?completed=false")
	List<Todo> getUncompleteTodosForUser(@RequestParam int userId);
	
	@RequestMapping(method = RequestMethod.GET, value = "/comments/")
	List<Comment> getComments();
	
	@RequestMapping(method = RequestMethod.GET, value = "/albums?userId={userId}")
	List<Album> getAlbums(@RequestParam int userId);
	
	@RequestMapping(method = RequestMethod.GET, value = "/photos?albumId={albumId}")
	List<Photo> getPhotos(@RequestParam int albumId);
}
