package com.example.ejbrest;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class OrderDAO {
	
	@PersistenceContext
    private EntityManager em;
    
    public Order getCustomer(int id) {
        return em.find(Order.class, id);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addOrder(Order order) {
    	List<Item> itens = order.getItens();
    	for (Item item : itens) {
			item.setOrder(order);
		}
        em.persist(order);
    }
    

}
