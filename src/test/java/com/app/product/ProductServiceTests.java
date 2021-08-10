package com.app.product;

import com.app.product.repository.ProductDao;
import com.app.product.entity.Product;
import com.app.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;

@SpringBootTest
class ProductServiceTests {

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

	@Test
	void whenRemoveTheProduct_ShouldCheckIfTheProductWasRemoved(){
		given()
		.whenPerformRemoveProduct(12)
		.then()
			.checkRemovedProductById(12);
	}

	@Test
	void whenAddAProductAlreadyIn_ShouldCheckAvoidDuplicateProduct(){
		given()
			.productAlreadyExists(product -> {
				product.id = 12;
				product.name = "123";
				product.description = "123";
				product.url = "http://url.com";
			})
			.productProvider(product -> {
				product.id = 12;
				product.name = "123";
				product.description = "123";
				product.url = "http://url.com";
			})
		.whenPerformSaveProduct()
		.then()
			.checkIfProductWasDuplicate(12);
	}

	private DSL given(){
		return new DSL();
	}
	private static class DSL {

		ProductService service;
		ProductProviderData prodData;
		ProductDao dao =  Mockito.mock(ProductDao.class);

		public DSL() {
			service = new ProductService(dao);
			prodData = new ProductProviderData();
		}

		public DSL productProvider(Consumer <ProductProviderData> product) {
			product.accept(prodData);
			return this;
		}

		public ThenDSL whenPerformSaveProduct() {
			Product newProd = new Product().add(prodData.id,prodData.name,prodData.description);
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

		public ThenDSL whenPerformRemoveProduct(int productId) {
			service.removeProductById(productId);
			return new ThenDSL();
		}

		public DSL productAlreadyExists(Consumer <ProductProviderData> product) {
			product.accept(prodData);
			Product newProd = new Product().add(prodData.id,prodData.name,prodData.description);
			Mockito.when(dao.getProductById(prodData.id)).thenReturn(newProd);
			return this;
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

			public void checkRemovedProductById(int productId) {
				Mockito.verify(dao).removeProduct(productId);
			}

			public void checkIfProductWasDuplicate(int productId){
				Mockito.verify(dao).getProductById(productId);
				Mockito.verify(dao, Mockito.times(0)) .save(Mockito.any());
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
