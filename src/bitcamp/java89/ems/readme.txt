v3.3

####구현할 기능 및 산출물:
오픈 소스 스프링 Ioc 컨테이너 적용
- 라이브러리 다운로드
  - build.gradle 파일에 스프링 라이브러리 정보 추가
  - gradle eclipse 실행: 의존 라이브러리 다운로드 및 .classpath 갱신

-ApplicationContext (삭제)
- EduAppServer.java(변경)
  - 스프링의 IoC 컨테이너 사용
  - application-context.xml 파일 생성
-기존에 생성한 @Component 을 스프링의 애노테이션으로 교체한다.
  - Component.java (삭제) : 스프링의 애노테이션을 사용할 것이다.
  - DAO, Controller, DataSource 클래스 변경
- 스프링 IoC 컨테이너에서 제공하는 @Autowired 애노테이션을 사용하여 의존 객체 주입 설정
  - @Autowired 가 붙은 변수에 대해, 그 변수의 타입에 해당하는 인스턴스를 찾아 자동으로 주입
  - 셋터 메서드 제거한다.
  - 대신 변수 앞에 Autowired 붙인다.
  - Controller, DAO 클래스 변경
  

  
####학습목표:
- 스프링 IoC 컨테이너를 설정하고 사용할 수 있다.
