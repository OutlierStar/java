package demo11.server;

public class Bus {
    private String id;
    private String position;
    private String status;

    public Bus(String id, String position, String status) {
        this.id = id;
        this.position = position;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
