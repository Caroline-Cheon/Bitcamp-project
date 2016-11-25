/*EduAppServer(사용자)와 Controller(피사용자) 사이의 호출 규칙 정의
 * EduAppServer입장에서는 Command를 구현할 Controller를 호출하는 것만 인지하고 코드를 짠다.
 * Controller 입장에서는 Command를 구현해야하고 호출당하는 것만 인지하고 코드를 짠다.
 * */
package bitcamp.java89.ems.server;

import java.io.PrintStream;
import java.util.HashMap;

public interface Command {
  void service(HashMap<String, String> paramMap, PrintStream out);
}
