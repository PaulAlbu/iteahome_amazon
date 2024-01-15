package ro.amazon.dao;

import java.io.*;
import java.util.Scanner;

public class BasketDAO {
    public void addProductQuantityBackToTheStock (int productID, int quantityRemovedByBuyer) {
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
                    int quantity = Integer.valueOf(prodLine[3].trim()) + quantityRemovedByBuyer;
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
