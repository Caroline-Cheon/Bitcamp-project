package bitcamp.java89.ems.server.controller; 

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TextBookDao;
import bitcamp.java89.ems.server.vo.TextBook;

public class TextBookAddController extends AbstractCommand {
  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
      TextBookDao textbookDao = TextBookDao.getInstance();
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
