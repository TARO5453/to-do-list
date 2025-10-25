package dev.taro.webapp;

public class ToDo {
    private String title;
    private String username;
    private boolean done;
    private int id;

    public ToDo() {}
    public ToDo(int id, String username, String title, boolean done) {
        this.title = title;
        this.username = username;
        this.id = id;
        this.done = done;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
