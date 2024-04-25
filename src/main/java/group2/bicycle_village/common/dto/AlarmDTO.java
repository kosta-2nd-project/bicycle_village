package group2.bicycle_village.common.dto;

public class AlarmDTO {
    private long alarmSeq;
    private String alarmContent;
    private int isSeen;
    private String linkURL;
    UserDTO user = new UserDTO();
    private long userSeq = user.getUser_seq();

    public AlarmDTO() {
    }

    public AlarmDTO(String alarmContent, int isSeen) {
        this.alarmContent = alarmContent;
        this.isSeen = isSeen;
    }

    public AlarmDTO(String alarmContent, int isSeen, String linkURL) {
        this(alarmContent, isSeen);
        this.linkURL = linkURL;
    }

    public AlarmDTO(long alarmSeq, String alarmContent, int isSeen, String linkURL, long userSeq) {
        this.alarmSeq = alarmSeq;
        this.alarmContent = alarmContent;
        this.isSeen = isSeen;
        this.linkURL = linkURL;
        this.userSeq = userSeq;
    }

    public long getAlarmSeq() {
        return alarmSeq;
    }

    public void setAlarmSeq(long alarmSeq) {
        this.alarmSeq = alarmSeq;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public int getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(int isSeen) {
        this.isSeen = isSeen;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(long userSeq) {
        this.userSeq = userSeq;
    }
}
