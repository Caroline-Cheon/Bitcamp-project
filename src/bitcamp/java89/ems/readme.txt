# v2.7
####구현할 기능 및 산출물:
메서드 대통합! 유사 업무를 처리하는 메서드끼리 한 클래스에서 관리한다.
- ContactController.java (생성)
  - Contact 관련 클래스들의 메서드를 모두 이 클래스에 넣는다.
- TextBookController.java (생성)
  - TextBook 관련 클래스들의 메서드를 모두 이 클래스에 넣는다.
- RequestHandlerMapping.java (생성)
  - 명령어를 처리할 메서드를 관리한다.
  - RequestHandler.java (중첩 클래스 생성)
    - 메서드와 인스턴스를 보관한다.
- EduAppServer.java (변경)
  - 객체 준비 뿐만 아니라 명령어를 처리할 메서드 연결 정보를 생성한다.
- RequestThread.java (변경)
  - RequestHandlerMapping을 이용하여 명령어를 찾는다.
  - RequestHandler를 이용하여 메서드를 호출한다.
  
####학습목표:
- 에노테이션 활용법을 익힌다.
- HashMap 활용법을 익힌다.
- 중첩 클래스 활용법을 익힌다.
- Reflection API 활용법을 익힌다.


