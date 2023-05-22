package demo13;

import javax.jws.WebService;

@WebService(endpointInterface = "demo13.OrderService")
public class OrderServiceImpl implements OrderService {

    @Override
    public Order getOrder(String orderId) {
        // 从数据库或其他数据源获取订单信息
        // 这里为了示例，我们直接构造一个订单对象并返回
        Order order = new Order();
        order.setId(orderId);
        order.setAmount(100.0);
        order.setDescription("购买商品");
        order.setSeller("商家A");
        order.setBuyer("张三");
        return order;
    }
}
