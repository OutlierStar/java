package demo11.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private BusManager busManager;
    private StationManager stationManager;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void init() {
        busManager = new BusManager();
        Bus bus = new Bus("1路", "1站", "正在行驶");
        Bus bus1 = new Bus("2路", "1站", "正在行驶");
        Bus bus2 = new Bus("2路", "1站", "已到站");
        busManager.addBus(bus);
        busManager.addBus(bus1);
        busManager.addBus(bus2);

    }


    @Override
    public void run() {
        try {
            // 获取输入流和输出流
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            init();
            // 处理客户端请求
            String request = in.readLine();
            String response = handleRequest(request);

            // 向客户端发送响应数据
            out.println(response);

            // 关闭输入流、输出流和 Socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String handleRequest(String request) {
        if (request.equals("GET_ALL_LINES")) { // 请求所有公交线路信息：
            // 获取所有公交线路信息
            // TODO: 实现获取逻辑
            String res = "";
            for (Bus bus : busManager.getBuses()) {
                res+=" " + bus.getId();
            }

            return res;
        }

        String[] parts = request.split(",");
        if (parts.length == 2 && parts[0].equals("GET_STATION_LINES")) { // 请求某个站台的公交线路信息：
            String stationId = parts[1];
            // 获取指定站台的公交线路信息
            // TODO: 实现获取逻辑
            return "1路,2路";
        }
        // String[] parts = request.split(",");
        if (parts.length == 2 && parts[0].equals("GET_STATION_BUSES")) { // 请求某个站台的所有公交巴士状态：
            String stationId = parts[1];
            // 获取指定站台的所有公交巴士状态
            // TODO: 实现获取逻辑
            return "[{\"id\":\"1\",\"position\":\"1站\",\"status\":\"正在行驶\"},{\"id\":\"2\",\"position\":\"2站\",\"status\":\"已到站\"}]";
        }

        return "Invalid request.";
    }

}
