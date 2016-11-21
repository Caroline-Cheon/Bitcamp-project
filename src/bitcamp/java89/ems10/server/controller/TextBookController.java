package bitcamp.java89.ems10.server.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import bitcamp.java89.ems10.server.vo.TextBook;

public class TextBookController {
  private Scanner in;
  private PrintStream out;
  
  private String filename = "textbook2.data";
  private ArrayList<TextBook> booklist;
  private boolean changed;
  private Scanner keyScan;  

  public TextBookController(Scanner in, PrintStream out) {
    booklist = new ArrayList<TextBook>();
    this.in = in;
    this.out = out;

    this.load();
  } 

  public boolean isChanged() {
    return changed;
  }
  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;

    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      booklist = (ArrayList<TextBook>)in.readObject();
      
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
    ObjectOutputStream out = new ObjectOutputStream(out0);

    out.writeObject(booklist);
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
        textbook.getTitle(),
        textbook.getAuthor(),
        textbook.getPress(),
        textbook.getReleaseDate(),
        textbook.getLanguage(),
        textbook.getDescription(),
        textbook.getPage(),
        textbook.getPrice());
    }
  }

  private void doAdd() throws Exception { 
    while (true) {
      TextBook textbook = new TextBook();

      System.out.print("책이름(ex: java's best practice)? ");
      textbook.setTitle(this.keyScan.nextLine());

      System.out.print("저자(ex: mr.Nam)? ");
      textbook.setAuthor(this.keyScan.nextLine());

      System.out.print("출판사(ex: Dow)? ");
      textbook.setPress(this.keyScan.nextLine());

      System.out.print("출판년도(ex: 2016)? ");
      textbook.setReleaseDate(Integer.parseInt(this.keyScan.nextLine()));

      System.out.print("언어(ex: Korean)? ");
      textbook.setLanguage(this.keyScan.nextLine());

      System.out.print("설명(ex: Do you want to feel java from basic to OOP?) ");
      textbook.setDescription(this.keyScan.nextLine());

      System.out.print("쪽수(ex: 520)? ");
      textbook.setPage(Integer.parseInt(this.keyScan.nextLine()));

      System.out.print("가격(ex: 30000)? ");
      textbook.setPrice(Integer.parseInt(this.keyScan.nextLine()));

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

    System.out.printf("책이름: %s\n", textbook.getTitle());
    System.out.printf("저자: %s\n", textbook.getAuthor());
    System.out.printf("출판사: %s\n", textbook.getPress());
    System.out.printf("출판년도: %s\n", textbook.getReleaseDate());
    System.out.printf("언어: %s\n", textbook.getLanguage());
    System.out.printf("설명: %s\n", textbook.getDescription());
    System.out.printf("쪽수: %d\n", textbook.getPage());
    System.out.printf("가격: %d\n",textbook.getPrice());
    System.out.println();
  }


  private void doDelete() {
    System.out.print("삭제할 리스트의 인덱스는? ");
    int index = Integer.parseInt(keyScan.nextLine());
    TextBook deletedBook = booklist.remove(index);
    changed = true;
    System.out.printf("%s 책 정보를 삭제하였습니다.\n", deletedBook.getTitle());

  }

  private void doUpdate() {
    System.out.print("변결할 책이름의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    TextBook oldBook = booklist.get(index);

    //새 책 정보를 입력받는다.
    TextBook textbook = new TextBook();

    System.out.printf("저자(%s)? ", oldBook.getAuthor());
    textbook.setAuthor(this.keyScan.nextLine());

    System.out.printf("출판사(%s)? ", oldBook.getPress());
    textbook.setPress(this.keyScan.nextLine());

    System.out.printf("출판년도(%d)? ", oldBook.getReleaseDate());
    textbook.setReleaseDate(Integer.parseInt(this.keyScan.nextLine()));

    System.out.printf("언어(%s)? ", oldBook.getLanguage());
    textbook.setLanguage(this.keyScan.nextLine()); 

    System.out.printf("설명(%s) ", oldBook.getDescription());
    textbook.setDescription(this.keyScan.nextLine());

    System.out.printf("쪽수(%d)? " ,oldBook.getPage());
    textbook.setPage(Integer.parseInt(this.keyScan.nextLine()));

    System.out.printf("가격(%d)? ", oldBook.getPrice());
    textbook.setPrice(Integer.parseInt(this.keyScan.nextLine()));

    System.out.print("저장하시겠습니까(y/n)?");
      if (keyScan.nextLine().toLowerCase().equals("y")) {
        textbook.setTitle(oldBook.getTitle());
        booklist.set(index, textbook);
        changed = true;
        System.out.println("저장하였습니다.");
      } else{
        System.out.println("변경을 취소하였습니다.");
      }
  }
}
  
