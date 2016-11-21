//버전 1.6
package bitcamp.java89.ems10.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import bitcamp.java89.ems10.server.controller.ContactController;
import bitcamp.java89.ems10.server.controller.TextBookController;

public class EduAppServer {
  static Scanner in;
  static PrintStream out;
  
  static Scanner keyScan = new Scanner(System.in);
  static TextBookController textBookController;
  static ContactController contactController;
  // 스태틱 멤버로 지정하여 다른 메서드가 참조할 수 있도록 한다.

  public static void main(String[] args) throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중");
    
    Socket socket = ss.accept();
    in = new Scanner(new BufferedInputStream(socket.getInputStream()));
    out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
    
    textBookController = new TextBookController(in, out);
    contactController = new ContactController(in, out);

    out.println("비트캠프 관리시스템에 오신 걸 환영합니다.");
    
    
    loop:
    while (true) {
      // 클라이언트에게 데이터를 전송한다.
      out.println("명령> ");
      out.println(); // 빈 줄은 데이터의 끝을 의미한다(프로토콜)

      // 클라이언트로부터 명령을 읽는다
      String command = in.nextLine().toLowerCase();
      
      switch (command) {
        case "menu": doMenu(); break;
        case "go 1": textBookController.service(); break;
        case "go 5": contactController.service(); break;
        case "save": doSave(); break;
        case "quit": 
          if (doQuit())
            break loop;
          break;
        default :
          out.println("지원하지 않는 명령어입니다.");
          break;
      }
    }
    
    in.close();
    out.close();
    socket.close();
    ss.close();
  }
  static void doMenu() {
    out.println("[메뉴]");
    out.println("1. 교재관리 ");
    out.println("5. 연락처관리 ");
    out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요. ");
    out.println("[명령]");
    out.println("save   데이터 저장");
    out.println("quit   프로그램 종료");
  }
  static boolean doQuit() {
    boolean changed = textBookController.isChanged();
    if (changed) {
      System.out.print("책 정보가 변경되었습니다. 그래도 종료하시겠습니까?(y/n) ");
      if (!keyScan.nextLine().equals("y"))
        return false;
      System.out.println("학생 정보가 변경된 것을 취소하고 종료합니다.");
    }
    System.out.println("Good bye!");
    return true;
  }

  static void doSave() {
    try {
      textBookController.save();
      contactController.save();
      out.println("저장하였습니다.");
    } catch (Exception e) {
      out.println("파일 저장 중에 오류가 발생했습니다.");
    }
  }
}
