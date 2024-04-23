package uz.pdp.modul;

public class User {
    private long chatId;
    private String fullName;
    private String phoneNumber;
    private String lang;
    private Role role;
    private BotState state;

    public User() {
    }

    public User(long chatId, BotState state) {
        this.chatId = chatId;
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "chatId=" + chatId +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", lang='" + lang + '\'' +
                ", role=" + role +
                ", state=" + state +
                '}';
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BotState getState() {
        return state;
    }

    public void setState(BotState state) {
        this.state = state;
    }
}
