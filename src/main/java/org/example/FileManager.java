package org.example;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManager
{
    public static List<Transactions> transactionHelper()
    {
        List<Transactions> transactions = new ArrayList<>();

        //File reader to see transaction history
        try
        {
            FileReader fr = new FileReader("src/main/resources/Transactions.txt");

            BufferedReader reader = new BufferedReader(fr);

            String line;

            while((line = reader.readLine()) != null)
            {
                String[] transactionData = line.split("\\|");
                //[0] = localDate, [1] = localTime, [2] = description, [3] = vendor, [4] = amount
                Transactions newTransaction = new Transactions();
                newTransaction.setDate(LocalDate.parse(transactionData[0]));
                newTransaction.setTime(LocalTime.parse(transactionData[1]));
                newTransaction.setDescription(transactionData[2]);
                newTransaction.setVendor(transactionData[3]);
                newTransaction.setAmount(Double.parseDouble(transactionData[4]));

                transactions.add(newTransaction);
            }
            reader.close();

        }
        catch (FileNotFoundException ex1)
        {
            System.out.println("Could not find the file");
        }
        catch(IOException ex2)
        {
            System.out.println("File had a problem with it");
        }

        return transactions;

    }

    public static void writeTransactions (Transactions transaction)
    {
        //File writer used when asking for user input
        try
        {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            FileWriter fileWriter = new FileWriter("src/main/resources/Transactions.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("\n" + transaction.getDate()
                    + "|" + transaction.getTime().format(timeFormatter)
                    + "|" + transaction.getDescription()
                    + "|" + transaction.getVendor()
                    + "|" + transaction.getAmount());

            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println("Something went wrong with the file, try again");
        }
    }
}
