package com.company.chat.main;

import com.company.chat.main.dao.ProductDao;
import com.company.chat.main.model.Product;
import com.company.chat.main.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SlackApplicationTests {

	ProductService prod;
	ProductDao dao;

	@BeforeEach
	void setup(){
		dao =  Mockito.mock(ProductDao.class);
		prod = new ProductService(dao);
	}

	@Test
	void whenTheListOfProductsAreEmpty(){
		Assert.assertTrue(prod.listProducts().isEmpty());
	}

	@Test
	void whenInsertNewProduct() {
		Product newProduct = prod.insertNewProduct("new", "new","url");
		Assert.assertEquals(newProduct.getName(), "new");
	}

	@Test
	void whenGetProductsList(){
		Mockito.when(dao.list()).thenReturn(products());

		List<Product> products = prod.listProducts();
		Assert.assertTrue(products.toArray().length ==1);
	}

	@Test
	void WhenGetProduct(){
		Product guy = products().get(0);
		Mockito.when(dao.getProductById(Mockito.anyInt())).thenReturn(guy);

		Product product = prod.getProductById(1212);

//		Mockito.when(dao.findProductById(Mockito.anyInt())).thenThrow(RuntimeException.class);
//		Mockito.verifyNoInteractions(dao.findProductById(Mockito.anyInt()));
//		try {
//			Mockito.verifyNoInteractions(dao.findProductById(Mockito.anyInt()));
//		}catch (Exception e){}

		Assert.assertTrue(product.getId() == 1212);
	}

    private List<Product> products() {
		List products = new ArrayList<>();
        products.add(new Product(1212,"new","new","url"));
		return products;
	}

}
