package com.example.cms.responsedto;

public class BlogPostResponse {

	private int blogId;
	private String title;
	private String subTitle;
	private String summary;
	private String postType;
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
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public BlogPostResponse(int blogId, String title, String subTitle, String summary, String postType) {
		
		this.blogId = blogId;
		this.title = title;
		this.subTitle = subTitle;
		this.summary = summary;
		this.postType = postType;
	}
	
}
