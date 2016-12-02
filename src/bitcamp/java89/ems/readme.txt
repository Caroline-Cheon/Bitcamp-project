v3.0

####구현할 기능 및 산출물:

커넥션 객체를 미리 생성해 놓고 사용하자. 매번 SQL을 실행할 때 마다 수행하는 DBMS와 연결하는 시간을 줄일 수 있다.

-각 DAO의 생성자에서 커넥션을 미리 만든다. SQL 실행 시 이 커넥션을 사용한다.
  -ContactMySQLDao.java (변경)
  -StudentMySQLDao.java (변경) ####학습목표:
-Authentication(인증)과 Authorization(권한)의 의미를 이해한다.
-커넥션 객체를 적게 생성해야 하는 이유를 안다.




