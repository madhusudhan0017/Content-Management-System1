package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

@RestController
public class BlogController {

	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		
		this.blogService = blogService;
	}

	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlogByUserId
	(@RequestParam int userId,@RequestBody BlogRequest blogRequest){
		return blogService.createBlogByUserId(userId, blogRequest);
	}
	
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> findByBlogByBlogId
	(@RequestParam int userId,@PathVariable int blogId){
		return blogService.findBlogByBlogId(blogId);
	}
	
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogByUserId(@RequestParam int blogId, @RequestBody BlogRequest blogRequest){
		return blogService.updateBlogByUserId(blogId, blogRequest);
	}
	
	@GetMapping("/titles/{title}/blogs")
	public boolean titleAvailability(@PathVariable String title) {
		return blogService.titleAvailability(title);
	}
}
