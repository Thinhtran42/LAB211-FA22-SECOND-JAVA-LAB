package mng;

import entity.Account;
import tools.MyTool;

public class LogIn {
    private static Account acc = null;

    public LogIn(Account acc) {
        LogIn.acc = acc;
    }

    public LogIn() {
    }

    public void inputAccount() {
        System.out.println("Input information of account: ");
        String accName = MyTool.readPattern("Name", "E\\d{3}");
        String passWord = MyTool.readNonBlank("Password");
        String role = MyTool.readNonBlank("Role");
        acc = new Account(accName, passWord, role);
    }

    //get account
    public Account getAcc() {
        return acc;
    }

}
