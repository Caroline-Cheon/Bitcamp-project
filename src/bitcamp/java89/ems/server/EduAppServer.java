//버전 1.7
package bitcamp.java89.ems.server;

import java.net.ServerSocket;

public class EduAppServer {
  
  // 스태틱 멤버로 지정하여 다른 메서드가 참조할 수 있도록 한다.

  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }
  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중");
    
    while (true) {
      new RequestThread(ss.accept()).start();
    }
    //ss.close();
  }
}
