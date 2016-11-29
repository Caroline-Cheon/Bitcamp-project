package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

@Component // ApplicationContext가 관리하는 대상 클래스임을 태깅한다.
public class ContactController {
  // 의존 객체 DAO를 저장할 변수 선언
  ContactDao contactDao;
  
  // 의존 객체 주입할 때 호출할 셋터 추가
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }

  @RequestMapping(value="contact/add")
  public void add(HashMap<String, String> paramMap, PrintStream out) throws Exception {
      if (contactDao.existEmail(paramMap.get("email"))) {
        out.println("같은 이메일이 존재합니다. 등록을 취소합니다.");
        return;
      }
      
      Contact contact = new Contact();
      contact.setName(paramMap.get("name"));
      contact.setPosition(paramMap.get("position"));
      contact.setTel(paramMap.get("tel"));
      contact.setEmail(paramMap.get("email"));
      
      contactDao.insert(contact);
      out.println("등록하였습니다.");
  }
  @RequestMapping(value="contact/delete")
  public void delete(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
      if (!contactDao.existEmail(paramMap.get("email"))) {
        out.println("해당 데이터가 없습니다.");
        return;
      }
      contactDao.delete(paramMap.get("email"));
      out.println("해당 데이터 삭제 완료하였습니다.");
  }
  
  @RequestMapping(value="contact/list")
  public void list(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
    ArrayList<Contact> list = contactDao.getList();
    for (Contact contact : list) {
      out.printf("%s,%s,%s,%s\n",
          contact.getName(),
          contact.getPosition(),
          contact.getTel(),
          contact.getEmail());
    }
  }
  
  @RequestMapping(value="contact/update")
  public void update(HashMap<String, String> paramMap, PrintStream out) throws Exception {
    // 주입 받은 contactDao를 사용할 것이기 때문에 더이상 이 메서드에서 ContactDao객체를 준비하지 않는다.
    // => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
      if (!contactDao.existEmail(paramMap.get("email"))) {
        out.println("이메일을 찾지 못했습니다.");
        return;
      }
      
      Contact contact = new Contact();
      contact.setEmail(paramMap.get("email"));
      contact.setName(paramMap.get("name"));
      contact.setPosition(paramMap.get("position"));
      contact.setTel(paramMap.get("tel"));
      
      contactDao.update(contact);
      out.println("변경 하였습니다.");
    
  }
  
  @RequestMapping(value="contact/view")
  public void view(HashMap<String, String> paramMap, PrintStream out) throws Exception {
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