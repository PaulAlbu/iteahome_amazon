package ro.amazon.entity;

public class Product {
    private String name;
    private String productDescription;
    private double price;


    public Product(String name, String productDescription, double price) {
        this.name = name;
        this.productDescription = productDescription;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
