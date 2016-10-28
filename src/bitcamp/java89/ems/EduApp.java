package bitcamp.java89.ems;
import java.util.Scanner;

public class EduApp {
  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {
    TextBookController textBookController = new TextBookController(keyScan);

    System.out.println("비트캠프 관리시스템에 오신 걸 환영합니다.");
    loop:
    while(true) {
      System.out.println("명령하세요, (보기:'add','list','view','update','delete,''quit')");
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
        case "add": textBookController.doAdd(); break;
        case "list": textBookController.doList(); break;
        case "view": textBookController.doView(); break;
        case "update": textBookController.doUpdate(); break;
        case "delete": textBookController.doDelete(); break;
        case "quit": System.out.println("굿바이"); break loop;
        default :
          System.out.println("지원하지 않는 명령입니다.");
          break;
      }
    }
  }
}
