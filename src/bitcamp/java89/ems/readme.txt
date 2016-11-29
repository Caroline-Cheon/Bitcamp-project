# v2.4
####구현할 기능 및 산출물:
빈 컨테이너 도입 : DAO, Controller 객체 관리를 자동화시킨다.  
- EduAppServer 클래스로부터 객체 관리 기능을 별도의 클래스로 분리한다.
- ( 현재 Server가 DAO를 생성한다)
  - (Spring에 있는 class의 기능을 모방할 것이다.)
  - ApplicationContext.java (생성)
  - ReflectionUtill 클래스와 ApplicationContext 클래스의 기능을 합친다.
  - ReflectionUtill.java (제거)
  - StudentDao, ContactDao 클래스 변경
    - 생성자 호출 시 load() 메서드를 호출하도록 바꾼다.
- EduAppServer 클래스는 ApplicationContext 클래스를 사용하여 객체를 다룬다.
  - EduAppServer.java (변경)
  - RequestThread.java (변경)

####학습목표:
- 빈 컨테이너의 목적을 이해한다.
- 빈 컨테이너의 내부 구동 원리를 이해한다.