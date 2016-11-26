package bitcamp.java89.ems.server.dao; 

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
// 추상 메서드가 없어도 추상 클래스가 될 수 있다.
public abstract class AbstractDao<T> {

  private String filename;
  protected ArrayList<T> list;

  public AbstractDao(String filename) {
    //super();
    this.filename = filename;
  }

  @SuppressWarnings("unchecked")
  protected void load() throws Exception {
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