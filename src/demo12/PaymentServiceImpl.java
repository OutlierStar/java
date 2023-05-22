package demo12;

import java.util.Date;

import javax.jws.WebService;

@WebService(endpointInterface = "demo12.PaymentService")
public class PaymentServiceImpl implements PaymentService {
    @Override
    public OrderInfo getOrderInfo(int orderId) {
        // 从数据库或其他数据源中获取订单信息
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        orderInfo.setOrderAmount(100.0);
        orderInfo.setOrderTime(new Date());
        orderInfo.setMerchantName("Example Merchant");
        orderInfo.setCustomerName("Example Customer");
        return orderInfo;
    }
 
    @Override
    public boolean confirmPayment(int orderId, String paymentPassword) {
        // 核实客户与商家信息，要求客户输入支付密码以确认支付订单
        // 返回 true 表示支付订单成功，返回 false 表示支付订单失败
        return true;
    }
 
    @Override
    public PaymentResult pay(int orderId) {
        // 完成订单交易，返回一个表示交易结果的 PaymentResult 对象
        PaymentResult paymentResult = new PaymentResult();
        paymentResult.setOrderId(orderId);
        paymentResult.setPaymentTime(new Date());
        paymentResult.setPaymentAmount(100.0);
        paymentResult.setPaymentStatus("SUCCESS");
        return paymentResult;
    }
}
