package group2.bicycle_village.common.constant;

public class CommonCode {
    public enum BoardCategory{
        FREE(1), //자유게시판
        INFORMATION(2),   //정보게시판
        TRADE(3),    //거래게시판
        REPORT(4);  //신고 게시판

        private final int value;

        BoardCategory(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static BoardCategory getStatus(int value) {
            for (BoardCategory boardCategory : BoardCategory.values()) {
                if (boardCategory.value == value) {
                    return boardCategory;
                }
            }
            throw new IllegalArgumentException("No matching BoardCategory for value: " + value);
        }
    }

    public enum BoardStatus{
        AVAILABLE(1), //정상 상태
        SOLD(2),   //판매중
        DELETE(3); //삭제

        private final int value;

        BoardStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static BoardCategory getStatus(int value) {
            for (BoardCategory boardCategory : BoardCategory.values()) {
                if (boardCategory.value == value) {
                    return boardCategory;
                }
            }
            throw new IllegalArgumentException("No matching BoardCategory for value: " + value);
        }

    }
    
    public enum UserStatus{
    	AVAILABLE(0), //정상 회원
    	DROP(2); //탈퇴 회원
    	
    	private final int value;
    	
    	UserStatus(int value){
    		this.value = value;
    	}
    	
    	public int getValue() {
    		return value;
    	}
    	
    	public static UserStatus getStatus(int value) {
    		for(UserStatus userStatus : UserStatus.values()) {
    			if(userStatus.value == value) {
    				return userStatus;
    			}
    		}
    		
    		throw new IllegalArgumentException("No matching UserStatus for value : "+value);
    	}
    }
}
