package demo9;

import java.io.IOException;

// 主程序类
public class Main {
    public static void main(String[] args) {
        // 创建数据处理程序对象
        DataProcessor processor = new DataProcessor("./src/demo9/input.txt", 1, 3);

        try {
            // 运行过滤器处理函数
            processor.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}