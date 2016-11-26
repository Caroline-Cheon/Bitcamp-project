# v2.2
####구현할 기능 및 산출물:
리플랙션 API를 활용하여 객체 생성 자동화하기
-ReflectionUtil.java(추가)
  - AbstractCommand의 서브 클래스를 찾아 그 클래스 목록을 리턴한다.
-AbstractCommand.java (변경)
  - 명령어를 리턴하는 겟터 메서드 추가. getCommandString()
-XxxController.java (변경)
  - 추상 메서드 getCommandString()을 구현
-EduAppServer.java(변경)
  - ReflectionUtil 클래스를 사용하여 커맨드 객체 생성 자동화

####학습목표:
- Reflection API의 용도를 이해한다.
- 객체 생성을 자동화하는 방법을 이해한다.
- 추상 메서드(getCommandString())의 용도를 이해한다.
