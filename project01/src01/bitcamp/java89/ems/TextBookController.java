package bitcamp.java89.ems;
import java.util.Scanner;

public class TextBookController {
  private Box head;
  private Box tail;
  private int length;
  private Scanner keyScan;

//기본 생성자가 없다. 따라서 이 클래스를 사용하려면 반드시 Scanner를 줘야 한다.
// => 생성자에서 하는 일은 그 객체를 사용하기 전에 유효상태로 만드는 것이다.
  public TextBookController(Scanner keyScan) {
    head = new Box();
    tail = head;
    length = 0;
    this.keyScan = keyScan;
  }

  public void service() {
    loop:
    while(true) {
      System.out.print("교재관리> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
        case "add": this.doAdd(); break;
        case "list": this.doList(); break;
        case "view": this.doView(); break;
        case "delete": this.doDelete(); break;
        case "update": this.doUpdate(); break;
        case "main": break loop;
        default :
          System.out.println("지원하지 않는 명령입니다.");
          break;
      }
    }
  }
  // 아래 doxx() 메서드들은 오직 service()에서만 호출하기 때문에
  // private으로 접근을 제한한다.
  private void doList() {
    Box currentBox = head;
    while (currentBox != null && currentBox != tail) {
      TextBook textbook = (TextBook) currentBox.value;
      System.out.printf("%s,%s,%s,%s,%s,%s,%d,%d\n",
      textbook.title,
      textbook.author,
      textbook.press,
      textbook.releaseDate,
      textbook.language,
      textbook.description,
      textbook.page,
      textbook.price);
      currentBox = currentBox.next;
    }
  }
  private void doAdd() {
    while (true) {
      TextBook textbook = new TextBook();

      System.out.print("책이름(ex: java's best practice)? ");
      textbook.title = this.keyScan.nextLine();

      System.out.print("저자(ex: mr.Nam)? ");
      textbook.author = this.keyScan.nextLine();

      System.out.print("출판사(ex: Dow)? ");
      textbook.press = this.keyScan.nextLine();

      System.out.print("출판년도(ex: 2016)? ");
      textbook.releaseDate = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("언어(ex: Korean)? ");
      textbook.language = this.keyScan.nextLine();

      System.out.print("설명(ex: Do you want to feel java from basic to OOP?) ");
      textbook.description = this.keyScan.nextLine();

      System.out.print("쪽수(ex: 520)? ");
      textbook.page = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("가격(ex: 30000)? ");
      textbook.price = Integer.parseInt(this.keyScan.nextLine());
//tail이 가리키는 빈 상자에 TextBook 인스턴스의 주소를 담는다.
//그리고 새 상자를 만든 다음 현재 상자에 연결한다.
//tail은 다시 맨 마지막 상자를 가리킨다.
      tail.value = textbook;
      tail.next = new Box();
      tail = tail.next;
      length++;

      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
      System.out.println();
    }
  }

   private void doView() {
    System.out.print("책이름의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if (index < 0 || index >= length) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }

    Box currentBox = head;
    for(int i = 0; i < index; i++) {
      currentBox = currentBox.next;
    }

    TextBook textbook = (TextBook)currentBox.value;

    System.out.printf("책이름: %s\n", textbook.title);
    System.out.printf("저자: %s\n", textbook.author);
    System.out.printf("출판사: %s\n", textbook.press);
    System.out.printf("출판년도: %s\n", textbook.releaseDate);
    System.out.printf("언어: %s\n", textbook.language);
    System.out.printf("설명: %s\n", textbook.description);
    System.out.printf("쪽수: %d\n", textbook.page);
    System.out.printf("가격: %d\n",textbook.price);
    System.out.println();
   }


  private void doDelete() {
    System.out.println("삭제할 리스트의 인덱스는? ");
    int index = Integer.parseInt(keyScan.nextLine());

    if (index < 0 || index >= length) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }

    TextBook deletedBook = null;
    if (index == 0) {
      deletedBook = (TextBook)head.value;
      head = head.next;
    } else {
      Box currentBox = head;
      for(int i = 0; i < (index - 1); i++) {
        currentBox = currentBox.next;
      }
      deletedBook = (TextBook)currentBox.next.value;
      currentBox.next = currentBox.next.next;
      }
    length--;
    System.out.printf("%s 책 정보를 삭제하였습니다.\n", deletedBook.title);
   }

  private void doUpdate() {
    System.out.print("변결할 책이름의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    //유효한 인덱스인지 검사
    if (index < 0 || index >= length) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }
    // 변경하려는 책 정보가 저장된 상자를 찾는다.
    Box currentBox = head;
    for(int i = 0; i < index; i++) {
      currentBox = currentBox.next;
    }
    //찾은 상자에서 변경할 책의 정보를 꺼낸다.
    TextBook oldBook = (TextBook)currentBox.value;

    //새 책 정보를 입력받는다.
    TextBook textbook = new TextBook();

    System.out.printf("저자(%s)? ", oldBook.author);
    textbook.author = this.keyScan.nextLine();

    System.out.printf("출판사(%s)? ", oldBook.press);
    textbook.press = this.keyScan.nextLine();

    System.out.printf("출판년도(%d)? ", oldBook.releaseDate);
    textbook.releaseDate = Integer.parseInt(this.keyScan.nextLine());

    System.out.printf("언어(%s)? ", oldBook.language);
    textbook.language = this.keyScan.nextLine();

    System.out.printf("설명(%s) ", oldBook.description);
    textbook.description = this.keyScan.nextLine();

    System.out.printf("쪽수(%d)? " ,oldBook.page);
    textbook.page = Integer.parseInt(this.keyScan.nextLine());

    System.out.printf("가격(%d)? ", oldBook.price);
    textbook.price = Integer.parseInt(this.keyScan.nextLine());

    System.out.print("저장하시겠습니까(y/n)?");
        if(keyScan.nextLine().toLowerCase().equals("y")) {
          textbook.title = oldBook.title;
          currentBox.value = textbook;
          System.out.println("저장하였습니다.");
        }else{
          System.out.println("변경을 취소하였습니다.");
        }
    }
  }
