v3.1

####구현할 기능 및 산출물:
각 DAO마다 커넥션 객체를 생성하는 것은 자원 낭비이다. 한 개만 만들어 공유하자.
- EduAppServer.java(변경)
  - Connection 객체를 외부에서 생성하여 ApplicationContext에게 넘긴다.
-ApplicationContext.java(변경)
  - 생성자 추가.외부에서 만든 객체를 포함시킨다.
- DAO 변경, 왜? Connection 객체를 주입 받을 수 있게 셋터 메서드 추가
  -ContactMySQLDao.java (변경)
  -StudentMySQLDao.java (변경)
  
  
####학습목표:
- DI(Dependency Injection) 기법을 사용하여 커넥션 객체를 주입할 수 있다.
- 커넥션 공유 시 문제점을 이해한다.


