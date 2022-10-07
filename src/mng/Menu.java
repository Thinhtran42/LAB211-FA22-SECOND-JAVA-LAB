package mng;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends ArrayList<String> {
    public Menu(){
        super();
    }
    public Menu(String [] items){
        super();
        for (String item : items){
            this.add(item);
        }
    }
    public int getChoice(String title){
        Scanner scanner = new Scanner(System.in);
        System.out.print(title);
        int choice = Integer.parseInt(scanner.nextLine());
        return choice;
    }
    public void displayFunction(){
        if (this.isEmpty()) System.out.println("no data");;
        for (String str : this){
            System.out.println(str);
        }
    }
}
