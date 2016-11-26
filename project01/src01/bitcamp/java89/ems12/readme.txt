# v1.8 
구현할 기능 및 산출물:

command 패턴 적용

Command.java 인터페이스 정의
클라이언트 요청을 들어왔을 때 EduAppServer가 호출하는 메서드 규칙
클라이언트 요청을 처리하는 클래스는 반드시 이 규칙을 따라야 한다.
ContactController.java에 Command 패턴 적용
ContactListController.java : doList()를 객체화
ContactViewController.java : doView()를 객체화
ContactAddController.java : doAdd()를 객체화
ContactUpdateController.java : doUpdate()를 객체화
ContactDeleteController.java : doDelete()를 객체화
StudentController.java에 Command 패턴 적용
StudentListController.java : doList()를 객체화
StudentViewController.java : doView()를 객체화
StudentAddController.java : doAdd()를 객체화
StudentUpdateController.java : doUpdate()를 객체화
StudentDeleteController.java : doDelete()를 객체화
학습목표:

커맨드 패턴 이해하며 또한 적용할 수 있다.
인터페이스의 용도를 이해한다.