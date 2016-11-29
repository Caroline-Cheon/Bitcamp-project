package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

@Component(value="contact/view")
public class ContactViewController extends AbstractCommand {
  //의존 객체 DAO를 저장할 변수 선언
  ContactDao contactDao;
  
  //의존 객체 주입할 때 호출할 셋터 추가
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }

  @Override
  protected void doResponse(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
      ArrayList<Contact> list = contactDao.getListByName(paramMap.get("name"));
      for (Contact contact : list) {
        out.println("------------------------------");
        out.printf("이름: %s\n", contact.getName());
        out.printf("직위: %s\n", contact.getPosition());
        out.printf("전화: %s\n", contact.getTel());
        out.printf("이메일: %s\n", contact.getEmail());
      }
  }
}
