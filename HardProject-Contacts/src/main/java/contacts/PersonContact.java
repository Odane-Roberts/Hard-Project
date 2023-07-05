package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonContact implements Contact, Serializable {
    private String name;
    private String surname;
    private String gender= "[no data]";
    private String dob = "[no data]";
    private String number = "[no number]";
    private LocalDateTime created;
    private LocalDateTime lastEdited;

    @Override
    public String toString() {
        return name + " "
           + surname;
    }

    public PersonContact(String name, String surname, String gender, String dob, String number, LocalDateTime created, LocalDateTime lastEdited) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dob = dob;
        this.number = number;
        this.created = created;
        this.lastEdited = lastEdited;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        setLastEdited(LocalDateTime.now().withNano(0).withSecond(0));
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        setLastEdited(LocalDateTime.now().withNano(0).withSecond(0));
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        setLastEdited(LocalDateTime.now().withNano(0).withSecond(0));
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        setLastEdited(LocalDateTime.now().withNano(0).withSecond(0));
        this.dob = dob;
    }

    public String getNumber() {
        return number;
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

    @Override
    public void displayInfo() {
        System.out.println("Name: "+getName());
        System.out.println("Surname: "+getSurname());
        System.out.println("Birth date: "+ getDob());
        System.out.println("Gender: "+getGender());
        System.out.println("Number: "+getNumber());
        System.out.println("Time created: "+getCreated());
        System.out.println("Time last edit: "+getLastEdited());
    }

    @Override
    public String regexToMatch() {
        return this.name +" "+this.surname +" "+this.number;
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
