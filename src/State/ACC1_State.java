package State;

import dao.DealerListDAO;
import dao.FileDAO;
import entity.Config;
import mng.Menu;
import tools.MyTool;

import java.util.Scanner;

public class ACC1_State {


    public static void run() throws Exception {
        String[] options = {"1.Add new dealer", "2.Search a dealer", "3.Remove a dealer", "4.Update a dealer", "5.Print all dealers", "6.Print continuing dealer", "7.Print UN-continuing dealer", "8.Write to file", "Others to exit !"};

        Menu menu = new Menu(options);
        DealerListDAO dealerList = new DealerListDAO();
        Config config = new Config();
        String dataFile = config.getDealerFile();
        DealerListDAO dealerFromFile = new DealerListDAO(dataFile);

        int choice = 0;
        do {
            System.out.println("Managing dealers: ");
            menu.displayFunction();
            choice = menu.getChoice("Input your choice: ");
            switch (choice) {
                case 1:
                    dealerFromFile.addDealer();
                    break;
                case 2:
                    dealerFromFile.searchDealer();
                    break;
                case 3:
                    dealerFromFile.removeDealer();
                    break;
                case 4:
                    dealerFromFile.updateDealer();
                    break;
                case 5:
                    dealerFromFile.printAllDealers();
                    break;
                case 6:
                    dealerFromFile.printContinuingDealers();
                    break;
                case 7:
                    dealerFromFile.printUnContinuingDealers();
                    break;
                case 8:
                    dealerFromFile.saveData();
                    break;
                default:
                    if (dealerFromFile.isChanged()) {
                        boolean res = MyTool.readBool("Data changed. Write to File ?");
                        if (res) dealerFromFile.writeDealerToFile();
                    }
            }
        } while (choice > 0 && choice <= menu.size());

        // go back to initial menu:
        MyTool.goBack();
    }
}
