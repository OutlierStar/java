package demo1;

public class Student {
    private String id;
    private String name;
    private float YuWenScore;
    private float ShuXueScore;

    public void setShuXueScore(float shuXueScore) {
        ShuXueScore = shuXueScore;
    }

    public float getShuXueScore() {
        return ShuXueScore;
    }

    public float getYingYuScore() {
        return YingYuScore;
    }

    public void setYingYuScore(float yingYuScore) {
        YingYuScore = yingYuScore;
    }

    private float YingYuScore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getYuWenScore() {
        return YuWenScore;
    }

    public void setYuWenScore(float yuWenScore) {
        YuWenScore = yuWenScore;
    }


}
