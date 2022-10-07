package entity;

import dao.FileDAO;
import tools.MyTool;

import java.util.ArrayList;

public class AccountChecker {
    private String accFile;
    private static String SEPARATOR = ",";

    public AccountChecker() throws Exception {
        setAccFile();
    }

    private void setAccFile() throws Exception {
        Config config = new Config();
        accFile = config.getAccountFile();
    }

    public boolean check(Account acc) throws Exception {
        ArrayList<String> lines = FileDAO.readLinesFromFile(accFile);

        for (String line : lines) {
            String[] part = line.split(SEPARATOR);
            if (part.length < 3) return false;
            if (part[0].equalsIgnoreCase(acc.getAccName()) &&
                    part[1].equals(acc.getPassWord()) &&
                    part[2].equals(acc.getRole())
            ) return true;
        }
        return false;
    }

    // test checker
    public static void main(String[] args) throws Exception {
        AccountChecker accountChecker = new AccountChecker();
        Account acc = new Account("E001", "12345678", "BOSS");
        boolean valid = accountChecker.check(acc);
        System.out.println(valid);
    }
}
