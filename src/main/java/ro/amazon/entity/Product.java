package ro.amazon.entity;

public class Product {
    private String name;
    private String productDescription;
    private double price;
    private Integer quantity;

    private Integer productID;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Product(String name, String productDescription, double price, Integer quantity, Integer prodcutID) {
        this.name = name;
        this.productDescription = productDescription;
        this.price = price;
        this.quantity = quantity;
        this.productID = prodcutID;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
