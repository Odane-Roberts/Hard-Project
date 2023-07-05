package contacts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PhoneBookUtil {
    Contact contact;
    Scanner scn = new Scanner(System.in);
    Pattern pattern;
    Matcher matcher ;
    public Contact createContact(String type) {
        if (type.equalsIgnoreCase("person")) {
            System.out.println("Enter the name: ");
            String name = scn.nextLine();

            System.out.println("Enter Surname: ");
            String surname = scn.nextLine();

            System.out.println("Enter the birth date: ");
            String dob = scn.nextLine();

            if(dob.equalsIgnoreCase(" ") || dob.equalsIgnoreCase("")){
                System.out.println("Bad birth date!");
                dob = "[no data]";
            }

            System.out.println("Enter the gender (M, F): ");
            String gender = scn.nextLine();

            if(gender.equalsIgnoreCase(" ") || gender.equalsIgnoreCase("")){
                System.out.println("Bad gender!");
                gender = "[no data]";
            }

            System.out.println("Enter number");
            String number = scn.nextLine();

            contact = new PersonContact(name, surname, gender, dob, number, LocalDateTime.now().withSecond(0).withNano(0), LocalDateTime.now().withSecond(0).withNano(0));


        } else if (type.equalsIgnoreCase("organization")) {
            System.out.print("Enter organization name: ");
            String name = scn.nextLine();


            System.out.print("Enter organization address: ");
            String address = scn.nextLine();

            System.out.print("Enter organization number: ");
            String telephone = scn.nextLine();

            contact = new OrgContact(name,address,telephone, LocalDateTime.now().withSecond(0).withNano(0),LocalDateTime.now().withSecond(0).withNano(0));

        }
        return contact;
    }

    public void countContacts(ArrayList<Contact> contactList) {
        System.out.println("The Phone Book has "+contactList.size() +" records.");
    }


    public void searchContact(ArrayList<Contact> contacts) {
        if (contacts.size() < 1) {
            System.out.println("No records to search!");
        } else {
            System.out.println("Enter search query: ");
            String query = scn.nextLine();
            ArrayList<Contact> result = new ArrayList<>();

            OrgContact org;
            for (Contact con : contacts) {
                if(con.regexToMatch().toLowerCase().contains(query.toLowerCase())){
                    result.add(con);
                }

            }
            System.out.println("Found " + result.size() + " results:");
            int index = 0;
            for (Contact str : result) {
                System.out.println(++index + ". " + str.toString());
            }
            System.out.println("[search] Enter action ([number], back , again)");
            String response = scn.nextLine().toUpperCase();

            switch (response) {
                case "BACK":
                    break;
                case "AGAIN":
                    searchContact(contacts);
                    break;
                default:
                    try {
                        int num = Integer.parseInt(response);
                        Contact con = result.get(--num);
                        con.displayInfo();
                        recordMenu(con,num,contacts);

                    } catch (NumberFormatException e) {
                        System.out.println(e + " is not a number");
                    }
            }
        }
    }

    void updateContact(Contact con){
        if(con instanceof PersonContact){
            System.out.println(" Select a field (name, surname, birth, gender, number): ");
            String response = scn.nextLine().toUpperCase();
            switch(response) {
                case "NAME":
                    System.out.println("Enter name: ");
                    ((PersonContact) con).setName(scn.nextLine());
                    break;
                case "SURNAME":
                    System.out.println("Enter surname: ");
                    ((PersonContact) con).setName(scn.nextLine());
                    break;
                case "BIRTH":
                    System.out.println("Enter birthdate");
                    ((PersonContact) con).setDob(scn.nextLine());
                    break;
                case "GENDER":
                    System.out.println("Enter gender: ");
                    ((PersonContact) con).setGender(scn.nextLine());
                    break;
                case "NUMBER":
                    System.out.println("Enter number: ");
                    ((PersonContact) con).setNumber(scn.nextLine());
                    break;
                default:
                    throw new IllegalArgumentException("Illegal Argument "+response);

            }

        } else {
            System.out.println(" Select a field (name, address, number): ");
            String response = scn.nextLine().toUpperCase();

            switch(response) {
                case "NAME":
                    System.out.println("Enter name: ");
                    ((OrgContact)con).setName(scn.nextLine());
                    break;
                case "ADDRESS":
                    System.out.println("Enter address: ");
                    ((OrgContact)con).setAddress(scn.nextLine());
                    break;
                case "NUMBER":
                    System.out.println("Enter number: ");
                    ((OrgContact)con).setNumber(scn.nextLine());
                    break;
                default:
                    throw new IllegalArgumentException("Wrong Option "+response);
            }
        }
        System.out.println("Saved");
        con.displayInfo();
    }

    public void listContacts(ArrayList<Contact> contactList) {
        if (contactList.size() < 1) {
            System.out.println("No contacts to display");
        } else {
            int index = 0;
            for (Contact c : contactList) {
                System.out.println(++index +". "+c.toString());

            }
            System.out.println("[list] Enter Action ([number], back): ");
            String response = scn.nextLine().toUpperCase();
            switch(response){
                case "BACK":
                    break;
                default:
                    int number = Integer.parseInt(response);
                    contact = contactList.get(--number);
                    contact.displayInfo();
                    System.out.println();
                    recordMenu(contact,number,contactList);
            }
        }
    }
    public void deleteContact(ArrayList<Contact> contacts, int number){
        contacts.remove(number);
    }
    public void recordMenu(Contact contact, int number, ArrayList<Contact> contactList){
        System.out.println("[record] Enter action (edit, delete, menu): ");
        String response = scn.nextLine().toUpperCase();
        switch (response){
            case "EDIT":
                updateContact(contact);
                recordMenu(contact,number,contactList);
                break;
            case "DELETE":
                deleteContact(contactList,number);
                recordMenu(contact,number,contactList);
                break;
            default:
                break;

        }

    }
}
