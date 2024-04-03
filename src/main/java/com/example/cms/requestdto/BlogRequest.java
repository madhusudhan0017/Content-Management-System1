package com.example.cms.requestdto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BlogRequest {
	
   @Pattern(regexp = "(?=.* [a-z]) (?=.*[A-z])", message = "The title should only contain alphabets" ) 
	private String title;
   @NotNull
	private String[] topics;
	private String summary;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getTopics() {
		return topics;
	}
	public void setTopics(String[] topics) {
		this.topics = topics;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
