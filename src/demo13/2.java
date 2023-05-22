package demo13;

@WebService
public class PaymentServiceImpl implements PaymentService {

    private static final String ORDER_SERVICE_URL = "http://localhost:8080/OrderService?wsdl";

    @Override
    public boolean makePayment(String orderId, String password) throws PaymentException {
        // 获取订单信息
        OrderService orderService = createOrderService();
        Order order = orderService.getOrder(orderId);

        // 核实客户与商家信息
        if (!"Example buyer".equals(order.getBuyer())) {
            throw new PaymentException("Invalid buyer");
        }
        if (!"Example seller".equals(order.getSeller())) {
            throw new PaymentException("Invalid seller");
        }

        // 要求客户输入支付密码
        if (!"123456".equals(password)) {
            throw new PaymentException("Incorrect password");
        }

        // 完成订单交易
        // 调用第三方支付服务完成支付
        boolean result = callPaymentService(orderId);

        // 向客户展示交易结果
        if (result) {
            System.out.println("Payment successful");
        } else {
            System.out.println("Payment failed");
        }
        return result;
    }

    private OrderService createOrderService() {
        URL url;
        try {
            url = new URL(ORDER_SERVICE_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        QName qname = new QName("http://example.com/", "OrderServiceImplService");
        Service service = Service.create(url, qname);
        return service.getPort(OrderService.class);
    }

    private boolean callPaymentService(String orderId) {
        // 调用第三方支付服务完成支付
        // 返回 true 表示支付成功，返回 false 表示支付失败
        return true;
    }

}

