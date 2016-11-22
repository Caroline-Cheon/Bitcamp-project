package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.dao.TextBookDao;
import bitcamp.java89.ems.server.vo.TextBook;

public class TextBookController {
  private Scanner in;
  private PrintStream out;
  
  private TextBookDao textbookDao;
  
  public TextBookController(Scanner in, PrintStream out) {
    this.in = in;
    this.out = out;

    textbookDao = TextBookDao.getInstance();
  } 

  public boolean isChanged() {
    return textbookDao.isChanged();
  }

  public void save() throws Exception {
    if (textbookDao.isChanged()) {
      textbookDao.save();
    }
  }

  public boolean service() {
    
    while(true) {
      out.println("교재관리> ");
      out.println();
      
      String[] commands = in.nextLine().split("\\?");
      
      try {
        switch (commands[0]) {
        case "add": this.doAdd(commands[1]); break;
        case "list": this.doList(); break;
        case "view": this.doView(commands[1]); break;
        case "delete": this.doDelete(commands[1]); break;
        case "update": this.doUpdate(commands[1]); break;
        case "main": return true;
        case "quit": return false;
        default :
          System.out.println("지원하지 않는 명령입니다.");
          break;
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다1.");
      } catch (Exception e) {
        System.out.println("실행 중 오류가 발생했습니다2.");
      } //try - catch   
    } //while
  }
  private void doList() {
    ArrayList<TextBook> list = textbookDao.getList();
    for (TextBook textbook : list) {
      out.printf("%s,%s,%s,%s,%s,%s,%d,%d\n",
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
  
  private void doUpdate(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    if (!textbookDao.existTitle(paramMap.get("title"))) {
      out.println("책이름을 찾지 못했습니다.");
      return;
    }
    
    TextBook textbook = new TextBook();
    textbook.setAuthor(paramMap.get("author"));
    textbook.setPress(paramMap.get("press"));
    textbook.setReleaseDate(Integer.parseInt(paramMap.get("releaseDate")));
    textbook.setLanguage(paramMap.get("language"));
    textbook.setDescription(paramMap.get("description"));
    textbook.setPage(Integer.parseInt(paramMap.get("page")));
    textbook.setPrice(Integer.parseInt(paramMap.get("price")));
    
    textbookDao.update(textbook);
    out.println("변경하였습니다.");
  }
//클라이언트에서 보낸 데이터 형식
  // => add?title=자바의세계&author=천재&press=비트센터&releaseDate=2016&language=eng&description=justdoit&page=300&price=10000
  // => doAdd("add?title=자바의세계&author=천재&press=비트센터&releaseDate=2016&language=eng&description=justdoit&page=300&price=10000") 

  private void doAdd(String params) {
    String[] values = params.split("&");
    HashMap<String,String> paramMap = new HashMap<>();
    
    for (String value : values) {
      String[] kv = value.split("=");
      paramMap.put(kv[0], kv[1]);
    }
    if (textbookDao.existTitle(paramMap.get("title"))) {
      out.println("같은 책이름이 존재합니다. 등록을 취소합니다.");
      return;
    }

    TextBook textbook = new TextBook();
    textbook.setTitle(paramMap.get("title"));
    textbook.setAuthor(paramMap.get("author"));
    textbook.setPress(paramMap.get("press"));
    textbook.setReleaseDate(Integer.parseInt(paramMap.get("releaseDate")));
    textbook.setLanguage(paramMap.get("language"));
    textbook.setDescription(paramMap.get("description"));
    textbook.setPage(Integer.parseInt(paramMap.get("page")));
    textbook.setPrice(Integer.parseInt(paramMap.get("price")));
    
    textbookDao.insert(textbook);
    out.println("등록하였습니다.");
  } 
 
  private void doView(String params) {
    String[] kv = params.split("=");
    
    ArrayList<TextBook> list = textbookDao.getListByTitle(kv[1]);
    for (TextBook textbook : list) {
      out.println("------------------------------");
      out.printf("책이름: %s\n", textbook.getTitle());
      out.printf("저자: %s\n", textbook.getAuthor());
      out.printf("출판사: %s\n", textbook.getPress());
      out.printf("출판년도: %s\n", textbook.getReleaseDate());
      out.printf("언어: %s\n", textbook.getLanguage());
      out.printf("설명: %s\n", textbook.getDescription());
      out.printf("쪽수: %d\n", textbook.getPage());
      out.printf("가격: %d\n",textbook.getPrice());
    }
  }


  private void doDelete(String params) {
    String[] kv = params.split("=");
    
    if (!textbookDao.existTitle(kv[1])) {
      out.println("해당 데이터가 없습니다");
      return;
    }
    textbookDao.delete(kv[1]);
    out.println("해당 데이터 삭제 완료하였습니다.");
  }
}
