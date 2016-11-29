# v2.6
####구현할 기능 및 산출물:
메서드에 애노테이션 적용하기
- RequestMapping.java 애노테이션을 정의한다.
- Command.java (삭제)
- AbstractCommand.java (삭제)
- Controller 클래스 변경
  - AbstractCommand 상속을 제거한다.
  - doResponse() 메서드의 이름을 적절히 바꾼다.
  - @RequestMapping 애노테이션을 메서드 앞에 붙인다.
- RequestThread.java (변경)
  - 명령어를 처리하는 @RequestMapping 애노테이션이 붙은 메서드를 호출한다.

####학습목표:
- 메서드에 애노테이션을 붙여 활용하는 방법을 익힌다.


