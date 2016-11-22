/* 작업내용: 저장 기능 추가
- changed 변수 추가
- isChanged() 추가
- save() 추가 
*/
package bitcamp.java89.ems7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TextBookController {
  private String filename = "textbook.data";
  private ArrayList<TextBook> booklist;
  private boolean changed;
  private Scanner keyScan;  

  public TextBookController(Scanner keyScan) {
    booklist = new ArrayList<TextBook>();
    this.keyScan = keyScan;

    this.load();
  } 

  public boolean isChanged() {
    return changed;
  }
  private void load() {
    FileInputStream in0 = null;
    DataInputStream in = null;

    try {
      in0 = new FileInputStream(this.filename);
      in = new DataInputStream(in0);

      while (true) {
        TextBook textbook = new TextBook(); // 빈 객체 생성

        textbook.title = in.readUTF();    // 데이터 저장
        textbook.author = in.readUTF();
        textbook.press = in.readUTF();
        textbook.releaseDate = in.readInt();
        textbook.language = in.readUTF();
        textbook.description = in.readUTF();
        textbook.page = in.readInt();
        textbook.price = in.readInt();
        this.booklist.add(textbook);    // 목록에 책 객체 저장
      }
    } catch (EOFException e) {
      //파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("책 데이터 로딩 중 오류 발생!");
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {
        // close하다가 예외 발생하면 무시한다.
      }
    }
  }

  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    DataOutputStream out = new DataOutputStream(out0);

    for (TextBook textbook : this.booklist) {
      out.writeUTF(textbook.title);
      out.writeUTF(textbook.author);
      out.writeUTF(textbook.press);
      out.writeInt(textbook.releaseDate);
      out.writeUTF(textbook.language);
      out.writeUTF(textbook.description);
      out.writeInt(textbook.page);
      out.writeInt(textbook.price);
    }
    changed = false;

    out.close();
    out0.close();
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
        case "main":
          break loop;
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
      changed = true;

      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
      System.out.println();
    } //while
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
    changed = true;
    System.out.printf("%s 책 정보를 삭제하였습니다.\n", deletedBook.title);

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
        changed = true;
        System.out.println("저장하였습니다.");
      } else{
        System.out.println("변경을 취소하였습니다.");
      }
  }
}
  
