package group2.bicycle_village.common.dto;

public class BoardFileDTO {
	private int bFileSeq, boardSeq, saveNumber;
	private String imageName;
	
	public BoardFileDTO() {}
	
	public BoardFileDTO(int bFileSeq, int boardSeq, int saveNumber, String imageName) {
		super();
		this.bFileSeq = bFileSeq;
		this.boardSeq = boardSeq;
		this.saveNumber = saveNumber;
		this.imageName = imageName;
	}

	public int getbFileSeq() {
		return bFileSeq;
	}

	public void setbFileSeq(int bFileSeq) {
		this.bFileSeq = bFileSeq;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public int getSaveNumber() {
		return saveNumber;
	}

	public void setSaveNumber(int saveNumber) {
		this.saveNumber = saveNumber;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
}
