package ro.amazon.dao;

import ro.amazon.entity.Product;
import ro.amazon.exceptions.ProductDatabaseException;

import java.io.*;
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
                Integer prodcutID = Integer.valueOf(daoProd[4].trim());


                Product product = new Product(name, description, price, quantity, prodcutID);
                productsArr.add(product);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new ProductDatabaseException(e);
        } catch (NumberFormatException e) {
            throw new ProductDatabaseException(e);
        }

        return productsArr;
    }

    public void decreaseProductQuantity(int productID, int quantityRequestedByBuyer) {
        String filePath = "src/main/resources/Products.txt";
        int lineNumberToEdit = productID; // Change to the line number you want to edit

        try {
            // Open the file for reading and writing
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder fileContent = new StringBuilder();
            String currentLine;

            // Read each line and edit the target line
            int currentLineNumber = 1;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLineNumber == lineNumberToEdit) {
                    // Modify the content of the target line
                    Scanner scanner = new Scanner(currentLine);
                    String[] prodLine = scanner.nextLine().split(";");
                    String name = prodLine[0].trim();
                    String description = prodLine[1].trim();
                    Double prodPrice = Double.valueOf(prodLine[2].trim());
                    int quantity = Integer.valueOf(prodLine[3].trim()) - quantityRequestedByBuyer;
                    int productId = productID;

                    String newText = name + ";" + description + ";" + prodPrice + ";" + quantity + ";" + productId;

                    fileContent.append(newText).append(System.lineSeparator());
                } else {
                    fileContent.append(currentLine).append(System.lineSeparator());
                }
                currentLineNumber++;
            }
            reader.close();

            // Write the modified content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(fileContent.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
