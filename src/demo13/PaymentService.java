package demo13;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface PaymentService {
    @WebMethod
    public @WebResult(name = "result") boolean payOrder(
            @WebParam(name = "orderId") String orderId,
            @WebParam(name = "password") String password);
}
