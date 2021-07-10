package com.app.product.main;

import com.app.product.main.dao.ProductDao;
import com.app.product.main.model.Product;
import com.app.product.main.service.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;

@SpringBootTest
class ProductServiceTests {

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
	void whenAddNewProduct_ShouldCheckIfProductWasSaved() {
		given()
			.productProvider(product -> {
				product.id = 12;
				product.name = "123";
				product.description = "123";
				product.url = "http://url.com";
			})
		.whenPerformSaveProduct()
		.then()
			.checkWhoseProductWasSaved();
	}

	@Test
	void whenGetTheProductsList_ShouldReturnAListOfProducts(){
		given()
		.whenPerformListProducts()
		.then()
			.checkTheProductList();
	}

	@Test
	void whenGetTheProduct_ShouldReturnTheProduct(){
		given()
		.whenGetProductById(12)
		.then()
			.checkProductById(12);
	}

	private DSL given(){
		return new DSL();
	}
	private static class DSL {

		ProductDao dao =  Mockito.mock(ProductDao.class);
		ProductService service;
		ProductProviderData prodData;

		public DSL() {
			service = new ProductService(dao);
			prodData = new ProductProviderData();
		}

		public DSL productProvider(Consumer <ProductProviderData> product) {
			product.accept(prodData);
			return this;
		}

		public ThenDSL whenPerformSaveProduct() {
			Product newProd = new Product(prodData.id,prodData.name,prodData.description,prodData.url);
			service.insertNewProduct(newProd);
			return new ThenDSL();
		}

		public ThenDSL whenPerformListProducts() {
			service.listProducts();
			return new ThenDSL();
		}

		public ThenDSL whenGetProductById(int productId) {
			service.getProductById(productId);
			return new ThenDSL();
		}

		public class ThenDSL {


			public ThenDSL then() {
				return this;
			}

			public void checkWhoseProductWasSaved() {
				Mockito.verify(dao).save(Mockito.any());
			}

			public void checkTheProductList() {
				Mockito.verify(dao).list();
			}

			public void checkProductById(int productId) {
				Mockito.verify(dao).getProductById(productId);
			}
		}
	}

	private static class ProductProviderData {
		public int id;
		public  String name;
		public  String description;
		public  String url;
	}


}