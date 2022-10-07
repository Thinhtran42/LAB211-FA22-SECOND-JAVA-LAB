package entity;

public class Account implements Comparable<Account> {
    private String accName;
    private String passWord;
    private String role;

    public static final String NAME_FORMAT = "E\\d{3}";
    public static final char SEPARATOR = ',';


    public Account(String accName, String passWord, String role) {
        this.accName = accName;
        this.passWord = passWord;
        this.role = role;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccName() {
        return accName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return accName + SEPARATOR + passWord + SEPARATOR + role;
    }

    @Override
    public int compareTo(Account o) {
        if (this.accName.compareTo(o.getAccName()) > 0) return 1; //1 la dong y swap 2 object trong mang
        else if (this.accName.compareTo(o.getAccName()) < 0) return -1;
        return 0;
    }
}
