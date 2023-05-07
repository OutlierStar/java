package demo6;

public class Document {
    CareTaker careTaker = new CareTaker();
    private Text content = new Text();
    private String title;

    public Document(String title) {
        this.title = title;
    }

    public void edit(String content) {
        setContent(content);
    }

    public void setContent(String content) {
        this.content.setState(content);
    }

    public void backup() {
        careTaker.add(this.content.saveStateToMemento());
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content.getState();
    }

    public String getTitle() {
        return title;
    }

    public void restore(int i) {
        if(i-1>0)
            content.getStateFromMemento(careTaker.get(i-1));
    }
}
