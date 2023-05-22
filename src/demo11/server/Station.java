package demo11.server;

import java.util.List;

public class Station {
    private String id;
    private List<String> lines;

    public Station(String id, List<String> lines) {
        this.id = id;
        this.lines = lines;
    }
    
    public String getId() {
        return id;
    }
    
    public List<String> getLines() {
        return lines;
    }
    
}
