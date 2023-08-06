package modals;

public class ProductRequest {
    public String name;
    public double price;
    public int stock;

    public ProductRequest( String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
