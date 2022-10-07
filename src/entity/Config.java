package entity;

import dao.FileDAO;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private static final String CONFIG_FILE = "src/data/config.txt";
    private String accountFile;
    private String dealerFile;
    private String deliveryFile;

    public Config() throws Exception {
        loadData();
    }
    private void loadData() throws Exception {
        ArrayList<String> lines = FileDAO.readLinesFromFile(CONFIG_FILE);
        if (lines != null){
            for (String line : lines){
                line = line.toUpperCase();
                String[] parts = line.split(":\\s");
                if (line.indexOf("ACCOUNT") >= 0){
                    accountFile = "src/data/" + parts[1].trim().toLowerCase();
                } else if (line.indexOf("DEALERS") >= 0){
                    dealerFile = "src/data/" + parts[1].trim().toLowerCase();
                } else if (line.indexOf("DELIVERIES") >= 0){
                    deliveryFile = "src/data/" + parts[1].trim().toLowerCase();
                }
            }
        } else System.out.println("Error");

    }

    public String getAccountFile() {
        return accountFile;
    }

    public String getDealerFile() {
        return dealerFile;
    }

    public String getDeliveryFile() {
        return deliveryFile;
    }
}
