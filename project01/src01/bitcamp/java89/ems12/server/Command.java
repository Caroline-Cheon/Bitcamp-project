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

// MyTip: CommandInterface의 목적은 Controller가 가진 메서드를 각기 분리해내는 데 있어서 공통된 분모를 규칙으로 
// 정의하여 일관성을 가짐과 동시에 향후 유지 보수에 있어서 용이함을 가지는 것이다.
// Controller가 가진 메서드들의 공통점은 HashMap을 이용해 out을 통해 어떤 메세지를 출력해내는 것이다.
// 그러니 당연하게도 Command 인터페이스가 가져야할 규율은 각각의 Controller가 구현할 수 있는 바탕을 준비해주는것
// 즉 파라미터로 HashMap과 PrintStream 을 넘겨주는 것이다.