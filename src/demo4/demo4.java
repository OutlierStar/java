package demo4;

public class demo4 {
    public static void main(String[] args) {
        WeatherStation ws = new WeatherStation();
        HotListener hot = new HotListener();
        ColdListener cold = new ColdListener();
        ws.addListener(hot);
        ws.addListener(cold);

        ws.setTemperature(40);
        ws.setTemperature(22);
        ws.setTemperature(4);

        ws.addListener(cold);
    }
}
