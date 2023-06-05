package contacts;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrgContact implements Contact, Serializable {
    private String name;
    private String address;
    private String number;
    private LocalDateTime created;
    private LocalDateTime lastEdited;

    public OrgContact(String name, String address, String number, LocalDateTime created, LocalDateTime lastEdited) {
        this.name = name;
        this.address = address;
        this.number = number;
        this.created = created;
        this.lastEdited = lastEdited;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        setLastEdited(LocalDateTime.now().withNano(0).withSecond(0));
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
        setLastEdited(LocalDateTime.now().withNano(0).withSecond(0));
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        if(checkNumber(number)){
            this.number = number;
        } else {
            System.out.println("Wrong number format");
            this.number = "[no number]";
        }
        setLastEdited(LocalDateTime.now().withNano(0).withSecond(0));
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastEdited() {
        return this.lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    @Override
    public String toString() {
        return  this.name;
    }

    @Override
    public void displayInfo() {
        System.out.println("Organization name: "+getName());
        System.out.println("Address: "+getAddress());
        System.out.println("Number: "+getNumber());
        System.out.println("Time created: "+getCreated());
        System.out.println("Time last edit: "+getLastEdited());
    }

    @Override
    public String regexToMatch() {
        return this.name +" "+" "+this.number;
    }

    private Boolean checkNumber(String number) {
        Pattern pattern = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[ \\-]\\(\\w{2,}\\)|\\w+)([ \\-]\\w{2,})*",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(number);
        if(matcher.matches()){
            return true;
        }
        return false;
    }
}
