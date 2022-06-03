package com.contin.assg.conroller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contin.assg.calc.model.UserPostCommentsEmail;
import com.contin.assg.calc.model.UserSetPostEmails;
import com.contin.assg.service.CalcServiceImpl;
import com.contin.assg.web.model.Album;
import com.contin.assg.web.model.Post;
import com.contin.assg.web.model.Todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE)
public class JSONPlaceholderController {

	private final CalcServiceImpl postService;
	
	@RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
	public ResponseEntity<Post> getPost(@PathVariable("postId") int postId) {
		log.info("getPost start postId {}", postId);
		Post post = postService.getPost(postId);
		log.info("getPost end");
		return ResponseEntity.ok(post);
	}
	
	@RequestMapping(value = "/summary_tasks_per_user", method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, Long>> summaryForEachUser() {
		log.info("summaryForEachUser start ");
		Map<Integer, Long> userTodoList = postService.calcUserTodoSummary();
		log.info("summaryForEachUser end");
		return ResponseEntity.ok(userTodoList);
	}
	
	@RequestMapping(value = "/tasks_for_special_user", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> uncompletedTasksForUser( @RequestParam int userId) {
		log.info("uncompleteTasksForUser start {}",userId);
		 List<Todo> userTodos = postService.getUserTodos(userId);
		log.info("uncompleteTasksForUser end");
		return ResponseEntity.ok(userTodos);
	}
	
	@RequestMapping(value = "/summary_emails_per_user_post", method = RequestMethod.GET)
	public ResponseEntity<List<UserSetPostEmails>> summaryUserPostCommentsEmail() {
		log.info("summaryForEachUser start ");
		List<UserSetPostEmails> userPostEmailSummary = postService.calcUserPostCommentsEmail();
		log.info("summaryForEachUser end");
		return ResponseEntity.ok(userPostEmailSummary);
	}
	
	@RequestMapping(value = "/summary_albums_per_user", method = RequestMethod.GET)
	public ResponseEntity<List<Album>> summaryUserAlbums(@RequestParam int userId, @RequestParam int threshold) {
		log.info("summaryUserAlboms start {},{}",userId,threshold);
		List<Album> userPostEmailSummary = postService.summaryUserAlbums(userId, threshold);
		log.info("summaryForEachUser end");
		return ResponseEntity.ok(userPostEmailSummary);
	}



}
