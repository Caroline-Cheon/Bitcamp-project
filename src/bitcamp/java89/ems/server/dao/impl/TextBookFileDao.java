package bitcamp.java89.ems.server.dao.impl;

import java.util.ArrayList;

import bitcamp.java89.ems.server.dao.TextBookDao;
import bitcamp.java89.ems.server.vo.TextBook;

//@Component // ApplicationContext가 객체를 관리하는 클래스임을 표시하기 위해 태그를 단다.
public class TextBookFileDao extends AbstractFileDao<TextBook> 
                             implements TextBookDao {
  
  public TextBookFileDao() {     //prepateObject()에서 예외 받아줌
    this.setFilename("textbook-v1.9.data");
    try {this.load();} catch(Exception e) {}
  }
  
  public ArrayList<TextBook> getList(){
    return this.list;
  }
  public ArrayList<TextBook> getListByTitle(String title) {
    ArrayList<TextBook> results = new ArrayList<>();
    for (TextBook textbook : list) {
      if (textbook.getTitle().equals(title)) {
        results.add(textbook);
      }
    }
    return results;
  }
  
  synchronized public void insert(TextBook textbook) {
    list.add(textbook);
    try {this.save();} catch (Exception e) {e.printStackTrace();}
  }
  synchronized public void update(TextBook textbook) {
    for (int i = 0;i < list.size();i++) {
      if (list.get(i).getTitle().equals(textbook.getTitle())) {
        list.set(i, textbook);
        try {this.save();} catch (Exception e) {e.printStackTrace();}
        return;
      }
    }
  }
  
  synchronized public void delete(String title) {
    for (int i = 0; i < list.size();i++) {
      if (list.get(i).getTitle().equals(title)) {
        list.remove(i);
        try {this.save();} catch (Exception e) {e.printStackTrace();}
        return;
      }
    }
  }
  
  public boolean existTitle(String title) {
    for (TextBook textbook : list) {
      if (textbook.getTitle().equals(title.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}