# v2.5
####구현할 기능 및 산출물:
애노테이션을 이용하여 객체의 이름을 JVM에 전달하기
- Component.java 애노테이션을 정의한다.
- ApplicationContext가 관리하는 대상 클래스에 Component에 애노테이션을 적용한다.
  - DAO에 적용
  - Controller에 적용. 단 Controller의 명령 문자열을 애노테이션에 등록한다.
    그리고 getCommandString() 메서드를 없앤다.
- AbstractCommand.java (변경)
  - getCommandString() 추상 메서드 제거
- ApplicationContext.java (변경)
  - @Component 가 붙은 클래스에 대해서만 객체를 생성한다.
  
  isComponent



####학습목표:
- 애노테이션의 용도를 이해한다.
- 애노테이션을 정의하고 적용할 수 있다.


tip: 애노테이션이 뭐냐고 묻는다면
     클래스, 변수, 메서드에 특별한 태깅을 붙이는 문법이고, 실행할 때 꺼내 쓸 수 있다.
     .class file 안에 들어 있기 때문에 이용가능한 것