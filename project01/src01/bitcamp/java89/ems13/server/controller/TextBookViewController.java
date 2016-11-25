package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TextBookDao;
import bitcamp.java89.ems.server.vo.TextBook;

public class TextBookViewController implements Command {
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    try {
      TextBookDao textbookDao = TextBookDao.getInstance();
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
