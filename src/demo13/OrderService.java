package demo13;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface OrderService {
    @WebMethod
    public Order getOrder(@WebParam(name = "orderId") String orderId);
}
