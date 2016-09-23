package ca.com.evermal.model;

public class SignificativeSampleComment {

	private long processedCommentId;
	private String projectName;
	private String text;
	private String classification;
	private String reviewer;
	private String reviewerClassification;	
	private Boolean reviewed;
	

	public Boolean getReviewed() {
		return reviewed;
	}

	public void setReviewed(Boolean reviewed) {
		this.reviewed = reviewed;
	}

	public void setProcessedCommentId(long processedCommentId) {
		this.processedCommentId = processedCommentId;
	}

	public long getProcessedCommentId() {
		return this.processedCommentId;
	}
	
	public void setClassification(String classification){
		this.classification = classification;
	}
	
	public String getClassification() {
		return this.classification;
	}
	
		public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
		
	}
	
	public String getProjectName(){
		return projectName;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getReviewerClassification() {
		return reviewerClassification;
	}

	public void setReviewerClassification(String reviewerClassification) {
		this.reviewerClassification = reviewerClassification;
	}
}