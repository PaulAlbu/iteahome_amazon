package ro.amazon.dao;

import ro.amazon.entity.Product;
import ro.amazon.exceptions.ProductDatabaseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDAO {
    private ArrayList<Product> productsArr = new ArrayList<>();

    public ArrayList<Product> createProductsList() throws ProductDatabaseException {

        try {
            Scanner scanner = new Scanner(new File("src/main/resources/Products.txt"));
            while (scanner.hasNextLine()) {
                String[] daoProd = scanner.nextLine().split(";");
                String name = daoProd[0].trim();
                String description = daoProd[1].trim();
                Double price = Double.valueOf(daoProd[2].trim());
                Integer quantity = Integer.valueOf(daoProd[3].trim());

                Product product = new Product(name, description, price, quantity);
                productsArr.add(product);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new ProductDatabaseException(e);
        } catch (NumberFormatException e){
            throw new ProductDatabaseException(e);
        }

        return productsArr;
    }

}
