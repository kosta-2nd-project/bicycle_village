package group2.bicycle_village.common.dto;


import java.util.Date;

public class ChattDTO {
    private int roomId;
    private String roomUrl;
    private Date createAt;
    private String sellerId;


    public ChattDTO(String roomUrl, String sellerId){
        this.roomUrl = roomUrl;
        this.sellerId = sellerId;
    };



    public ChattDTO(int roomId, String roomUrl, Date date, String sellerId) {
        this.roomId = roomId;
        this.roomUrl = roomUrl;
        this.createAt = createAt;
        this.sellerId = sellerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        this.roomUrl = roomUrl;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "ChattDTO{" +
                "roomId=" + roomId +
                ", roomUrl='" + roomUrl + '\'' +
                ", createAt=" + createAt +
                ", sellerId='" + sellerId + '\'' +
                '}';
    }
}
