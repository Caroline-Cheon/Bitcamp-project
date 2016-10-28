package bitcamp.java89.ems;
import java.util.Scanner;

public class EduApp {
  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {
    //EduApp에서 사용하는 keyScan을 TextBookController와 공유!
    TextBookController.keyScan = keyScan;

    System.out.println("비트캠프 관리시스템에 오신 걸 환영합니다.");
    loop:
    while(true) {
      System.out.println("명령하세요, (보기:'add','list','view','quit')");
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
        case "add": TextBookController.doAdd(); break;
        case "list": TextBookController.doList(); break;
        case "view": TextBookController.doView(); break;
        case "quit": System.out.println("굿바이"); break loop;
        default :
          System.out.println("지원하지 않는 명령입니다.");
          break;
      }

      System.out.print("계속 명령하시겠습니까(y/n)?");
      if(!keyScan.nextLine().toLowerCase().equals("y"))
        break;
    }
  }
}
