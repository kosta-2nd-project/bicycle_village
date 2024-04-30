package group2.bicycle_village.common.dto;

public class BoardDTO {
    private int boardSeq, isSeen, boardCount, goodsPrice, productSeq, userSeq;
    private String boardName, regDate, category, boardContent, boardAddr, boardEdit;
    
    private UserDTO userDTO;
    
    public BoardDTO() {}

    public BoardDTO(int boardSeq, String boardEdit, int boardCount, int goodsPrice, int productSeq, int userSeq,
                    String boardName, String regDate, String category, int isSeen, String boardContent, String boardAddr) {
        super();
        this.boardSeq = boardSeq;
        this.boardEdit = boardEdit;
        this.boardCount = boardCount;
        this.goodsPrice = goodsPrice;
        this.productSeq = productSeq;
        this.userSeq = userSeq;
        this.boardName = boardName;
        this.regDate = regDate;
        this.category = category;
        this.isSeen = isSeen;
        this.boardContent = boardContent;
        this.boardAddr = boardAddr;
    }
    
	public int getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    public String getBoardEdit() {
        return boardEdit;
    }

    public void setBoardEdit(String boardEdit) {
        this.boardEdit = boardEdit;
    }

    public int getBoardCount() {
        return boardCount;
    }

    public void setBoardCount(int boardCount) {
        this.boardCount = boardCount;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(int productSeq) {
        this.productSeq = productSeq;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(int isSeen) {
        this.isSeen = isSeen;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardAddr() {
        return boardAddr;
    }

    public void setBoardAddr(String boardAddr) {
        this.boardAddr = boardAddr;
    }

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

   
}