package com.FeedBack;

public class FeedBackDetails {
	private int feedbackId;
	private String longText;
	private int ratings;
	private int customerId;
	public FeedBackDetails( String longText, int ratings,int customerId) {
		
		//this.feedbackId = feedbackId;
		this.longText = longText;
		this.ratings = ratings;
		this.customerId=customerId;
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getLongText() {
		return longText;
	}
	public void setLongText(String longText) {
		this.longText = longText;
	}
	public int getRatings() {
		return ratings;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
}
