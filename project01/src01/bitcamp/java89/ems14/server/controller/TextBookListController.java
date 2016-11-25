package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TextBookDao;
import bitcamp.java89.ems.server.vo.TextBook;

public class TextBookListController extends AbstractCommand {
  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
      TextBookDao textbookDao = TextBookDao.getInstance();
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
}
