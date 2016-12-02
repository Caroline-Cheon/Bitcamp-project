# v2.9
####구현할 기능 및 산출물:
DBMS를 이용하여 데이터를 관리한다.
- JDBC 드라이버 준비
  - 프로젝트 폴더 아래 libs 폴더를 생성한 후 
    해당 폴더에 JDBC 드라이버 파일을 둔다.
  - Java Build Path에 이 드라이버의 경로를 추가한다.
  
- DAO 클래스 변경
  - AbstractDao.java 파일을 AbstractFileDao.java로 변경
  - ContactDao.java 파일을 ContactFileDao.java로 변경
  - TextBookDao.java 파일을 TextBookFileDao.java로 변경
  - ContactDao.java 인터페이스 생성: 컨트롤러와 DAO 사이에 호출 규칙을 정의
  - ContactXxxx.java는 이 인터페이스를 구현한다.
  - ContacctMysqlDao.java (생성)는 이 인터페이스를 구현한다.
- 클래스 관리를 효율적으로 하기 위해
  - dao 패키지 아래에 impl 패키지를 생성한다.
  - impl 패키지 아래에 ContactDao 인터페이스를 구현한 클래스를 둔다.
  
    
####학습목표:
- JDBC 프로그래밍을 익힌다.



# v2.8
####구현할 기능 및 산출물:
파라미터 선언을 자유롭게 하자! 메서드의 파라미터 정보를 분석하여 필요한 값만 전달한다.
-파라미터에 붙일 애노테이션을 생성한다.
  -RequestParam.java (생성)
  
-Controller 변경
  -각 메서드 별로 파라미터에 RequestParam 애노테이션을 붙인다.
 
-RequestThread.java (변경)
  -메서드를 호출할 때 파라미터 정보를 분석하여 값을 준비한다.
  
  
####학습목표:
파라미터에 애노테이션을 적용할 수 있다.
파라미터에 적용된 애노테이션의 값을 추출할 수 있다.
