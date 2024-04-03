package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.entity.Blog;
import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogService {

	ResponseEntity<ResponseStructure<BlogResponse>> createBlogByUserId(int userId, BlogRequest blogRequest);

	ResponseEntity<ResponseStructure<BlogResponse>> findBlogByBlogId(int blogId);

	
//	ResponseEntity<ResponseStructure<BlogResponse>> updateBlogByBlogId(int blogId, BlogRequest blogRequest);


	boolean titleAvailability(String title);

	ResponseEntity<ResponseStructure<BlogResponse>> updateBlogByUserId(int blogId, BlogRequest blogRequest);

	


}
