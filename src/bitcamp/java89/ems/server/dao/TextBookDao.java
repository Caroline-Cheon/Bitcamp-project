package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.TextBook;

public class TextBookDao {
  static TextBookDao obj;
  
  private String filename = "textbook-v1.7.data";
  private ArrayList<TextBook> list;
  private boolean changed;
  
  private TextBookDao() {
    this.load();
  } 
  public static TextBookDao getInstance() {
    if (obj == null) {
      obj = new TextBookDao();
    }
    return obj;
  }
  
  public boolean isChanged() {
    return changed;
  }
  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;

    try {
      in0 = new FileInputStream(this.filename);
      in = new ObjectInputStream(in0);

      list = (ArrayList<TextBook>)in.readObject();
      
    } catch (EOFException e) {
      //파일을 모두 읽었다.
    } catch (Exception e) {
      System.out.println("책 데이터 로딩 중 오류 발생!");
      list = new ArrayList<>(); //파일 없으니까 생성
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {
        // close하다가 예외 발생하면 무시한다.
      }
    }
  }

  public void save() throws Exception {
    FileOutputStream out0 = new FileOutputStream(this.filename);
    ObjectOutputStream out = new ObjectOutputStream(out0);

    out.writeObject(list);
    changed = false;

    out.close();
    out0.close();
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
    changed = true;
  }
  synchronized public void update(TextBook textbook) {
    for (int i = 0;i < list.size();i++) {
      if (list.get(i).getTitle().equals(textbook.getTitle())) {
        list.set(i, textbook);
        changed = true;
        return;
      }
    }
  }
  
  synchronized public void delete(String title) {
    for (int i = 0; i < list.size();i++) {
      if (list.get(i).getTitle().equals(title)) {
        list.remove(i);
        changed = true;
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
  
