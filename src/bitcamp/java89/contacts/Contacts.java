package bitcamp.java89.contacts;

import java.io.Serializable;

public class Contacts implements Serializable{
  private static final long serialVersionUID = 1L;
  
  protected String name;
  protected String position;
  protected String tel;
  protected String email;
  
  public Contacts() {}

  @Override
  public String toString() {
    return "Contacts [name=" + name + ", position=" + position + ", tel=" + tel + ", email=" + email + "]";
  }
  
  
}
