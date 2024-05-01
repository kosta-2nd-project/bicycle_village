package group2.bicycle_village.common.dto;

import java.util.Date;

public class ChattEntity {
    private int chattSeq;
    private String sellerId;
    private String buyerId;
    private String roomUrl;

    private String content;
    private Date roomCreateDate;


    public static final class ChattEntityBuilder {
        private int chattSeq;
        private String sellerId;
        private String buyerId;
        private String roomUrl;
        private String content;
        private Date roomCreateDate;


        private ChattEntityBuilder() {
        }

        public static ChattEntityBuilder ChattEntity() {
            return new ChattEntityBuilder();
        }

        public ChattEntityBuilder chattSeq(int chattSeq) {
            this.chattSeq = chattSeq;
            return this;
        }

        public ChattEntityBuilder sellerId(String sellerId) {
            this.sellerId = sellerId;
            return this;
        }

        public ChattEntityBuilder buyerId(String buyerId) {
            this.buyerId = buyerId;
            return this;
        }

        public ChattEntityBuilder roomUrl(String roomUrl) {
            this.roomUrl = roomUrl;
            return this;
        }

        public ChattEntityBuilder content(String content) {
            this.content = content;
            return this;
        }
        public ChattEntityBuilder  roomCreateDate(Date roomCreateDate){
            this.roomCreateDate= roomCreateDate;
            return this;
        }

        public ChattEntity build() {
            ChattEntity chattEntity = new ChattEntity();
            chattEntity.sellerId = this.sellerId;
            chattEntity.roomUrl = this.roomUrl;
            chattEntity.chattSeq = this.chattSeq;
            chattEntity.content = this.content;
            chattEntity.buyerId = this.buyerId;
            chattEntity.roomCreateDate= this.roomCreateDate;
            return chattEntity;
        }
    }



}
