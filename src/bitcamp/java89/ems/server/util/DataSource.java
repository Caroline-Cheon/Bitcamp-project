package bitcamp.java89.ems.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
// 커넥션을 관리하는 datasource를 만든다.
@Component
public class DataSource {
  ArrayList<Connection> conPool = new ArrayList<>();

  public DataSource() { // 생성자에 로딩을 넣어줌
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection() throws Exception {
    if (conPool.size() == 0) {   // 커넥션이 없으면 생성한다.
      System.out.println("DB 커넥션 생성");
      return DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", 
          "java89", "1111");
    } else {
      return conPool.remove(0);   // 0번 방에 있는 객체를 삭제하면서 리턴한다.
    }
  }

  public void returnConnection(Connection con) {
    try {
      if (!con.isClosed() && con.isValid(5)) {     // 커넥션이 닫히지 않고 유효한지(timeout 안에 있는지) 확인
        conPool.add(con);
      }
    } catch(Exception e) {}
  }
}
