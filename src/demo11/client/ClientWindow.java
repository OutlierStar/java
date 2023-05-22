package demo11.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientWindow extends JFrame {
    private JTextArea outputTextArea;
    private JTextField inputTextField;

    public ClientWindow() {
        super("公交管理系统客户端");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        add(scrollPane, BorderLayout.CENTER);

        inputTextField = new JTextField();
        add(inputTextField, BorderLayout.SOUTH);
        outputTextArea.append("GET_ALL_LINES 获取全部车次\nGET_STATION_LINES,1 查询1站公交线路\nGET_STATION_BUSES,1 查询1站公交状态\n下方输入框输入查询指令");
        JButton sendButton = new JButton("发送请求");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String request = inputTextField.getText();
                String response = Client.sendRequest(request);
                outputTextArea.append("请求：" + request + "\n");
                outputTextArea.append("响应：" + response + "\n\n");
                inputTextField.setText("");
            }
        });
        add(sendButton, BorderLayout.NORTH);
    }

    public void run() {
        setVisible(true);
    }
}
