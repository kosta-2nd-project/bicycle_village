package group2.bicycle_village.common.dto;

import java.util.Date;

public class MessageEntity {
    private int messageSeq;
    private int roomId;
    private String userId;
    private String mesage;
    private Date createAt;

    public MessageEntity(Builder builder) {
        this.messageSeq =builder.messageSeq;
        this.roomId = builder.roomId;
        this.userId = builder.userId;
        this.mesage = builder.mesage;
        this.createAt = builder.createAt;
    }

    public static final class Builder {
        private int messageSeq;
        private int roomId;
        private String userId;
        private String mesage;
        private Date createAt;

        private Builder() {
        }

        public static Builder aMessageEntity() {
            return new Builder();
        }

        public Builder messageSeq(int messageSeq) {
            this.messageSeq = messageSeq;
            return this;
        }

        public Builder roomId(int roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder mesage(String mesage) {
            this.mesage = mesage;
            return this;
        }

        public Builder createAt(Date createAt) {
            this.createAt = createAt;
            return this;
        }

        public MessageEntity build() {

            return new MessageEntity(this);
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
}
