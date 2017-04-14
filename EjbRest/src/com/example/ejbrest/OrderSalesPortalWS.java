package com.example.ejbrest;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/orderSalesPortal")
@Stateless
@LocalBean
public class OrderSalesPortalWS {

	
	@POST
	@Consumes({"application/xml"})
	@javax.ws.rs.Produces({"application/xml"})
    public OrderResponse addOrderSalesPortal(OrderSalesPortal order) throws Exception {
		try {
			System.out.println(order.getCnpj());
			order.setId(1234);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		OrderResponse r = new OrderResponse();
		r.setMessage("SUCESSO SALES PORTAL");
		r.setCode(order.getId() + "");
		return r;
    }
	
}
