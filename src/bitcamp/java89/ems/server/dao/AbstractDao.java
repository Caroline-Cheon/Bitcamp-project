package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public abstract class AbstractDao<T> {

  private String filename;
  protected ArrayList<T> list;

  // 파일명을 객체 생성후 주입받는다.
  public void setFilename(String filename) {
    this.filename = filename;
  }

  @SuppressWarnings("unchecked")
  public void load() throws Exception {
    try (
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.filename));){
  
      list = (ArrayList<T>)in.readObject();
      
    } catch (EOFException e) {
      //파일을 모두 읽었다.
    } catch (Exception e) {
      list = new ArrayList<>();
      throw new Exception("데이터 로딩 중 오류 발생!");
    } 
  }

  public synchronized void save() throws Exception {
    try ( //autoclosable 객체 구현
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.filename));) {
    out.writeObject(list);
    } catch (Exception e) {
      throw e;
    }
  }

}