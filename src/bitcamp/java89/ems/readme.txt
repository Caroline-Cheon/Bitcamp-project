v3.2

####구현할 기능 및 산출물:
DB 커넥션을 효울적으로 관리하기 위해 커넥션풀 기법 도입. 커넥션 객체를 임대하여 쓴다.
- EduAppServer.java(변경)
  - Connection 객체 생성 코드를 제거한다.
- DataSource.java(생성)
  - util 패키지 생성. 이 패키지 안에 DataSource 클래스 생성
  - DB 커넥션의 생성-임대-소멸을 관리
  - DB Connection Pool 이라고 말한다.
  - 공유하는 객체를 미리 생성해 두었다가 필요할 때 마다 임대해서 사용하는 개발 기법을
    "Flyweight" 디자인 패턴이라고 한다.
-DAO 변경. 왜? connection 객체를 주입 받는대신....??????????
  -ContactMySQLDao.java (변경)
  -StudentMySQLDao.java (변경)
  
  
####학습목표:
- DB 커넥션풀 구동 원리를 이해한다. 커넥션풀을 사용해야 하는 이유를 안다.
- 간단한 커넥션풀을 만들 수 있다.

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


