package services;

import org.testng.annotations.Test;

public class Main {
    @Test
    public static void MainTest(){
        GetAllProducts getAllProducts =new GetAllProducts();
        getAllProducts.getProducts();
        CreateNewProduct createNewProduct = new CreateNewProduct();
        String productname= createNewProduct.postCreateProduct();
        GetProduct getProduct = new GetProduct();
        getProduct.getSpecificProduct(productname);
        getProduct.getSpecificProductWithDifferentTestDaatas("elma","products", 200);
        createNewProduct.deleteCreatedProduct();
    }
}
