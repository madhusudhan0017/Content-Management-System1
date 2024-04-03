package com.example.cms.responsedto;

public class BlogResponse {

	private int blogId;
	private String title;
	private String[] topics;
	private String summary;
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
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
	public BlogResponse(int blogId, String title, String[] topics, String summary) {
		super();
		this.blogId = blogId;
		this.title = title;
		this.topics = topics;
		this.summary = summary;
	}
	
	
}
