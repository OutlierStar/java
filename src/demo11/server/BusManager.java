package demo11.server;

import java.util.ArrayList;
import java.util.List;

public class BusManager {
    private List<Bus> buses;

    public BusManager() {
        this.buses = new ArrayList<>();
    }

    public void addBus(Bus bus) {
        buses.add(bus);
    }

    public void removeBus(Bus bus) {
        buses.remove(bus);
    }

    public void updateBus(Bus bus) {
        for (Bus b : buses) {
            if (b.getId().equals(bus.getId())) {
                b.setPosition(bus.getPosition());
                b.setStatus(bus.getStatus());
            }
        }
    }

    public List<Bus> getBuses() {
        return buses;
    }
}