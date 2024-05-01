package group2.bicycle_village.common.dto;

import java.util.Date;

public class ChattDTO {
    private int chattSeq;
    private String sellerId;
    private String buyerId;
    private String roomUrl;

    private String content;

    private Date roomCreateDate;

    public ChattDTO(){};

    public ChattDTO(int chattSeq, String sellerId, String buyerId, String roomUrl, String content) {
        super();
        this.chattSeq = chattSeq;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.roomUrl = roomUrl;
        this.content = content;
    }

    public ChattDTO( String sellerId, String buyerId, String roomUrl, String content, Date roomCreateDate) {
        super();
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.roomUrl = roomUrl;
        this.content = content;
        this.roomCreateDate= roomCreateDate;
    }
    public ChattDTO( String sellerId, String buyerId, String roomUrl, String content ) {
        super();
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.roomUrl = roomUrl;
        this.content = content;
    }

    public int getChattSeq() {
        return chattSeq;
    }

    public void setChattSeq(int chattSeq) {
        this.chattSeq = chattSeq;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        this.roomUrl = roomUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRoomCreateDate() {
        return roomCreateDate;
    }

    public void setRoomCreateDate(Date roomCreateDate) {
        this.roomCreateDate = roomCreateDate;
    }
}
