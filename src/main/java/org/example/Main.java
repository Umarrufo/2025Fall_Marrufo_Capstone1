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
            System.out.println("\nPlease choose an option");
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
                            System.out.println("\nHere are all your transactions:");
                            for(Transactions transactions : allTransactions)
                            {
                                System.out.printf("\n%s \t %s \t %s \t %s \t %f",
                                        transactions.getDate(), transactions.getTime(),
                                        transactions.getDescription(), transactions.getVendor(),
                                        transactions.getAmount());
                            }

                            break;
                        case "2":
                            System.out.println("\nHere are all your deposits");
                            for(Transactions transactions : allTransactions)
                            {
                                if(transactions.getAmount() > 0)
                                {
                                    System.out.printf("\n%s \t %s \t %s \t %s \t %f",
                                            transactions.getDate(), transactions.getTime(),
                                            transactions.getDescription(), transactions.getVendor(),
                                            transactions.getAmount());
                                }
                            }

                            break;
                        case "3":
                            System.out.println("\nHere are all your payments");
                            for(Transactions transactions : allTransactions)
                            {
                                if(transactions.getAmount() < 0)
                                {
                                    System.out.printf("\n%s \t %s \t %s \t %s \t %f",
                                            transactions.getDate(), transactions.getTime(),
                                            transactions.getDescription(), transactions.getVendor(),
                                            transactions.getAmount());
                                }
                            }
                            break;
                        case "4":
                            System.out.println("\nWhat report would you like to see?" +
                                    "\nPlease enter the number");
                            System.out.println("1) Previous Month");
                            System.out.println("2) Year to Date");
                            System.out.println("3) Previous Year");
                            System.out.println(("4) Return"));
                            String reportsInput = scanner.nextLine();

                            switch (reportsInput)
                            {
                                case "1":
                                    System.out.println("\nHere are all your transactions from the previous month");
//                                    for(Transactions transactions : allTransactions)
//                                    {
//                                        if(transactions.getDate().getMonth() )
//                                        {
//                                            System.out.printf("\n%s \t %s \t %s \t %s \t %f",
//                                                    transactions.getDate(), transactions.getTime(),
//                                                    transactions.getDescription(), transactions.getVendor(),
//                                                    transactions.getAmount());
//                                        }
//                                    }
                                    break;
                                case "2":
                                    System.out.println("\nHere are all your transactions from the start of that year");
                                    break;
                                case "3":
                                    System.out.println("\nHere are all your transactions from the previous year");
                                    break;
                                case "4":
                                    break;
                                default:
                                    System.out.println("\nInvalid choice: Please try again");
                            }
                            break;

                        case "5":
                            break;
                        default:
                            System.out.println("\nInvalid choice: Please try again");
                    }
                    break;

                case "4":
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nInvalid choice: Please try again");

            }

        }

    }
}