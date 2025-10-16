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

        //Date variables
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        //Main Menu
        while(true)
        {
            System.out.println("\nPlease choose an option");
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
                        System.out.println("1) Show All Transactions");
                        System.out.println("2) Show Deposits");
                        System.out.println("3) Show Payments");
                        System.out.println("4) Show Reports");
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
                                    System.out.printf("%-12s %-8s %-25s %-15s %10.2f%n",
                                            transactions.getDate(), transactions.getTime(),
                                            transactions.getDescription(), transactions.getVendor(),
                                            transactions.getAmount());
                                }
                                break;

                                //All deposits print
                            case "2":
                                System.out.println("\nHere are all your deposits:");
                                for(Transactions transactions : allTransactions)
                                {
                                    if(transactions.getAmount() > 0)
                                    {
                                        System.out.printf("%-12s %-8s %-25s %-15s %10.2f%n",
                                                transactions.getDate(), transactions.getTime(),
                                                transactions.getDescription(), transactions.getVendor(),
                                                transactions.getAmount());
                                    }
                                }
                                break;

                                //All payments print
                            case "3":
                                System.out.println("\nHere are all your payments:");
                                for(Transactions transactions : allTransactions)
                                {
                                    if(transactions.getAmount() < 0)
                                    {
                                        System.out.printf("%-12s %-8s %-25s %-15s %10.2f%n",
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
                                    System.out.println("\nWhat report would you like to see?");
                                    System.out.println("1) Previous Month");
                                    System.out.println("2) Year to Date");      //Start of  year
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
                                            System.out.println("\nHere are all your transactions from the previous month:");

                                            LocalDate startOfPastMonth = currentDate.minusMonths(1).withDayOfMonth(1);
                                            LocalDate endOfPastMonth = currentDate.withDayOfMonth(1).minusDays(1);

                                            for(Transactions transactions : allTransactions)
                                            {
                                                if(transactions.getDate().isBefore(endOfPastMonth) && transactions.getDate().isAfter(startOfPastMonth))
                                                {
                                                    System.out.printf("%-12s %-8s %-25s %-15s %10.2f%n",
                                                            transactions.getDate(), transactions.getTime(),
                                                            transactions.getDescription(), transactions.getVendor(),
                                                            transactions.getAmount());
                                                }
                                            }
                                            break;

                                            //All transactions from start of asked year
                                        case "2":

                                            System.out.println("\nHere are all your transactions from the start of this year:");

                                            LocalDate startOfYear = LocalDate.of(currentYear, 1, 1);
                                            LocalDate endOfYear = currentDate.plusYears(1);

                                            for(Transactions transactions : allTransactions)
                                            {
                                                if(transactions.getDate().isAfter(startOfYear) && transactions.getDate().isBefore(endOfYear))
                                                {
                                                    System.out.printf("%-12s %-8s %-25s %-15s %10.2f%n",
                                                            transactions.getDate(), transactions.getTime(),
                                                            transactions.getDescription(), transactions.getVendor(),
                                                            transactions.getAmount());
                                                }
                                            }
                                            break;

                                        case "3":
                                            System.out.println("\nHere are all your transactions from the previous year:");

                                            int previousYear = currentYear -1;
                                            LocalDate startOfPreviousYear = LocalDate.of(previousYear, 1,1);
                                            LocalDate endOfPreviousYear = LocalDate.of(previousYear,12, 31);

                                            for(Transactions transactions : allTransactions)
                                            {
                                                if(transactions.getDate().isBefore(endOfPreviousYear) && transactions.getDate().isAfter(startOfPreviousYear))
                                                {
                                                    System.out.printf("%-12s %-8s %-25s %-15s %10.2f%n",
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
                                break;

                            case "5":
                                ledgerRunning = false;
                                break;

                            default:
                                System.out.println("\nInvalid choice: Please try again");
                        }
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