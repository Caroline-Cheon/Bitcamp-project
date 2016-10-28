package bitcamp.java89.ems;
import java.util.Scanner;

public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리시스템에 오신 걸 환영합니다.");

    //여러명의 책 정보를 입력하기 위한 레퍼런스 배열을 만든다.
    TextBook[] textbooks = new TextBook[100];
    int length = 0;          //레퍼런스 배열에 몇개의 책 정보를 저장했는지 갯수를 지정한다.
                            // 레퍼런스 배열에 몇개의 TextBook 인스턴스가 들어 있는지 그 갯수를 지정한다. ㄴ

    //사용자로부터 값을 입력 받을 때 사용할 입력 도구 준비
    Scanner keyScan = new Scanner(System.in);

    while(length < textbooks.length){
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

      textbooks[length++] = textbook;
      System.out.print("계속 입력하시겠습니까(Y/N)?");
      if(!keyScan.nextLine().equals("Y"))
        break;
    }
    printTextBookList(textbooks, length);
  }

  static void printTextBookList(TextBook[] textbooks, int length) {
    for (int i = 0; i < length; i++) {
      TextBook textbook = textbooks[i];
      System.out.printf("%s,%s,%s,%s,%s,%s,%d,%d\n",
        textbook.title,
        textbook.author,
        textbook.press,
        textbook.releaseDate,
        textbook.language,
        textbook.description,
        textbook.page,
        textbook.price);
    }
  }
}
