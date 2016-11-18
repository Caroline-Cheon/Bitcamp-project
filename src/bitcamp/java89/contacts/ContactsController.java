package bitcamp.java89.contacts;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactsController {
  private String filename = "contacts.data";
  private ArrayList<Contacts> list;
  private Scanner keyScan;
  
  public ContactsController(Scanner keyScan) {
    list = new ArrayList<Contacts>();
    this.keyScan = keyScan;
    
    this.doLoad();
  }
  public void doLoad() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;
    
    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      list = (ArrayList<Contacts>)in.readObject();
      
    } catch (EOFException e) {
      //파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("연락처 데이터 로딩 중 오류 발생!");
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {
        // close하다가 예외 발생하면 무시한다.
      }
    }
  }
  public void doSave() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);

    out.writeObject(list);
    
    out.close();
    out0.close();
  }
  public void doAdd() throws Exception { 
    Contacts contact = new Contacts();

    System.out.print("이름(ex: 엄길홍)? ");
    contact.name = this.keyScan.nextLine();

    System.out.print("직위(ex: 대리)? ");
    contact.position = this.keyScan.nextLine();

    System.out.print("전화(ex: 111-1111)? ");
    contact.tel = this.keyScan.nextLine();

    System.out.print("이메일(ex: angel@adress.com)? ");
    
    String inputEmail = this.keyScan.nextLine();
    if(checkEmail(inputEmail) != true) {
      contact.email = inputEmail;
    } else {
      System.out.print("이메일이 존재합니다.계속하시겠습니까?(y/n)");
      if (!this.keyScan.nextLine().equals("y")) {
        System.out.println("저장을 취소하였습니다.");
        return;
      }
      contact.email = inputEmail;
      System.out.println("저장하였습니다.");
    }
    list.add(contact);
    this.doSave();
  }
  public boolean checkEmail(String inputEmail) {
    
    Contacts contact;
    for(int i = 0;i < list.size();i++) {
      try {
      contact = list.get(i);
      if (contact.email.equals(inputEmail)) 
        return true;
      } catch (NullPointerException e) {
      }
    }
    return false;

  }
  public void doList() {
    for (Contacts contact : list) {
      System.out.printf("%s,%s,%s,%s\n",
        contact.name,
        contact.position,
        contact.tel,
        contact.email);
    }
  }
  public void doView() {
    System.out.print("이름? ");
    String inputName = this.keyScan.nextLine();
    Contacts contact;
    
    for(int i = 0;i < list.size(); i++) {
      contact = list.get(i);
      if (contact.name.equals(inputName)) {
        System.out.println("------------------------------");
        
        System.out.printf("이름: %s\n", contact.name);
        System.out.printf("직위: %s\n", contact.position);
        System.out.printf("전화: %s\n", contact.tel);
        System.out.printf("이메일: %s\n", contact.email);
      }
    }
    System.out.println("------------------------------");
  }
  public void doDelete() throws Exception {     //how to delete 1
    System.out.print("이름? ");
    String name = keyScan.nextLine();
    
    ArrayList<Contacts> deleteList = new ArrayList<>();

    for (Contacts contact : list) {
      if (contact.name.equals(name)) {
        deleteList.add(contact);
      }
    }
    
    for (Contacts contact : deleteList) {
      list.remove(contact);
    }
    
    this.doSave();
    System.out.println("삭제하였습니다.");
  }
public boolean checkName(String inputName) {
    
    Contacts contact;
    for(int i = 0;i < list.size();i++) {
      try {
      contact = list.get(i);
      if (contact.name.equals(inputName)) 
        return true;
      } catch (NullPointerException e) {
      }
    }
    return false;

  }
  
}
