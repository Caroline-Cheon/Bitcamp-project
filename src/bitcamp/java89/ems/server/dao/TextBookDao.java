package bitcamp.java89.ems.server.dao;

import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.TextBook;

public interface TextBookDao {
  ArrayList<TextBook> getList() throws Exception;
  ArrayList<TextBook> getListByTitle(String title) throws Exception;
  void insert(TextBook textbook) throws Exception;
  void update(TextBook textbook) throws Exception;
  void delete(String title) throws Exception;
  boolean existTitle(String title) throws Exception;

}
