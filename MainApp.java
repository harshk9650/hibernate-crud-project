package org.example;

import java.util.List;
import java.util.Scanner;


public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ItemDAO dao = new ItemDAO();

        while (true) {

           
            System.out.println("1. Add Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

             
                case 1:
                    System.out.print("Do you want to add item? (yes/no): ");
                    String answer = sc.next();

                    while (answer.equalsIgnoreCase("yes")) {

                        ItemDTO item = new ItemDTO();

                        System.out.print("Enter ID: ");
                        item.setId(sc.nextInt());

                        System.out.print("Enter Name: ");
                        item.setName(sc.next());

                        System.out.print("Enter Price: ");
                        item.setPrice(sc.nextDouble());

                        System.out.print("Enter Quantity: ");
                        item.setQuantity(sc.nextInt());

                        dao.addItem(item);

                        System.out.print("Add another item? (yes/no): ");
                        answer = sc.next();
                    }
                    break;

                
                case 2:
                    List<ItemDTO> items = dao.getAllItems();

                   
                    for (ItemDTO i : items) {
                        System.out.println(
                                i.getId() + " | " +
                                i.getName() + " | " +
                                i.getPrice() + " | " +
                                i.getQuantity()
                        );
                    }
                    break;

               
                case 3:
                    System.out.print("Enter Item ID: ");
                    int id = sc.nextInt();

                    System.out.println("What do you want to update?");
                    System.out.println("1. Price");
                    System.out.println("2. Quantity");

                    int option = sc.nextInt();

                    System.out.print("Enter new value: ");
                    double value = sc.nextDouble();

                    dao.updateItem(id, option, value);
                    break;

               
                case 4:
                    System.out.print("Enter Item ID to delete: ");
                    int deleteId = sc.nextInt();

                    dao.deleteItem(deleteId);
                    break;

             
                case 5:
                    System.out.println("Exiting application...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
