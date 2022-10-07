package dao;

import entity.Account;
import entity.Config;
import entity.Dealer;
import tools.MyTool;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountListDAO {
    private Config config; // de get src tu config co san
    private ArrayList<Account> listAcc;

    // khoi tao account bang cach load data tu file account.txt roi luu data vo list account
    public AccountListDAO() {
        try {
            config = new Config();
            String filename = config.getAccountFile();
            listAcc = FileDAO.readLinesFromFileAccount(filename);
        } catch (Exception er) {
            er.printStackTrace();
        }
    }
    ///////////////////////////////////////////////

    public int searchDealer(String name) {
        if (!listAcc.isEmpty()) {
            for (int i = 0; i < listAcc.size(); i++) {
                if (listAcc.get(i).getAccName().equals(name)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void addAccount() {
        String name;
        String passWord;
        String role;

        int pos;
        do {
            name = MyTool.readPattern("Name of account", Account.NAME_FORMAT);
            name = name.toUpperCase();
            pos = searchDealer(name);
            if (pos >= 0) System.out.println("Account's name is duplicated !");
        } while (pos >= 0);
        passWord = MyTool.readNonBlank("Password of account");
        do {
            role = MyTool.readNonBlank("Role of account");
        } while (!role.equals("ACC-1") && !role.equals("BOSS") && !role.equals("ACC-2"));

        Account acc = new Account(name, passWord, role);
        listAcc.add(acc);
        System.out.println("New Account has been added !");
    }

    public void searchAccount() {
        String nameToSearch = MyTool.readPattern("Input the name acc to search", Account.NAME_FORMAT);
        if (!listAcc.isEmpty()) {
            for (Account acc : listAcc) {
                if (acc.getAccName().equalsIgnoreCase(nameToSearch)) {
                    System.out.println(acc);
                }
            }
            System.out.println("Not found dealer ! ");
        } else System.out.println("No data ! ");
    }

    public void removeAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input name of account need to remove: ");
        String nameToRemove = sc.nextLine();
        int pos = searchDealer(nameToRemove);
        if (pos < 0) System.out.println("Account " + nameToRemove + " not found !");
        else {
            listAcc.remove(listAcc.get(pos));
            System.out.println("This account has been removed !");
        }
    }

    public void updateAccount() {
        System.out.println("Input name of account need to update: ");
        Scanner sc = new Scanner(System.in);
        String nameToUpdate = sc.nextLine();
        int pos = searchDealer(nameToUpdate);

        if (pos < 0) System.out.println("Account " + nameToUpdate + " not found !");
        else {
            Account acc = listAcc.get(pos);
            System.out.print("New name, ENTER for editing: ");
            String newName = MyTool.readPattern("Name of account", Account.NAME_FORMAT);
            acc.setAccName(newName);
            System.out.print("New PASSWORD, ENTER for editing: ");
            String newPass = sc.nextLine();
            if (!newPass.isEmpty()) {
                acc.setPassWord(newPass);
            }
            System.out.print("New role, ENTER for editing: ");
            String newRole = sc.nextLine();

            if (!newRole.isEmpty()) {
                acc.setRole(newRole);
            } else {
                do {
                    newRole = MyTool.readNonBlank("New role, ENTER for editing: ");
                    acc.setRole(newRole);
                } while (!newRole.equals("ACC-1") && !newRole.equals("BOSS") && !newRole.equals("ACC-2"));
            }
            System.out.println("Updated !");
        }
    }

    public void print() {
        if (listAcc != null) {
            System.out.println("Account list: ");
            for (Account a : listAcc) {
                System.out.println(a);
            }
        } else System.out.println("no data");
    }

    public void writeToFile() throws Exception {
        Config config = new Config();
        String dataFile = config.getAccountFile();
        FileDAO.writeAccountData(dataFile, listAcc);
        System.out.println("Saved ! ");
    }

    public static void main(String[] args) throws Exception {
        AccountListDAO acc = new AccountListDAO();

        acc.addAccount();
        acc.print();
        acc.writeToFile();

    }
}
