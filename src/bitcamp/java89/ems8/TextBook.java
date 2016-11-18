package bitcamp.java89.ems8;

import java.io.Serializable;

public class TextBook implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String title;
  protected String author;
  protected String press;
  protected int releaseDate;
  protected String language;
  protected String description;
  protected int page;
  protected int price;

  public TextBook() {
    this("자바의 정석", "남궁성", 30000);
  }

  public TextBook(String title, String author, int price){
    this.title = title;
    this.author = author;
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPress() {
    return press;
  }

  public void setPress(String press) {
    this.press = press;
  }

  public int getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(int releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "TextBook [title=" + title + ", author=" + author + ", press=" + press + ", releaseDate=" + releaseDate
        + ", language=" + language + ", description=" + description + ", page=" + page + ", price=" + price + "]";
  }

}
