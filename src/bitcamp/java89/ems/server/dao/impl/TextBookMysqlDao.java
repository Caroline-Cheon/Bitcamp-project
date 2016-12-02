package bitcamp.java89.ems.server.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.dao.TextBookDao;
import bitcamp.java89.ems.server.vo.TextBook;

@Component // ApplicationContext가 객체를 관리하는 클래스임을 표시하기 위해 태그를 단다.
public class TextBookMysqlDao implements TextBookDao {
  
  public TextBookMysqlDao() {
  }
  
  public ArrayList<TextBook> getList() throws Exception {
    ArrayList<TextBook> list = new ArrayList<>();
    Class.forName("com.mysql.jdbc.Driver");
    try (
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", 
          "java89", "1111");
      PreparedStatement stmt = con.prepareStatement(
          "select title, author, press, releaseDate, language, description, page, price from ex_textbooks");
      ResultSet rs = stmt.executeQuery(); ){
      
      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        TextBook textbook = new TextBook(); 
        textbook.setTitle(rs.getString("title"));
        textbook.setAuthor(rs.getString("author"));
        textbook.setPress(rs.getString("press"));
        textbook.setReleaseDate(rs.getInt("releaseDate"));
        textbook.setLanguage(rs.getString("language"));
        textbook.setDescription(rs.getString("description"));
        textbook.setPage(rs.getInt("page"));
        textbook.setPrice(rs.getInt("price"));
        
        list.add(textbook);
      }
    }
    return list;
  }
  public ArrayList<TextBook> getListByTitle(String title) throws Exception {
    ArrayList<TextBook> list = new ArrayList<>();
    Class.forName("com.mysql.jdbc.Driver");
    try (
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", 
          "java89", "1111");
      PreparedStatement stmt = con.prepareStatement(
          "select title, author, press, releaseDate, language, description, page, price from ex_textbooks where title=?"); ){
      
      stmt.setString(1, title);
      ResultSet rs = stmt.executeQuery();
      
      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        TextBook textbook = new TextBook(); 
        textbook.setTitle(rs.getString("title"));
        textbook.setAuthor(rs.getString("author"));
        textbook.setPress(rs.getString("press"));
        textbook.setReleaseDate(rs.getInt("releaseDate"));
        textbook.setLanguage(rs.getString("language"));
        textbook.setDescription(rs.getString("description"));
        textbook.setPage(rs.getInt("page"));
        textbook.setPrice(rs.getInt("price"));
        
        list.add(textbook);
      }
      rs.close();
    }
    return list;
  }
  
  public void insert(TextBook textbook) throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    try (
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", 
          "java89", "1111");
      PreparedStatement stmt = con.prepareStatement(
          "insert into ex_textbooks(title, author, press, releaseDate, language, description, page, price)"
          + "values(?, ?, ?, ?, ?, ?, ?, ?)"); ){
      
      stmt.setString(1, textbook.getTitle());
      stmt.setString(2, textbook.getAuthor());
      stmt.setString(3, textbook.getPress());
      stmt.setInt(4, textbook.getReleaseDate());
      stmt.setString(5, textbook.getLanguage());
      stmt.setString(6, textbook.getDescription());
      stmt.setInt(7, textbook.getPage());
      stmt.setInt(8, textbook.getPrice());
     
      stmt.executeUpdate();
    }
  }
  public void update(TextBook textbook) throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    try (
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", 
          "java89", "1111");
      PreparedStatement stmt = con.prepareStatement(
          "update ex_textbooks set title=?, author=?, press=? , releaseDate=?, language=?, description=?, page=?, price=? where title=?"); ) {
      
      stmt.setString(1, textbook.getTitle());
      stmt.setString(2, textbook.getAuthor());
      stmt.setString(3, textbook.getPress());
      stmt.setInt(4, textbook.getReleaseDate());
      stmt.setString(5, textbook.getLanguage());
      stmt.setString(6, textbook.getDescription());
      stmt.setInt(7, textbook.getPage());
      stmt.setInt(8, textbook.getPrice());
      
      stmt.executeUpdate();
    } 
  }
  
  public void delete(String title) throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    try (
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", 
          "java89", "1111");
      PreparedStatement stmt = con.prepareStatement(
          "delete from ex_textbooks where title=?"); ) {
      
      stmt.setString(1, title);
      
      stmt.executeUpdate();
    } 
  }
  
  public boolean existTitle(String title) throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    try (
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", 
          "java89", "1111");
      PreparedStatement stmt = con.prepareStatement(
          "select * from ex_textbooks where title=?"); ) {
      
      stmt.setString(1, title);
      ResultSet rs = stmt.executeQuery();
      
      if (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }
    }
  }
}