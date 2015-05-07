package ca.com.evermal.model;

public class Comment {

	private static int LINE_CORRECTION = 1;

	private long id;
	private long classCommentId;
	private int startLine;
	private int endLine;
	private String text;
	private String type;
	private String location;
	private String description;
	private int dictionaryHit;
	private int jdeodorantHit;
	private String refactoringListName;
	private String classification;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}
	
	public void setClassification(String classification){
		this.classification = classification;
	}
	
	public String getClassification() {
		return this.classification;
	}
	
	public void setClassCommentId(long classCommentId) {
		this.classCommentId = classCommentId;
	}

	public long getClassCommentId() {
		return this.classCommentId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine + LINE_CORRECTION;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine + LINE_CORRECTION;
	}
	
	public void setStartLineWitoutCorrection(int startLine) {
		this.startLine = startLine;
	}

	public void setEndLineWitoutCorrection(int endLine) {
		this.endLine = endLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public int getStartLine() {
		return startLine;
	}
	
	public int getJdeodorantHit() {
		return jdeodorantHit;
	}

	public void setJdeodorantHit(int jdeodorantHit) {
		this.jdeodorantHit = jdeodorantHit;
	}

	public String getRefactoringListName() {
		return refactoringListName;
	}

	public void setRefactoringListName(String refactoringListName) {
		this.refactoringListName = refactoringListName;
	}
	
	public int getDictionaryHit() {
		return dictionaryHit;
	}

	public void setDictionaryHit(int dictionaryHit) {
		this.dictionaryHit = dictionaryHit;
	}
}