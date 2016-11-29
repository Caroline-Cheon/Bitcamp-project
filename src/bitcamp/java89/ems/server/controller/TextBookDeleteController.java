package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.TextBookDao;

@Component(value="textbook/delete")
public class TextBookDeleteController extends AbstractCommand {
  //의존 객체 DAO를 저장할 변수 선언
  TextBookDao textbookDao;
  
  //의존 객체 주입할 때 호출할 셋터 추가
  public void setTextbookDao(TextBookDao textbookDao) {
    this.textbookDao = textbookDao;
  }

  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
      if (!textbookDao.existTitle(paramMap.get("title"))) {
        out.println("해당 데이터가 없습니다");
        return;
      }
      textbookDao.delete(paramMap.get("title"));
      out.println("해당 데이터 삭제 완료하였습니다.");
  }
}
