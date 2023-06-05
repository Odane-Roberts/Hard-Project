package contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {
    private Contact contact;
    private static PhoneBookUtil app = new PhoneBookUtil();
    private static ArrayList<Contact> contactList = new ArrayList<>();
    private static Scanner scn = new Scanner(System.in);
    private static File file ;

    public static void main(String[] args) {
        //new File(args[0]);
        menu();
    }
    static void menu(){
        System.out.println("[menu] Enter Action (add, list, search, count, exit): ");
        String action = scn.nextLine().toUpperCase();

        switch(action) {
            case "ADD":
                System.out.println("Enter the type (person, organization): ");
                contactList.add(app.createContact(scn.nextLine()));
                System.out.println();
                menu();
                break;
            case "COUNT":
                app.countContacts(contactList);
                System.out.println();
                menu();
                break;
            case "LIST":
                app.listContacts(contactList);
                System.out.println();
                menu();
                break;
            case "SEARCH":
                app.searchContact(contactList);
                System.out.println();
                menu();
                break;
            case "EXIT":
                System.out.println();
                System.out.println("Goodbye");
                return;
            default:
                throw new IllegalArgumentException("Invalid choice: "+action);
        }
    }
}
