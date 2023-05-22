package demo12;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
 

import javax.xml.ws.Service;

public class PaymentServiceClient {
    public static void main(String[] args) throws MalformedURLException {
		URL wsdlURL = new URL("http://localhost:8888/fistSoap?wsdl");
		//check above URL in browser, you should see WSDL file
		
		//creating QName using targetNamespace and name
		QName qname = new QName("http://demo12/", "PaymentServiceImplService"); 
		
		Service service = Service.create(wsdlURL, qname);  
		
		//We need to pass interface and model beans to client
		PaymentService paymentService = service.getPort(PaymentService.class);

        // 调用 getOrderInfo 方法，获取指定订单的订单信息
        OrderInfo orderInfo = paymentService.getOrderInfo(12345);
        System.out.println("Order Info:");
        System.out.println("Order ID: " + orderInfo.getOrderId());
        System.out.println("Order Amount: " + orderInfo.getOrderAmount());
        System.out.println("Order Time: " + orderInfo.getOrderTime());
        System.out.println("Merchant Name: " + orderInfo.getMerchantName());
        System.out.println("Customer Name: " + orderInfo.getCustomerName());
 
        // 调用 confirmPayment 方法，核实客户与商家信息，要求客户输入支付密码以确认支付订单
        boolean paymentConfirmed = paymentService.confirmPayment(12345, "123456");
        System.out.println("Payment Confirmed: " + paymentConfirmed);
 
        // 调用 pay 方法，完成订单交易，返回一个表示交易结果的 PaymentResult 对象
        PaymentResult paymentResult = paymentService.pay(12345);
        System.out.println("Payment Result:");
        System.out.println("Order ID: " + paymentResult.getOrderId());
        System.out.println("Payment Time: " + paymentResult.getPaymentTime());
        System.out.println("Payment Amount: " + paymentResult.getPaymentAmount());
        System.out.println("Payment Status: " + paymentResult.getPaymentStatus());
    }
}
