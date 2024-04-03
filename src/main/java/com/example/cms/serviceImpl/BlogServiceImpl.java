package com.example.cms.serviceImpl;

import java.util.Arrays;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Blog;
import com.example.cms.entity.User;
import com.example.cms.exception.BlogNotFoundByIdException;
import com.example.cms.exception.TitleNotAvailableException;
import com.example.cms.exception.TopicNotSpecifiedException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogRepository blogRepository;

	private UserRepository userRepository;

	private ResponseStructure<BlogResponse> responseStructure;



	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> createBlogByUserId(int userId, BlogRequest blogRequest) {

		 return userRepository.findById(userId).map(user->{
			if(blogRepository.existsByTitle(blogRequest.getTitle()))
				throw new TitleNotAvailableException("Failed to create blog");

			if(blogRequest.getTopics().length<1)
				throw new TopicNotSpecifiedException("Failed to create blog");

			Blog blog = mapToBlogEntity(blogRequest, new Blog());
			blog.setUser((User) Arrays.asList(user));
			blogRepository.save(blog);

			return  ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("blog created")
					.setBody(mapToBlogResponse(blog))
					);
		}).orElseThrow(()->new UserNotFoundByIdException("Failed to create blog"));		
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogByBlogId(int blogId) {

		return blogRepository.findById(blogId).map(blog->{
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.FOUND.value())
					.setMessage("Blog found by blogid").setBody(mapToBlogResponse(blogRepository.save(blog))));
		}) .orElseThrow(()-> new BlogNotFoundByIdException("blog not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlogByUserId(int blogId,BlogRequest blogRequest) {

		return blogRepository.findById(blogId).map(blog->{
			if(blogRequest.getTopics().length < 1)
				throw new TopicNotSpecifiedException("Failed to update blog");
			Blog blog1 = mapToBlogEntity(blogRequest, blog);
			blog1.setUser(blog.getUser());

			blogRepository.save(blog1);

			return  ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("Blog updates successfully")
					.setBody(mapToBlogResponse(blog1)));
		}).orElseThrow(()->new BlogNotFoundByIdException("blog is not updated"));
	}



	private BlogResponse mapToBlogResponse(Blog blog) {

		return new BlogResponse(blog.getBlogId(),
				blog.getTitle(),
				blog.getTopics(),
				blog.getSummary());
	}
	
     @Override
	public boolean titleAvailability(String title) {
		return blogRepository.existsByTitle(title);
	}

	private Blog mapToBlogEntity(BlogRequest blogRequest,Blog blog) {
	blog.setTitle(blogRequest.getTitle());
	blog.setTopics(blogRequest.getTopics());
	blog.setSummary(blogRequest.getSummary());
	return blog;
	
}

}
