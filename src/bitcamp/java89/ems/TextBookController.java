package bitcamp.java89.ems;
import java.util.Scanner;

public class TextBookController {
  TextBook[] textbooks = new TextBook[100];
  int length = 0;
  Scanner keyScan;

//기본 생성자가 없다. 따라서 이 클래스를 사용하려면 반드시 Scanner를 줘야 한다.
// => 생성자에서 하는 일은 그 객체를 사용하기 전에 유효상태로 만드는 것이다.
  public TextBookController(Scanner keyScan) {
    this.keyScan = keyScan;
  }
  public void doAdd() {
    TextBook textbook = new TextBook();

    System.out.print("책이름(ex: java's best practice)? ");
    textbook.title = this.keyScan.nextLine();

    System.out.print("저자(ex: mr.Nam)? ");
    textbook.author = this.keyScan.nextLine();

    System.out.print("출판사(ex: Dow)? ");
    textbook.press = this.keyScan.nextLine();

    System.out.print("출판년도(ex: 2016)? ");
    textbook.releaseDate = this.keyScan.nextLine();

    System.out.print("언어(ex: Korean)? ");
    textbook.language = this.keyScan.nextLine();

    System.out.print("설명(ex: Do you want to feel java from basic to OOP? ");
    textbook.description = this.keyScan.nextLine();

    System.out.print("쪽수(ex: 520)? ");
    textbook.page = Integer.parseInt(this.keyScan.nextLine());

    System.out.print("가격(ex: 30000)? ");
    textbook.price = Integer.parseInt(this.keyScan.nextLine());

    textbooks[length++] = textbook;
    System.out.println();
  }

  public void doList() {
    for (int i = 0; i < this.length; i++) {
      TextBook textbook = this.textbooks[i];
      System.out.printf("%s,%s,%s,%s,%s,%s,%d,%d\n",
        textbook.title,
        textbook.author,
        textbook.press,
        textbook.releaseDate,
        textbook.language,
        textbook.description,
        textbook.page,
        textbook.price);
      System.out.println();
    }
  }
  public void doView() {
    System.out.println("책이름을 입력하세요");
    String input = keyScan.nextLine().toLowerCase();
    for(int i = 0; i <this.length;  i++) {

      if(textbooks[i].title.toLowerCase().equals(input)) {
        System.out.println("-----------------------------------");
        System.out.printf("text: %s\n",this.textbooks[i].title);
        System.out.printf("author: %s\n",this.textbooks[i].author);
        System.out.printf("press: %s\n",this.textbooks[i].press);
        System.out.printf("releaseDate: %s\n",this.textbooks[i].releaseDate);
        System.out.printf("language: %s\n",this.textbooks[i].language);
        System.out.printf("description: %s\n",this.textbooks[i].description);
        System.out.printf("page: %d\n",this.textbooks[i].page);
        System.out.printf("price: %d\n",this.textbooks[i].price);
        System.out.println();
        break;
      }
    }
  }
}
