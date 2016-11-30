package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.annotation.RequestParam;
import bitcamp.java89.ems.server.dao.TextBookDao;
import bitcamp.java89.ems.server.vo.TextBook;

@Component
public class TextBookController {
  //의존 객체 DAO를 저장할 변수 선언
  TextBookDao textbookDao;
  
  //의존 객체 주입할 때 호출할 셋터 추가
  public void setTextbookDao(TextBookDao textbookDao) {
    this.textbookDao = textbookDao;
  }

  @RequestMapping(value="textbook/add")
  public void add(
      @RequestParam("title") String title,
      @RequestParam("author") String author,
      @RequestParam("press") String press,
      @RequestParam("releaseDate") int releaseDate,
      @RequestParam("language") String language,
      @RequestParam("description") String description,
      @RequestParam("page") int page,
      @RequestParam("price") int price,
      PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
      if (textbookDao.existTitle(title)) {
        out.println("같은 책이름이 존재합니다. 등록을 취소합니다.");
        return;
      }
      
      TextBook textbook = new TextBook();
      textbook.setTitle(title);
      textbook.setAuthor(author);
      textbook.setPress(press);
      textbook.setReleaseDate(releaseDate);
      textbook.setLanguage(language);
      textbook.setDescription(description);
      textbook.setPage(page);
      textbook.setPrice(price);
      
      textbookDao.insert(textbook);
      out.println("등록하였습니다.");
  }
  
  @RequestMapping(value="textbook/delete")
  public void delete(@RequestParam("title") String title, PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
      if (!textbookDao.existTitle(title)) {
        out.println("해당 데이터가 없습니다");
        return;
      }
      textbookDao.delete(title);
      out.println("해당 데이터 삭제 완료하였습니다.");
  }
  
  @RequestMapping(value="textbook/list")
  public void list(PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
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
  
  @RequestMapping(value="textbook/update")
  public void update(
      @RequestParam("title") String title,
      @RequestParam("author") String author,
      @RequestParam("press") String press,
      @RequestParam("releaseDate") int releaseDate,
      @RequestParam("language") String language,
      @RequestParam("description") String description,
      @RequestParam("page") int page,
      @RequestParam("price") int price,
      PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
      if (!textbookDao.existTitle(title)) {
        out.println("책이름을 찾지 못했습니다.");
        return;
      }
      
      TextBook textbook = new TextBook();
      textbook.setTitle(title);
      textbook.setAuthor(author);
      textbook.setPress(press);
      textbook.setReleaseDate(releaseDate);
      textbook.setLanguage(language);
      textbook.setDescription(description);
      textbook.setPage(page);
      textbook.setPrice(price);
      
      textbookDao.update(textbook);
      out.println("변경하였습니다.");
  }
  
  @RequestMapping(value="textbook/view")
  public void view(@RequestParam("title") String title, PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
      ArrayList<TextBook> list = textbookDao.getListByTitle(title);
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
}
