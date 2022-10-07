package entity;

import java.util.concurrent.Delayed;

public class Dealer implements Comparable<Dealer> {
    public static final char SEPARATOR = ',';
    public static final String ID_FORMAT = "D\\d{3}";
    public static final String PHONE_FORMAT = "\\d{9}|\\d{11}";


    // field of Dealer
    private String ID;
    private String name;
    private String address;
    private String phone;
    private boolean continuing;

    public Dealer(String ID, String name, String address, String phone, boolean continuing) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.continuing = continuing;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isContinuing() {
        return continuing;
    }

    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }

    @Override
    public String toString() {
        return ID + SEPARATOR + name + SEPARATOR + address + SEPARATOR + phone + SEPARATOR + continuing;
    }

    @Override
    public int compareTo(Dealer dealer) {
        if (this.ID.compareTo(dealer.getID()) > 0) return 1; //1 la dong y swap 2 object trong mang
        else if (this.ID.compareTo(dealer.getID()) < 0) return -1;
        return 0;
    }
}
