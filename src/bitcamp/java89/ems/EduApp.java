package bitcamp.java89.ems;
import java.util.Scanner;

public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리시스템에 오신 걸 환영합니다.");

    //사용자로부터 값을 입력 받을 때 사용할 입력 도구 준비
    Scanner keyScan = new Scanner(System.in);

    TextBook textbook = new TextBook();
    System.out.print("title(ex: 자바의 정석)? ");
    textbook.title = keyScan.nextLine();

    System.out.print("author(ex: 남궁성)? ");
    textbook.author = keyScan.nextLine();

    System.out.print("press(ex: 도우출판)? ");
    textbook.press = keyScan.nextLine();

    System.out.print("releaseDate(ex: 2016)? ");
    textbook.releaseDate = keyScan.nextLine();

    System.out.print("language(ex: 한국어)? ");
    textbook.language = keyScan.nextLine();

    System.out.print("description(ex: 자바의 기초부터 객체지향개념을 넘어 실전활용까지 자바의 참맛을 제대로 느껴보세요)? ");
    textbook.description = keyScan.nextLine();

    System.out.print("page(ex: 520)? ");
    textbook.page = Integer.parseInt(keyScan.nextLine());

    System.out.print("price(ex: 30000)? ");
    textbook.price = Integer.parseInt(keyScan.nextLine());

    System.out.printf("title: %s\n", textbook.title);
    System.out.printf("author: %s\n", textbook.author);
    System.out.printf("press: %s\n", textbook.press);
    System.out.printf("releaseDate: %s\n", textbook.releaseDate);
    System.out.printf("language: %s\n", textbook.language);
    System.out.printf("description: %s\n", textbook.description);
    System.out.printf("page: %s\n", textbook.page);
    System.out.printf("price: %s\n", textbook.price);
  }
}
