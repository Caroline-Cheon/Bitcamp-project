package bitcamp.java89.ems.server;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class ReflectionUtil2 {
private static ArrayList<Class> classList;

  public static void main(String[] args) throws Exception {
    File file = new File("./bin");
    getCommandClasses(file, classList);
  }

  public static void getCommandClasses(File dir, ArrayList<Class> classList) {
    if (!dir.isDirectory()) {
      return;
    }
    
    File[] files = dir.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname.isDirectory())
          return true;
        if (pathname.getName().endsWith(".class") && !pathname.getName().contains("$"))
          return true;
        return false;
      }
    });

    for (File file :files) {
      if (file.isDirectory()) {
        getCommandClasses(file, classList);
      } else {
        String path = file.getAbsolutePath().replaceAll("\\\\", "/");
        int pos = path.indexOf("/bin/");
       
        try {
          String classname = path.substring(pos + 5).replaceAll("/", ".").replaceAll(".class", "");
          System.out.println(classname);
          
          Class c = Class.forName(classname);
          
          Class superClass = c.getSuperclass();
          System.out.println("&&&&&& " + superClass.getName());
          if (superClass == AbstractCommand.class) {
            System.out.println("=>>>" + c.getName());
            classList.add(c);
          }
        } catch (Exception e) {
          
        }
        
      }
    }
  }
}













