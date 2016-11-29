package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.TextBookDao;
import bitcamp.java89.ems.server.vo.TextBook;

@Component(value="textbook/add")
public class TextBookAddController {
  //의존 객체 DAO를 저장할 변수 선언
  TextBookDao textbookDao;
  
  //의존 객체 주입할 때 호출할 셋터 추가
  public void setTextbookDao(TextBookDao textbookDao) {
    this.textbookDao = textbookDao;
  }

  @RequestMapping
  public void add(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
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
}
