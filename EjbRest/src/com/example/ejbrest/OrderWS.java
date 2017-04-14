package com.example.ejbrest;

import java.io.StringWriter;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

@Path("/order")
@Stateless
@LocalBean
public class OrderWS {

	@EJB
	private OrderDAO orderDao;
	
	@POST
	@Consumes({"application/json"})
	@javax.ws.rs.Produces({"application/json"})
    public OrderResponse addOrder(Order order) throws Exception {
		ClientResponse<OrderResponse> response ;
		try {
			orderDao.addOrder(order);
			OrderSalesPortal os = new OrderSalesPortal();
		    os.setCnpj("1231241241241");
		    os.setCustomerPo(order.getCustomerPo());
		    os.setId(order.getId());
		     
		    StringWriter writer = new StringWriter();
		    JAXBContext jaxbContext = JAXBContext.newInstance(OrderSalesPortal.class);
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    jaxbMarshaller.marshal(os, writer);
		     
		    //Define the API URI where API will be accessed
		    ClientRequest request = new ClientRequest("http://localhost:8080/EjbRest/orderSalesPortal");
		     
		    //Set the accept header to tell the accepted response format
		    request.body("application/xml", writer.getBuffer().toString());
		     
		    //Send the request
		    response = request.post(OrderResponse.class);
		     
		    //First validate the api status code
		    int apiResponseCode = response.getResponseStatus().getStatusCode();
//		    if(response.getResponseStatus().getStatusCode() != 201)
//		    {
//		        throw new RuntimeException("Failed with HTTP error code : " + apiResponseCode);
//		    }
			     
			    //Get the user object from entity
			    //OrderResponse user = response.getEntity();
			     
			    //verify the user object
			    //System.out.println(user.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		OrderResponse r = new OrderResponse();
		r.setMessage("SUCESSO");
		r.setCode(order.getId() + "");
		return response.getEntity();
    }
	
}
