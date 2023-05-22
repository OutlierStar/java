package demo12;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {

        String url = "http://localhost:8888/fistSoap";
        Endpoint.publish(url, new PaymentServiceImpl());
    }
}
