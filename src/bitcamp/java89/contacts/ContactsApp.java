package bitcamp.java89.contacts;

import java.util.Scanner;

public class ContactsApp {
  static Scanner keyScan = new Scanner(System.in);
  static ContactsController contactsController;
  
  public static void main(String[] args) throws Exception {
    contactsController = new ContactsController(keyScan);
    
    System.out.println("연락처 관리시스템에 오신 걸 환영합니다.");
    loop:
    while (true) {
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();
      
      switch (command) {
      case "add": contactsController.doAdd(); break;
      case "list": contactsController.doList(); break;
      case "view": contactsController.doView(); break;
      case "delete": contactsController.doDelete(); break;
      case "quit":
        break loop;
      default :
        System.out.println("지원하지 않는 명령어입니다.");
        break;
      }
    }
  }
}
