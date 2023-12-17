package ro.amazon.dao;

import ro.amazon.entity.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDAO {
    private ArrayList<Product> productsArr = new ArrayList<>();

    public ArrayList<Product> createProductsList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/Products.txt"));

        while (scanner.hasNextLine()){
            String[] daoProd = scanner.nextLine().split(";");
            String name = daoProd[0] ;
            String description = daoProd[1];
            Double price = Double.valueOf(daoProd[2]);

            Product product = new Product(name, description, price);
            productsArr.add(product);
        }
        scanner.close();
        return productsArr;
    }

}
