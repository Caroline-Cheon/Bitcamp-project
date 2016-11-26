package bitcamp.java89.ems.server.controller; 

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.TextBookDao;
import bitcamp.java89.ems.server.vo.TextBook;

public class TextBookUpdateController implements Command {
  private TextBookDao textbookDao;
  
  public TextBookUpdateController() {
    textbookDao = TextBookDao.getInstance();
  } 

  public void service(HashMap<String, String> paramMap, PrintStream out) {
    if (!textbookDao.existTitle(paramMap.get("title"))) {
      out.println("책이름을 찾지 못했습니다.");
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
    
    textbookDao.update(textbook);
    out.println("변경하였습니다.");
  }
  
 
}
