package dao;

import entity.Config;
import entity.Dealer;
import tools.MyTool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DealerListDAO {

    private static final String PHONEPATTERN = "\\d{9}|\\d{11}";
    boolean changed = false;
    ArrayList<Dealer> dealers;

    public DealerListDAO() {
        dealers = new ArrayList<>();
    }

    public DealerListDAO(String dataFile) throws Exception {
        dealers = FileDAO.readLinesFromFileDealer(dataFile);
    }


    public void addDealer() {
        String Id;
        String name;
        String address;
        String phone;
        boolean continuing;
        int pos;
        do {
            Id = MyTool.readPattern("Id of new dealer: ", Dealer.ID_FORMAT);
            Id = Id.toUpperCase();
            pos = searchDealer(Id);
            if (pos >= 0) System.out.println("Id is duplicated !");
        } while (pos >= 0);
        name = MyTool.readNonBlank("Name of new dealer").toUpperCase();
        address = MyTool.readNonBlank("Address of new dealer");
        phone = MyTool.readPattern("Phone number of new dealer", Dealer.PHONE_FORMAT);
        continuing = true; // default value of new dealer;
        Dealer dealer = new Dealer(Id, name, address, phone, continuing);
        dealers.add(dealer);
        System.out.println("New dealer has been added !");
        changed = true;
    }

    public ArrayList<Dealer> getContinuingList(ArrayList<Dealer> list) {
        ArrayList<Dealer> continuingList = new ArrayList<>();

        for (Dealer dealer : list) {
            if (dealer.isContinuing()) {
                continuingList.add(dealer);
            }
        }
        return continuingList;
    }

    public ArrayList<Dealer> getUnContinuingList(ArrayList<Dealer> list) {
        ArrayList<Dealer> unContinuingList = new ArrayList<>();

        for (Dealer dealer : list) {
            if (!dealer.isContinuing()) {
                unContinuingList.add(dealer);
            }
        }
        return unContinuingList;
    }

    public int searchDealer(String id) {
        if (!dealers.isEmpty()) {
            for (int i = 0; i < dealers.size(); i++) {
                if (dealers.get(i).getID().equals(id)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void searchDealer() {
        String idToSearch = MyTool.readPattern("Input the id to search", Dealer.ID_FORMAT);
        if (!dealers.isEmpty()) {
            for (Dealer deal : dealers) {
                if (deal.getID().equalsIgnoreCase(idToSearch)) {
                    System.out.println(deal);
                }
            }
            System.out.println("Not found dealer ! ");
        } else System.out.println("No data ! ");
    }

    public void removeDealer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input id of dealers need to remove : ");
        String idToRemove = sc.nextLine();
        int pos = searchDealer(idToRemove);

        if (pos < 0) System.out.println("Dealer " + idToRemove + " not found !");
        else {
            if (!dealers.get(pos).isContinuing()) {
                System.out.println("This dealer has been removed !");
            } else {
                dealers.get(pos).setContinuing(false);
                System.out.println("Removed !");
                changed = true;
            }
        }
    }

    public void updateDealer() {
        System.out.println("Input Dealer's Id need to updating: ");
        Scanner sc = new Scanner(System.in);
        String idToUpdate = sc.nextLine();
        int pos = searchDealer(idToUpdate);

        if (pos < 0) System.out.println("Dealer " + idToUpdate + " not found !");
        else {
            Dealer dealer = dealers.get(pos);
            System.out.print("New name, ENTER for editing: ");
            String newName = sc.nextLine().toUpperCase();
            if (!newName.isEmpty()) {
                dealer.setName(newName);
                changed = true;
            }
            System.out.print("New address, ENTER for editing: ");
            String newAddress = sc.nextLine();
            if (!newAddress.isEmpty()) {
                dealer.setAddress(newAddress);
                changed = true;
            }
            String newPhone = MyTool.readPattern("New phone, ENTER for editing", Dealer.PHONE_FORMAT);
            if (!newPhone.isEmpty()) {
                dealer.setPhone(newPhone);
                changed = true;
            }
            System.out.println("Updated !");
        }
    }

    public void printAllDealers() {
        if (dealers == null) {
            System.out.println("No data");
        } else {
            Collections.sort(dealers);
            System.out.println("Dealer list: ");
            for (Dealer d : dealers) {
                System.out.println(d);
            }
        }
    }

    public void printContinuingDealers() {
        ArrayList<Dealer> list = this.getContinuingList(dealers);
        System.out.println("Continuing Dealers: ");
        for (Dealer d : list) {
            System.out.println(d);
        }
    }

    public void printUnContinuingDealers() {
        ArrayList<Dealer> list = this.getUnContinuingList(dealers);
        System.out.println("Un-Continuing Dealers: ");
        for (Dealer d : list) {
            System.out.println(d);
        }
    }

    public void writeDealerToFile() throws Exception {
        Config config = new Config();
        String dataFile = config.getDealerFile();
        if (changed) {
            FileDAO.writeDealerData(dataFile, dealers);
            System.out.println("Saved !");
            changed = false;
        } else System.out.println("Not changed .");
    }
    public void saveData() throws Exception {
        Config config = new Config();
        String dataFile = config.getDealerFile();
        Collections.sort(dealers);
        FileDAO.writeDealerData(dataFile, dealers);
        System.out.println("Saved ! ");
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        String dataFile = config.getDealerFile();

        DealerListDAO d = new DealerListDAO(dataFile);

        d.printAllDealers();



    }
}
