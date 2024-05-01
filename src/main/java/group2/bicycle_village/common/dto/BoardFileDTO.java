package group2.bicycle_village.common.dto;

public class BoardFileDTO {
	private long bFileSeq, boardSeq;
	int saveNumber;
	private String imageName;
	
	public BoardFileDTO() {}

	public BoardFileDTO(long boardSeq, int saveNumber, String imageName) {
		super();
		this.boardSeq = boardSeq;
		this.saveNumber = saveNumber;
		this.imageName = imageName;
	}

	public BoardFileDTO(int bFileSeq, int boardSeq, int saveNumber, String imageName) {
		super();
		this.bFileSeq = bFileSeq;
		this.boardSeq = boardSeq;
		this.saveNumber = saveNumber;
		this.imageName = imageName;
	}

	public long getbFileSeq() {
		return bFileSeq;
	}

	public void setbFileSeq(long bFileSeq) {
		this.bFileSeq = bFileSeq;
	}

	public long getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(long boardSeq) {
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
