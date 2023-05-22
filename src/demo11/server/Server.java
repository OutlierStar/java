package demo11.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // 创建 ServerSocket 实例，指定端口号为 8888
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("服务端已启动，等待客户端连接...");

            // 无限循环，等待客户端连接
            while (true) {
                // 接受客户端请求，创建 Socket 实例
                Socket socket = serverSocket.accept();
                // 开启一个新线程，处理客户端请求
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
