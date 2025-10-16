package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        //Scanner
        Scanner scanner = new Scanner(System.in);

        //Variable for the date the program is in use
        LocalDate currentDate = LocalDate.now();

        //Main Menu
        while(true)
        {
            System.out.println("\n");
            System.out.println("Please choose an option");
            System.out.println("1) Add Deposit");
            System.out.println("2) Make Payment");
            System.out.println("3) Ledger");
            System.out.println("4) Exit");
            String mainMenuInput = scanner.nextLine();

            if(mainMenuInput.isBlank())
            {
                System.out.println("Please type something in.");
                continue;
            }

            switch(mainMenuInput)
            {
                //Deposit transaction
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

                    //Payment transaction
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

                    //Ledger Menu
                case "3":

                    boolean ledgerRunning = true;
                    while(ledgerRunning)
                    {
                        System.out.println("\nPlease choose an option:");
                        System.out.println("1) All");
                        System.out.println("2) Deposits");
                        System.out.println("3) Payments");
                        System.out.println("4) Reports");
                        System.out.println("5) Return");
                        String ledgerInput = scanner.nextLine();

                        if(ledgerInput.isBlank())
                        {
                            System.out.println("Please type something in.");
                            continue;
                        }

                        List<Transactions> allTransactions = FileManager.transactionHelper();

                        switch(ledgerInput)
                        {
                            //All transactions print
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

                                //All deposits print
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

                                //All payments print
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

                                //Reports Menu
                            case "4":
                                boolean reportRunning = true;
                                while(reportRunning)
                                {
                                    System.out.println("What report would you like to see?" +
                                            "\nPlease enter the number");
                                    System.out.println("1) Previous Month");
                                    System.out.println("2) Year to Date");      //Start of previous year
                                    System.out.println("3) Previous Year");     //Start of current year
                                    System.out.println(("4) Return"));
                                    String reportsInput = scanner.nextLine();

                                    if(reportsInput.isBlank())
                                    {
                                        System.out.println("Please type something in.");
                                        continue;
                                    }

                                    switch (reportsInput)
                                    {
                                        //All transactions from previous month
                                        case "1":
                                            System.out.println("\nHere are all your transactions from the previous month");

                                            LocalDate startOfPastMonth = currentDate.minusMonths(1).withDayOfMonth(1);
                                            LocalDate endOfPastMonth = currentDate.withDayOfMonth(1).minusDays(1);

                                            for(Transactions transactions : allTransactions)
                                            {
                                                if(transactions.getDate().isBefore(endOfPastMonth) && transactions.getDate().isAfter(startOfPastMonth))
                                                {
                                                    System.out.printf("\n%s \t %s \t %s \t %s \t %f",
                                                            transactions.getDate(), transactions.getTime(),
                                                            transactions.getDescription(), transactions.getVendor(),
                                                            transactions.getAmount());
                                                }
                                            }
                                            break;

                                            //All transactions from start of asked year
                                        case "2":
                                            System.out.println("What year would you like your transactions to begin from?");
                                            int startYear = scanner.nextInt();


                                            LocalDate startOfInputYear = currentDate.withYear(startYear).withDayOfYear(1);

                                            System.out.printf("\nHere are all your transactions starting from %d to today", startYear);

                                            for(Transactions transactions : allTransactions)
                                            {
                                                if(transactions.getDate().isAfter(startOfInputYear) && transactions.getDate().isBefore(currentDate))
                                                {
                                                    System.out.printf("\n%s \t %s \t %s \t %s \t %f",
                                                            transactions.getDate(), transactions.getTime(),
                                                            transactions.getDescription(), transactions.getVendor(),
                                                            transactions.getAmount());
                                                }
                                            }
                                            break;

                                        case "3":
                                            System.out.println("\nHere are all your transactions from the previous year:");

                                            int currentYear = currentDate.getYear();
                                            LocalDate startOfYear = currentDate.withYear(currentYear).withDayOfYear(1);

                                            for(Transactions transactions : allTransactions)
                                            {
                                                if(transactions.getDate().isAfter(startOfYear) && transactions.getDate().isBefore(currentDate))
                                                {
                                                    System.out.printf("\n%s \t %s \t %s \t %s \t %f",
                                                            transactions.getDate(), transactions.getTime(),
                                                            transactions.getDescription(), transactions.getVendor(),
                                                            transactions.getAmount());
                                                }
                                            }
                                            break;

                                        case "4":
                                            reportRunning = false;
                                            break;
                                        default:
                                            System.out.println("\nInvalid choice: Please try again");
                                    }
                                }

                            case "5":
                                ledgerRunning = false;
                                break;

                            default:
                                System.out.println("\nInvalid choice: Please try again");
                        }
                    }

                case "4":
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nInvalid choice: Please try again");

            }
        }
    }
}