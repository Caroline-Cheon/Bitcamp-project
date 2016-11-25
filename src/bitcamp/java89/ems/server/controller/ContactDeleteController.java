package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ContactDao;

public class ContactDeleteController extends AbstractCommand {
  @Override
  public String getCommandString() {
    return "contact/delete";
  }
  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
      ContactDao contactDao = ContactDao.getInstance();
      if (!contactDao.existEmail(paramMap.get("email"))) {
        out.println("해당 데이터가 없습니다.");
        return;
      }
      contactDao.delete(paramMap.get("email"));
      out.println("해당 데이터 삭제 완료하였습니다.");
  }
}
