package State;

import entity.Account;
import entity.AccountChecker;
import mng.LogIn;

import java.util.Scanner;

public class InitialState  {
    public static void login() throws Exception {
        Scanner sc = new Scanner(System.in);
        LogIn login = new LogIn();
        AccountChecker accountChecker = new AccountChecker();
        while (true){
            System.out.println("----LOGIN----");
            login.inputAccount();
            Account acc = login.getAcc();
            boolean valid = accountChecker.check(acc);

            if (valid){
                int choice;
                do {
                    String role = acc.getRole();
                    switch (role){
                        case "ACC-1":
                            ACC1_State.run();
                            break;
                        case "BOSS":
                            BossState.run();
                            break;
                    }
                } while (!valid);
                    break;
            } else System.out.println("Wrong information !");
        }
    }


}
