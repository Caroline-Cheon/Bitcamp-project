package bitcamp.java89.ems.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

import bitcamp.java89.ems.server.annotation.RequestParam;
import bitcamp.java89.ems.server.context.RequestHandlerMapping;
import bitcamp.java89.ems.server.context.RequestHandlerMapping.RequestHandler;

public class RequestThread extends Thread {
  private Socket socket;
  private Scanner in;
  private PrintStream out;
  
  private RequestHandlerMapping handlerMapping;
  
  public RequestThread(Socket socket, RequestHandlerMapping handlerMapping) {
    this.socket = socket;
    this.handlerMapping = handlerMapping;
  }
  @Override
  public void run() {
    // 스레드가 독립적으로 실행할 코드를 두는 곳
    try {
      in = new Scanner(new BufferedInputStream(socket.getInputStream()));
      out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()), true);
      
      out.println("비트캠프 관리시스템에 오신 걸 환영합니다.");
      
      
      while (true) {
        // 클라이언트에게 데이터를 전송한다.
        out.println("명령> ");
        out.println(); // 빈 줄은 데이터의 끝을 의미한다(프로토콜)
   
        boolean running = true;
        
        // 클라이언트가 보낸 명령문을 분석하여 파라미터 값을 분리 추출한다.
        String[] command = in.nextLine().split("\\?");
        
        HashMap<String,String> dataMap = new HashMap<>();
        //파라미터 문자열이 있다면, 이 문자열을 분석하여 HashMap에 보관한다.
        
        if (command.length == 2) {
          String[] params = command[1].split("&");
          for (String value : params) {
            String[] kv = value.split("=");
            dataMap.put(kv[0], kv[1]);
          }
        }
        
        RequestHandler requestHandler = handlerMapping.getRequestHandler(command[0]);
        
        if (requestHandler == null) {
          if (command[0].equals("quit")) {
            doQuit();
            break;
          }
          out.println("지원하지 않는 명령어입니다.");
          continue; // 다음 줄로 가지 않고 반복문 조건 검사로 건너뛴다.
        }
        // 클라이언트가 보낸 명령을 처리할 객체가 있다면, 작업을 실행한다.
        try {
          //Object[] args = getArguments(requestHandler.method, dataMap, out);
          //requestHandler.method.invoke(requestHandler.obj, args);
          // 위 두줄과 같다.
          requestHandler.method.invoke(requestHandler.obj, getArguments(requestHandler.method, dataMap, out));
          
        } catch (Exception e) {
          System.out.println("작업 중 오류가 발생했습니다.");
          e.printStackTrace();
        }
        
      } // while
    } catch (Exception e) {
      // 클라이언트 요청 처리 중 예외 발생!
    } finally {
      try {in.close();} catch (Exception e) {}
      try {out.close();} catch (Exception e) {}
      try {socket.close();} catch (Exception e) {}
    }
  }
  private Object[] getArguments(Method method, HashMap<String, String> dataMap, PrintStream out) {
    // 호출할 메서드의 파라미터 정보를 추출한다. @@@@@@@@@@주목
    Parameter[] params = method.getParameters();      // 수정
    
    // 파라미터 값을 저장할 배열을 준비한다.
    Object[] args = new Object[params.length];
    
    //파라미터 정보를 꺼내서 그에 맞는 값을 준비한다.
    for (int i = 0; i < params.length; i++) {
      // 파라미터 @RequestParam 이라는 애노테이션이 붙은 경우
      RequestParam anno = params[i].getAnnotation(RequestParam.class);
      if (anno != null) {
        String value = anno.value();
        if (params[i].getType() == int.class) {
          args[i] = Integer.parseInt(dataMap.get(value));
        } else if (params[i].getType() == boolean.class) {
          args[i] = Boolean.parseBoolean(dataMap.get(value));
        } else if (params[i].getType() == String.class) {
          args[i] = dataMap.get(value);
        } else {
          args[i] = null;
        }
      } else {
        if (params[i].getType() == PrintStream.class) {
          args[i] = out;
        } else if (params[i].getType() == HashMap.class) {
          args[i] = dataMap;
        } else {
          args[i] = null;
        }
      }
    } //for
    return args;
  }

  private boolean doQuit() {
    System.out.println("클라이언트 연결 종료");
    return true;
  }
}
