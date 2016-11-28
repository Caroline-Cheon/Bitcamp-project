package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.TextBook;

public class TextBookDao extends AbstractDao<TextBook>{
  
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
  
