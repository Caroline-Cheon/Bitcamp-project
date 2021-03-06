[v0.9]
구현할 기능:
=> linked list를 TextBookController에 적용한다.

산출물:
=> TextBook.java
=> EduApp.java
=> TextBookController.java(변경)
=> Box.java (삭제)
=> LinkedList.java (추가)

학습목표:
=> LinkedList를를 만들 수 있다
=> LinkedList를 사용하여 


[v0.8]
구현할 기능:
=> linked list 이용, 학생, 강사, 등 데이터 관리

산출물:
=> TextBook.java
=> EduApp.java(변경)
=> TextBookController.java(변경)
=> Box.java 추가

학습목표:
=> linked list 사용법 숙달
=> linked list 구현 및 타 프로젝트 적용

[v0.7]
구현할 기능:
=> EduApp에 있던 책관리 명령어 처리를
   학생관리 전문가인 TextBookController로 이전한다.
=> EduApp은 대신 메뉴 선택 기능을 추가한다.

산출물:
=> TextBook.java
=> EduApp.java(변경)
=> TextBookController.java(변경)

학습목표:
=> 리팩토링(refactoring)을 통해 명령어 코드를 관련된 클래스에 분류시키는 것
=> 객체지향 설계 방법론 중에서 "응집력 높이기(high cohesion)"을 이해한다.
   High Cohesion? 한 클래스에 한 가지 역할에 집중해야 한다.
   => 한 클래스가 여러 가지 일을 하면 클래스 코드가 커지고, 유지보수에 안좋다.
=> 메서드나 변수에 대해 접근을 제어하는 방법과 이유를 이해한다.
   => TextBookController의 doxxx()의 메서드들을 private 으로 접근을 제한한다.
   => 이 클래스의 내부 변수들도 private으로 접근을 제한한다.
------------------------------------------------------------------------------

[v0.6]
구현할 기능:
=> 삭제기능 추가
    명령> delete
    삭제할 책의 이름은? java
    java 책 정보를 삭제하였습니다.

    명령> delete
    삭제할 책의 이름은? jaja
    jaja 책 정보를 찾을 수 없습니다.


=> 변경기능 추가
    명령> update
    변경할 책의 이름은? hong
    저자? 111
    출판사? 1
    출판년도?
    언어?
    설명?
    쪽수?
    가격?
    저장하시겠습니까(y/n)? y
    저장하였습니다.
    명령>

    저장하시겠습니까(y/n)? n
    변경을 취소하였습니다.
    명령>

    명령> update
    변경할 책의 이름은? hong
    hong 이라는 책이 없습니다.


산출물:
=> TextBook.java
=> EduApp.java(변경)
=> TextBookController.java(변경)

학습목표:
=> 삭제와 변경 기능을 추가하여 CRUD(Creat/Read/Update/Delete) 기본 기능을 완성한다.
=> 기존의 코드에 새 기능을 추가하는 경험을 해본다.
    이것이 유지보수 개발업무이다.



[v0.5]
구현할 기능:
=> TextBookController 클래스를 확장해서 사용할 수 있도록,
   학생 목록을 스태틱 변수로 저장하지 말고, 인스턴스 변수로 저장한다.
=> 그에 따라 명령어를 처리하는 메서드도 인스턴스 메서드로 전환한다.

산출물:
=> TextBook.java
=> EduApp.java(변경)
=> TextBookController.java(변경)

학습목표:
=> 인스턴스 변수와 인스턴스 메서드의 사용법을 익힌다.
=> 생성자의 용도를 확인한다.

[v0.4]
구현할 기능:
=> 소스 코드 리팩토링(refactoring)
  => 유지보수하기 좋게 소스 코드를 좀 더 객체지향적으로 정리한다.
=> EduApp에 있는 TextBook 데이터의 입출력을 다루는데 관련된 메서드를
   따로 다른 클래스로 분류한다.

산출물:
=> TextBook.java
=> EduApp.java
=> TextBookController.java

학습목표:
=> 클래스 문법의 용도를 다시 한 번 익힌다.
  => 클래스는 특정 작업과 관련된 메서드를 관리하기 좋게 분류할 때 사용한다.

[v0.3]
구현할 기능:
=> 사용자에게 명령어를 입력 받는 프롬프트 기능을 추가한다.
예) 프로그램을 시작시키면 다음과 같이 동작한다.
명령> add
title(ex: 자바의 정석)?
author(ex: 남궁성)? 1
press(ex: 도우출판)? 1
releaseDate(ex: 2016)? 1
language(ex: 한국어)? 1
description(ex: 자바의 기초부터 객체지향개념을 넘어 실전활용까지 자바의 참맛을 제대로 느껴보세요)? 1
page(ex: 520)? 1
price(ex: 30000)? 1

명령> list
1,1,11,,11,1,1,1

명령> view
책 title ? 자바의 정석
-----------------------------------------------------------
title:1;
author:1;
press:1;
releaseDate:1;
language:1;
description:1;
page:1;
price:1;

산출물:
=>

학습목표:
=> 반복문 중첩해서 사용하는 방법을 익힌다.
=> 입력 코드를 별도의 메서드로 분리하여 유지보수 하기 좋은 코드로 만드는 것을 익힌다.

[v0.2]
구현할 기능:
=> 여러 명의 책의 정보를 입력 받아 저장하기

산출물:
=> TextBook.java
=> EduApp.java(코드 변경)

학습목표:
=> 배열을 사용하여 여러 개의 인스턴스를 다루는 방법을 익힌다.
=> 여러 번 입력 받기 위해 반복문을 사용하는 방법을 익힌다.
=> 인스턴스와 레퍼런스의 관계를 이해한다.
=> 여러 개의 책의 정보를 출력하기 위해
   한 책 정보를 출력한 코드를 별도의 메서드로 분리한다.
   메서드의 용도와 사용법을 익힌다.

[v0.1]
목표:
=> 책 정보를 다룰 때 사용할 TextBook 데이터 타입을 정의한다.
=> 책 정보를 입력 받아 출력해본다.

산출물:
=> TextBook.java
=> EduApp.java

학습목표:
=> Class 문법을 사용하여 사용자 정의 데이터 타입을 만드는 방법을 익힌다.
=> 키보드를 통해 사용자로부터 입력 받는 방법을 배운다.
=> 인스턴스를 생성하고 인스턴스에 값을 넣는 방법을 익힌다.
=> 문자열 데이터를 int, boolean 등 다른 타입의 값으로 바꾸는 방법을 배운다.
