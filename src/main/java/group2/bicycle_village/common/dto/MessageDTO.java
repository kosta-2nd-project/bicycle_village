package group2.bicycle_village.common.dto;

import java.util.Date;

public class MessageDTO {
    private int messageSeq;
    private int roomId;
    private String userId;
    private String mesage;
    private Date createAt;

    public MessageDTO(int messageSeq, int roomId, String userId, String mesage, Date createAt) {
        super();
        this.messageSeq = messageSeq;
        this.roomId = roomId;
        this.userId = userId;
        this.mesage = mesage;
        this.createAt = createAt;
    }

    public int getMessageSeq() {
        return messageSeq;
    }

    public void setMessageSeq(int messageSeq) {
        this.messageSeq = messageSeq;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
