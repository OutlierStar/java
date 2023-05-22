package demo11.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationManager {
    private Map<String, Station> stations;

    public StationManager() {
        this.stations = new HashMap<>();
    }

    public void addStation(Station station) {
        stations.put(station.getId(), station);
    }

    public void removeStation(Station station) {
        stations.remove(station.getId());
    }

    public void updateStation(Station station) {
        stations.put(station.getId(), station);
    }

    public Map<String, Station> getStations() {
        return stations;
    }
}