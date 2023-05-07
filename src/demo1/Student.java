package demo1;

public class Student {
    private String id;
    private String name;
    private float YuWenScore;
    private float ShuXueScore;
    private float YingYuScore;
    private float AllScore;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getYuWenScore() {
        return YuWenScore;
    }
    public void setYuWenScore(float yuWenScore) {
        YuWenScore = yuWenScore;
    }
    public float getShuXueScore() {
        return ShuXueScore;
    }
    public void setShuXueScore(float shuXueScore) {
        ShuXueScore = shuXueScore;
    }
    public float getYingYuScore() {
        return YingYuScore;
    }
    public void setYingYuScore(float yingYuScore) {
        YingYuScore = yingYuScore;
    }
    public float getAllScore() {
        return AllScore;
    }
    public void setAllScore(float allScore) {
        AllScore = allScore;
    }
    public void countAllScore() {
        this.AllScore=this.ShuXueScore+this.YuWenScore+this.YingYuScore;
    }


}
