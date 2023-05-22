package demo12;

import javax.jws.WebService;

@WebService
public interface PaymentService {
    public OrderInfo getOrderInfo(int orderId);
    public boolean confirmPayment(int orderId, String paymentPassword);
    public PaymentResult pay(int orderId);
}

