package bitcamp.java89.ems.server.controller;
 
import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.TextBookDao;

public class TextBookDeleteController extends AbstractCommand {
  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
      TextBookDao textbookDao = TextBookDao.getInstance();
      if (!textbookDao.existTitle(paramMap.get("title"))) {
        out.println("해당 데이터가 없습니다");
        return;
      }
      textbookDao.delete(paramMap.get("title"));
      out.println("해당 데이터 삭제 완료하였습니다.");
  }
}
