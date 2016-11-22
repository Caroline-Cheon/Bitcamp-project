package bitcamp.java89.ems10.server.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import bitcamp.java89.ems10.server.vo.TextBook;

public class TextBookController2 {
  private Scanner in;
  private PrintStream out;
  
  private String filename = "textbook-v1.6.data";
  private ArrayList<TextBook> list;
  private boolean changed;
  public TextBookController2(Scanner in, PrintStream out) {
    list = new ArrayList<TextBook>();
    this.in = in;
    this.out = out;
    
    this.load();
  }
  
  public boolean isChanged() {
    return changed;
  }
  @SuppressWarnings("unchecked")
  private void load() {
    FileInputStream in0 = null;
    ObjectInputStream in = null;
    
    try {
      in0 = new FileInputStream(filename);
      in = new ObjectInputStream(in0);
      
      list = (ArrayList<TextBook>)in.readObject();
    } catch (EOFException e) {
      
    } catch (Exception e) {
      System.out.println("책 데이터 로딩 중 오류 발생!");
    } finally {
      try {
        in.close();
        in0.close();
      } catch (Exception e) {
        
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
  
  public void service() {
    loop:
     while (true) {
       out.println("교재관리> ");
       out.println();
       
       String[] commands = in.nextLine().split("\\?");
     }
  }
}
  
