package State;

import dao.AccountListDAO;
import dao.DealerListDAO;
import entity.Account;
import entity.Config;
import mng.Menu;
import tools.MyTool;

import java.util.Scanner;

public class BossState {
    public static void run() throws Exception {
        String[] options = {"1. Add new account", "2. Search a account", "3. Remove a account", "4. Update a account", "5. Print all accounts", "6. Write to file", "Others to exit !"};

        Menu menu = new Menu(options);
        Config config = new Config();
        String dataFile = config.getAccountFile();
        AccountListDAO accList = new AccountListDAO();

        int choice = 0;
        do {
            System.out.println("Managing dealers: ");
            menu.displayFunction();
            choice = menu.getChoice("Input your choice: ");
            switch (choice) {
                case 1:
                    accList.addAccount();
                    break;
                case 2:
                    accList.searchAccount();
                    break;
                case 3:
                    accList.removeAccount();
                    break;
                case 4:
                    accList.updateAccount();
                    break;
                case 5:
                    accList.print();
                    break;
                case 6:
                    accList.writeToFile();
                    break;
            }
        } while (choice > 0 && choice < menu.size());

        // go back to initial menu:
        MyTool.goBack();

    }
}
