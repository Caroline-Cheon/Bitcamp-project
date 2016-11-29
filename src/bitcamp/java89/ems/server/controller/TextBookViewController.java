package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.TextBookDao;
import bitcamp.java89.ems.server.vo.TextBook;

@Component(value="textbook/view")
public class TextBookViewController {
  //의존 객체 DAO를 저장할 변수 선언
  TextBookDao textbookDao;
  
  //의존 객체 주입할 때 호출할 셋터 추가
  public void setTextbookDao(TextBookDao textbookDao) {
    this.textbookDao = textbookDao;
  }
  
  @RequestMapping
  public void view(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
      ArrayList<TextBook> list = textbookDao.getListByTitle(paramMap.get("title"));
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
