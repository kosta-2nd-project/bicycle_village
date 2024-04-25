package group2.bicycle_village.common.dto;

public class AlarmDTO {
    private String alarmContent;
    private int isSeen;
    private String linkURL;

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
}
