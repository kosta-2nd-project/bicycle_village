package group2.bicycle_village.common.dto;

import java.util.Date;

public class ChatEntity {
   private int roomId;
   private String roomUrl;
   private Date createAt;
   private String sellerId;

    public ChatEntity(Builder builder) {
        this.roomId = builder.roomId;
        this.roomUrl = builder.roomUrl;
        this.createAt = builder.createAt;
        this.sellerId= builder.sellerId;
    }

    public static final class Builder {
        private int roomId;
        private String roomUrl;
        private Date createAt;
        private String sellerId;


        private Builder() {
        }

        public static Builder aChatEntity() {
            return new Builder();
        }

        public Builder roomId(int roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder roomUrl(String roomUrl) {
            this.roomUrl = roomUrl;
            return this;
        }

        public Builder createAt(Date createAt) {
            this.createAt = createAt;
            return this;
        }
        public Builder sellerId(Date createAt) {
            this.sellerId = sellerId;
            return this;
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
    }


    @Override
    public String toString() {
        return "ChatEntity{" +
                "roomId=" + roomId +
                ", roomUrl='" + roomUrl + '\'' +
                ", createAt=" + createAt +
                ", sellerId='" + sellerId + '\'' +
                '}';
    }
}
