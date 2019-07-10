package com.javacodegeeks.enterprise.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.javacodegeeks.enterprise.product.Product;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 20)
public class CartBean implements Cart {

	@PersistenceContext(unitName = "pu", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	private List<Product> products;

	@PostConstruct
	private void initializeBean() {
		products = new ArrayList<Product>();
	}

	@Override
	public void addProductToCart(Product product) {
		products.add(product);

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void checkOut() {
		for (Product product : products) {
			entityManager.persist(product);
		}
		products.clear();

	}
}
