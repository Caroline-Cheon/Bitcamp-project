# v2.3
####구현할 기능 및 산출물:
의존 객체 주입(Dependency Injection) 적용
- DAO 클래스에 적용된 Singleton 패턴을 제거한다.
    - AbstractDao.java(변경)
        - 파일명 주입받기: setFilename() 추가
        - 파일명 입력 받는 생성자 제거
    - ContactDao.java(변경)
      - 생성자 제거
      - Singleton 관련 스태틱 변수와 스태틱 메서드 제거
    - textbookDao.java(변경)
      - 생성자 제거
      - Singleton 관련 스태틱 변수와 스태틱 메서드 제거
- Controller 클래스에 DAO를 주입할 수 있도록 인스턴스 변수와 셋터를 추가한다.
  - contactXxxController.java에 ContactDao 객체를 저장할 변수와 셋터 추가
  - contactXxxController.java에 TextBookDao 객체를 저장할 변수와 셋터 추가
- EduAppServer에서 DAO를 만들어 Controller에 주입한다.
  - EduAppServer.java(변경)

####학습목표:
- 의존 객체 주입을 이해한다.
- 리플랙션 API를 사용하여 셋터 메서드를 찾아서 의존 객체 주입을 자동화할 수 있다.
