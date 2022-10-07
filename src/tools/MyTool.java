package tools;

import State.InitialState;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MyTool {
    public static final Scanner scanner = new Scanner(System.in);

    public static boolean validPassWord(String str, int minLen) throws Exception {
        if (str.length() < minLen) return false;
        return str.matches(".*[a-zA-Z]+.*") &&
                str.matches(".*[\\d]+.*") &&
                str.matches(".*[\\W]+.*");
    }

    public static boolean validStr(String str, String rexEx){
        if(str.matches(rexEx)) return true;
        return false;
    }
    public static String readNonBlank(String msg){
        String input = "";
        do {
            System.out.print(msg + ": ");
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }
    public static String readPattern(String msg, String pattern){
        String input = "";
        boolean valid;
        do {
            System.out.print(msg + ": ");
            input = scanner.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }
    public static boolean readBool(String msg){
        String input;
        System.out.print(msg + ": ");
        input = scanner.nextLine().trim();
        if (input.isEmpty()) return false;
        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }
    public static Date parseDate(String dateStr, String dateFormat){
        SimpleDateFormat dF = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        dF.applyPattern(dateFormat);
        try {
            long t = dF.parse(dateStr).getTime();
            return new Date(t);

        }catch (ParseException er){
            System.out.println(er);
        }
        return null;
    }

    public static String dataToString(Date date, String dateFormat){
        // Implement it
        return "";
    }
    public static boolean parseBool(String boolString){
        char c = boolString.trim().toUpperCase().charAt(0);
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static void goBack() throws Exception {
        System.out.println("Do you want go back LOGIN MENU ?");
        System.out.print("Input y/n: ");
        String goBack = scanner.nextLine();
        if (goBack.equalsIgnoreCase("y")){
            InitialState.login();
        } else {
            System.out.println("Bye !");
        }
    }

}
