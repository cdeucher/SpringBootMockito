package com.app.product;


import com.app.product.dao.ImageDaoImpl;
import com.app.product.entity.Image;
import com.app.product.service.ImageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.function.Consumer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.validateMockitoUsage;

@SpringBootTest
class ImageServiceTests {

    @Test
    void whenAddNewImage_ShouldChackIfImageWasSave(){
            given()
                .imageProvider(image -> {
                    image.id=1;
                    image.name="nameXx";
                    image.productId=1;
                    image.url="http://localhost/imag.pgn";
                })
            .whenPerformSaveImage()
            .then()
                .checkWhoseImageWasSaved();

    }

    @Test
    void whenListImages_ShouldReturnListOfImages(){
            given()
                .imageProvider(image -> {
                    image.id=1;
                    image.name="nameXx";
                    image.productId=1;
                    image.url="http://localhost/imag.pgn";
                })
            .whenPerformList_ShouldReturnImageList(1);
    }

    @Test
    void whenGetTheImage_ShouldReturnTheImage(){
            given()
                .imageProvider(image -> {
                    image.id=1;
                    image.name="nameXx";
                    image.productId=1;
                    image.url="http://localhost/imag.pgn";
                })
            .whenGetImageById(1)
            .then()
                .checkImageById(1);
    }

    @Test
    void whenRemoveImage_ShouldRemoveTheImage(){
            given()
            .whenRemoveImage(1)
            .then()
                .checkRemovedImage(1);
    }

    @AfterAll
    static void validate() {
        validateMockitoUsage();
    }

    public DSL given(){
        return new DSL();
    }
    private static class DSL {

        ImageProviderData imageData;
        ImageService service;
        ImageDaoImpl dao = mock(ImageDaoImpl.class);


        public DSL() {
            service = new ImageService(dao);
            imageData = new ImageProviderData();
        }

        public DSL imageProvider(Consumer <ImageProviderData> image) {
            image.accept(imageData);
            return this;
        }

        public ThenDSL whenPerformSaveImage(){
            service.insertNewImage(imageData.convertToImage());
            return new ThenDSL();
        }

        public ThenDSL whenPerformList_ShouldReturnImageList(int productId) {
            try (MockedStatic<ImageDaoImpl> theMock = Mockito.mockStatic(ImageDaoImpl.class)) {
                theMock.when(() -> dao.getImagesByProductId(productId)).thenReturn(Arrays.asList(imageData.convertToImage()));

                Image img = (Image) service.list(productId).stream().findFirst().get();
                Assertions.assertThat(img.getId()).isEqualTo(imageData.convertToImage().getId());
            }
            return new ThenDSL();
        }

        public ThenDSL whenGetImageById(int imageId) {
            service.getImage(imageId);
            return new ThenDSL();
        }

        public ThenDSL whenRemoveImage(int imageId) {
            service.removeImageById(imageId);
            return new ThenDSL();
        }

        public class ThenDSL {

            public ThenDSL then() {
                return this;
            }

            public void checkWhoseImageWasSaved() {
                Mockito.verify(dao).save(Mockito.any());
            }

            public void checkImageById(int imageId) {
                Mockito.verify(dao).getImagesById(imageId);
            }

            public void checkRemovedImage(int imageId) {
                Mockito.verify(dao).removeImage(imageId);
            }
        }


    }

    private static class ImageProviderData {
        private String name;
        private String url;
        private int productId;
        private int id;
        public Image convertToImage(){
            return new Image(name, url, productId, id);
        }
    }
}
