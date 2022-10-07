package dao;

import entity.Account;
import entity.Dealer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDAO {
    private final static Scanner scanner = new Scanner(System.in);

    public static ArrayList<Account> readLinesFromFileAccount(String fileName) throws Exception{
        //check validation
        File tmp = new File(fileName); // lop file de check thong tin file
        if (!tmp.exists() || !tmp.isFile()) return null;
        // mo file
        FileReader f = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(f);

        //doc file
        ArrayList<Account> listLines = new ArrayList<>();
        while (bf.ready()){
            String str = bf.readLine();
            String [] s = str.split(",");
            if (s.length == 3){
                Account acc = new Account(s[0].trim(), s[1].trim(), s[2].trim());
                listLines.add(acc);
            }

        }
        // dong file
        f.close();
        bf.close();
        return listLines;
    }
    public static ArrayList<Dealer> readLinesFromFileDealer(String fileName) throws Exception{
        //check validation
        File tmp = new File(fileName); // lop file de check thong tin file
        if (!tmp.exists() || !tmp.isFile()) return null;
        // mo file
        FileReader f = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(f);
        // doc file
        ArrayList<Dealer> list = new ArrayList<>();
        while (bf.ready()) {
            String s = bf.readLine();
            //chuyen s thanh object
            String[] arr = s.split("[,.]");
            if (arr.length == 5) {
                Dealer d = new Dealer(arr[0].trim(), arr[1].trim(), arr[2].trim(), arr[3].trim(), Boolean.parseBoolean(arr[4].trim()));
                list.add(d);
            }
        }
        // dong file
        f.close();
        bf.close();
        return list;
    }

    public static ArrayList<String> readLinesFromFile(String fileName) throws Exception{
        //check validation
        File tmp = new File(fileName); // lop file de check thong tin file
        if (!tmp.exists() || !tmp.isFile()) return null;
        // mo file
        FileReader f = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(f);

        //doc file
        ArrayList<String> listLines = new ArrayList<>();
        while (bf.ready()){
            String str = bf.readLine();
            listLines.add(str);
        }
        // dong file
        f.close();
        bf.close();
        return listLines;
    }

        public static void writeDealerData(String fileName, ArrayList<Dealer> list) throws Exception {
        File tmp = new File(fileName);
        if (!tmp.exists() || !tmp.isFile()) return; // neu khong ton tai or khong phai file thi return luon
        if (list != null && list.size() > 0) {
            PrintWriter w = new PrintWriter(fileName);
            for (Dealer deal : list) {
                w.print(deal.toString());
            }
            w.close();
        }
    }
    public static void writeAccountData(String fileName, ArrayList<Account> list) throws Exception {
        File tmp = new File(fileName);
        if (!tmp.exists() || !tmp.isFile()) return; // neu khong ton tai or khong phai file thi return luon
        if (list != null && list.size() > 0) {
            PrintWriter w = new PrintWriter(fileName);
            for (Account acc : list) {
                String accInfo = acc.getAccName() + ',' + acc.getPassWord() + ',' + acc.getRole();
                w.println(accInfo);
            }
            w.close();
        }
    }
}
