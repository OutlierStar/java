package demo9;

import java.io.*;
import java.util.*;

// 管道-过滤器设计模式的过滤器类
public class DataProcessor {
    private final String inputFile; // 输入文件路径
    private final double threshold; // 聚集判断阈值
    private final int alertSize; // 警报人数

    // 构造函数，传入输入文件路径、聚集判断阈值和警报人数
    public DataProcessor(String inputFile, double threshold, int alertSize) {
        this.inputFile = inputFile;
        this.threshold = threshold;
        this.alertSize = alertSize;
    }

    // 过滤器处理函数
    public void run() throws IOException {
        // 读取输入文件
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        // 使用HashMap存储每个位置出现的人数
        HashMap<String, Integer> counts = new HashMap<>();

        // 逐行读取位置信息
        String line;
        while ((line = reader.readLine()) != null) {
            // 解密位置信息
            String location = decrypt(line);

            // 统计该位置出现的人数
            counts.put(location, counts.getOrDefault(location, 0) + 1);
        }

        // 关闭文件读取器
        reader.close();

        // 打印统计结果
        for (String location : counts.keySet()) {
            int count = counts.get(location);
            System.out.println("位置 " + location + " 出现 " + count + " 次");
        }

        // 判断是否有聚集现象
        for (String location : counts.keySet()) {
            int count = counts.get(location);

            // 如果该位置出现的人数超过聚集判断阈值，输出警报信息
            if (count > alertSize * threshold) {
                System.out.println("警报：位置 " + location + " 聚集人数超过 " + alertSize);
            }
        }
    }

    // 解密位置信息的函数，这里仅作演示，实际应用中应该使用更加安全的加密方式
    private String decrypt(String encrypted) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            sb.append((char)(c - 2));// 学校师生的位置信息以ASCII字符码减2为加密形式
        }
        return sb.toString();
    }
}

