package org.example;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        //Scanner
        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            System.out.println("1) Add Deposit");
            System.out.println("2) Make Payment");
            System.out.println("3) Ledger");
            System.out.println("4) Exit");
            String homeScreenInput = scanner.nextLine();

            switch(homeScreenInput)
            {
                case "1":
                    Transactions deposit = new Transactions();

                    deposit.setTime(LocalTime.now());
                    deposit.setDate(LocalDate.now());

                    System.out.println("\nEnter a Description:");
                    deposit.setDescription(scanner.nextLine());

                    System.out.println("Enter the Vendor:");
                    deposit.setVendor(scanner.nextLine());

                    System.out.println("Enter an Amount:");
                    deposit.setAmount(Double.parseDouble(scanner.nextLine()));

                    FileManager.writeTransactions(deposit);

                    break;

                case "2":
                    Transactions payment = new Transactions();

                    payment.setTime(LocalTime.now());
                    payment.setDate(LocalDate.now());

                    System.out.println("Enter a Description:");
                    payment.setDescription(scanner.nextLine());

                    System.out.println("Enter the Vendor:");
                    payment.setVendor(scanner.nextLine());

                    System.out.println("Enter an Amount:");
                    payment.setAmount(Double.parseDouble(scanner.nextLine()) * -1);

                    FileManager.writeTransactions(payment);

                    break;

                case "3":
                    System.out.println("\nPlease choose an option:");
                    System.out.println("1) All");
                    System.out.println("2) Deposits");
                    System.out.println("3) Payments");
                    System.out.println("4) Reports");
                    System.out.println("5) Return");
                    String ledgerInput = scanner.nextLine();

                    List<Transactions> allTransactions = FileManager.transactionHelper();

                    switch(ledgerInput)
                    {
                        case "1":
                            System.out.println("Here are all your transactions:");
                            for(Transactions transactions : allTransactions)
                            {
                                System.out.printf("%s \t %s \t %f\n",
                                        transactions.getDescription(), transactions.getVendor(),
                                        transactions.getAmount());
                            }

                            break;
                        case "2":
                            System.out.println("Here are all your deposits\n");
                            break;
                        case "3":
                            System.out.println("Here are all your payments\n");
                            break;
                        case "4":
                            System.out.println("1) Previous Month");
                            System.out.println("2) Year to Date");
                            System.out.println("3) Previous Year");
                            System.out.println(("4) Return"));
                            String reportsInput = scanner.nextLine();

                            switch (reportsInput)
                            {
                                case "1":
                                    System.out.println("Here are all your transactions from the previous month\n");
                                    break;
                                case "2":
                                    System.out.println("Here are all your transactions from the start of that year\n");
                                    break;
                                case "3":
                                    System.out.println("Here are all your transactions from the previous year\n");
                                    break;
                                case "4":
                                    break;
                                default:
                                    System.out.println("Invalid choice: Please try again");
                            }
                            break;

                        case "5":
                            break;
                        default:
                            System.out.println("Invalid choice: Please try again");
                    }
                    break;

                case "4":
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice: Please try again");

            }

        }

    }
}