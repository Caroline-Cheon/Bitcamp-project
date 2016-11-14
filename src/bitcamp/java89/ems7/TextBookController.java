package bitcamp.java89.ems7;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class TextBookController {
  private ArrayList<TextBook> booklist;
  private Scanner keyScan;
  private boolean change;
  

  public TextBookController(Scanner keyScan) throws Exception {
    booklist = new ArrayList<TextBook>();
    this.keyScan = keyScan;
    this.doLoad();
  } 
  public void service() {
    loop:
    while(true) {
      System.out.print("교재관리> ");
      String command = keyScan.nextLine().toLowerCase();
      try {
        switch (command) {
        case "add": this.doAdd(); break;
        case "list": this.doList(); break;
        case "view": this.doView(); break;
        case "delete": this.doDelete(); break;
        case "update": this.doUpdate(); break;
        case "save": this.doSave(); break;
        case "quit": 
          if(this.doQuit()) {
          break loop;
          } 
          break;
        default :
          System.out.println("지원하지 않는 명령입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다1.");
      } catch (Exception e) {
        System.out.println("실행 중 오류가 발생했습니다2.");
      } //try - catch   
    } //while
  }
  private void doList() {
    for (TextBook textbook : booklist) {
      System.out.printf("%s,%s,%s,%s,%s,%s,%d,%d\n",
        textbook.title,
        textbook.author,
        textbook.press,
        textbook.releaseDate,
        textbook.language,
        textbook.description,
        textbook.page,
        textbook.price);
    }
  }
  private void doLoad() {
    try { 
      FileInputStream in = new FileInputStream("src/bitcamp/temp/EduApp.data");
      ObjectInputStream in2 = new ObjectInputStream(in);
      booklist = (ArrayList<TextBook>)in2.readObject();

      in2.close();
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean doQuit() throws Exception {
    if (change == true) {
      System.out.print("책 정보가 변경되었습니다. 그래도 종료하시겠습니까?(y/n) ");
      if (EduApp.keyScan.nextLine().equals("y")) {
        System.out.println("학생 정보가 변경된 것을 취소하고 종료합니다.");
        System.out.println("Good bye!");
        return true;
      } return false;
    } 
    return true;
  }

  private void doSave() throws Exception {
    FileOutputStream out = new FileOutputStream("src/bitcamp/temp/EduApp.data");
    ObjectOutputStream out2 = new ObjectOutputStream(out);
 
    out2.writeObject(booklist);
    System.out.println("저장하였습니다.");

    out2.close();
    out.close();
    change = false;
  }

  private void doAdd() throws Exception { 
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

      booklist.add(textbook);
  
      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
      System.out.println();
    } //while
  change = true;
  } 

  private void doView() {
    System.out.print("책이름의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    TextBook textbook = booklist.get(index);

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
    System.out.print("삭제할 리스트의 인덱스는? ");
    int index = Integer.parseInt(keyScan.nextLine());
    TextBook deletedBook = booklist.remove(index);
    System.out.printf("%s 책 정보를 삭제하였습니다.\n", deletedBook.title);
    change = true;
  }

  private void doUpdate() {
    System.out.print("변결할 책이름의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    TextBook oldBook = booklist.get(index);

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
      if (keyScan.nextLine().toLowerCase().equals("y")) {
        textbook.title = oldBook.title;
        booklist.set(index, textbook);
        System.out.println("저장하였습니다.");
        change = true;
      } else{
        System.out.println("변경을 취소하였습니다.");
      }
  }
}
  
