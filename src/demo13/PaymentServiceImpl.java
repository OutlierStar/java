package demo13;

import javax.jws.WebService;

@WebService(endpointInterface = "demo13.PaymentService")
public class PaymentServiceImpl implements PaymentService {

    @Override
    public boolean payOrder(String orderId, String password) {
        // 核实客户和商家信息
        Order order = getOrder(orderId);
        if (order == null) {
            return false;
        }

        // 要求客户输入支付密码
        if (!verifyPassword(order.getBuyer(), password)) {
            return false;
        }

        // 完成订单交易
        if (!processPayment(order)) {
            return false;
        }

        // 向客户展示交易结果
        return true;
    }

    private Order getOrder(String orderId) {
        // 调用订单服务获取订单信息
        OrderService orderService = new OrderServiceImplService().getOrderServiceImplPort();
        return orderService.getOrder(orderId);
    }

    private boolean verifyPassword(String username, String password) {
        // 根据用户名和密码核实客户身份
        // 这里为了示例，我们直接将用户名和密码写死
        return "张三".equals(username) && "123456".equals(password);
    }

    private boolean processPayment(Order order) {
        // 调用第三方支付服务完成支付
        // 这里为了示例，我们直接返回支付成功
        return true;
    }
}
