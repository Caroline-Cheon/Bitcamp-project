/* 역할: 빈 컨테이너(Bean Container) == IoC 컨테이너
 * => bean = object = instance
 * => 객체의 생성과 소멸을 관리한다.
 * => 또한 객체가 사용하는 의존 객체 주입을 수행한다. 
 * 
 */
package bitcamp.java89.ems.server.context;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ApplicationContext {
  HashMap<String, Object> objPool = new HashMap<>();

  // package에서 class로 나누고 다시 object로 나누는 과정
  public ApplicationContext(String[] packages) {
    ArrayList<Class<?>> classList = getClassList(packages);
    prepareObjects(classList);
    injectDependencies();
  }
  
  public Object getBean(String name) {
    return objPool.get(name);
  }
  
  private void injectDependencies() {
    // HashMap에 저장된 객체 목록을 뽑아 온다.
    Collection<Object> objects = objPool.values();
    for (Object obj : objects) {
      // 각 객체의 public 메서드 목록을 뽑는다.
      //System.out.println(obj.getClass().getName());
      
      Method[] methods = obj.getClass().getMethods();
      for (Method m : methods) {
        if (!m.getName().startsWith("set")) {
          continue;
        }
        if (m.getParameterCount() != 1) { // 파라미터갯수가 1개가 아니면 제외
          continue;
        }
        // 셋터의 0 번째 파라미터 타입을 알아낸다.
        Class<?> paramType = m.getParameterTypes()[0];
        
        // 그 타입에 해당하는 객체를 objPool에서 찾는다.
        Object dependency = findDependency(paramType);
        
        if (dependency != null) { // 찾았다면, 
          try {
            //System.out.println("==>" + m.getName());
            m.invoke(obj, dependency); //의존객체를 주입하기 위해 셋터를 호출한다.
          } catch (Exception e) {}
        }
      }
    }
    
  }
  private Object findDependency(Class<?> paramType) {
    Collection<Object> objects = objPool.values();
    for (Object obj : objects) {
      if (paramType.isInstance(obj)) {
        return obj;
      }
    }
    return null;
  }
  private ArrayList<Class<?>> getClassList(String[] packages) {
    ArrayList<Class<?>> classList = new ArrayList<>();
    
    for (String packageName : packages) {
      try {
        // package경로를 file 경로로 바꾸어서 클래스 리스트를 뽑아내는 과정
        findClasses(packageNameToFile(packageName), classList);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return classList;
  }
  private void prepareObjects(ArrayList<Class<?>> classList) {
    for (Class<?> clazz : classList) {
      try {
        Object obj = clazz.newInstance();
        String key = null;
        try {
          Method m = clazz.getMethod("getCommandString");
          key = (String)m.invoke(obj);
        } catch (Exception e) {
          key = clazz.getName(); // controller가 아니면 클래스 이름 자체를 key로 사용
        }
        objPool.put(key, obj);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  private File packageNameToFile(String packageName) {
    // 클래스 파일을 찾을 디렉토리 경로를 정의한다.
    // 그런데 파라미터로 넘어오는 값은 순수한 패키지 이름
    // (bitcamp.java89.ems.server.controller)으로 되어 있다.
    // 그래서 다음과 같이 파일 경로로 만들기 위해 "."을 "/"로 변경해야 한다.
    // ex) ./bin/bitcamp/java89/ems/server/controller
    return  new File("./bin/" + packageName.replaceAll("\\.", "/"));
  }
  private void findClasses(File dir, ArrayList<Class<?>> classList) {   // Class<?> 은 제네릭을 이용해서 어떤 클래스든지 담아 줄 수 있게 하는 것이다.
    if (!dir.isDirectory()) {
      return;
    }

    File[] files = dir.listFiles(new FileFilter() { // 익명이기 때문에 정의하자마자 객체를 만들어야한다.
      @Override
      public boolean accept(File pathname) {
        if (pathname.isDirectory())
          return true;
        if (pathname.getName().endsWith(".class") && !pathname.getName().contains("$"))
          return true;
        return false;
      }
    });
    
    for (File file : files) {
      if (file.isDirectory()) {
        findClasses(file, classList); // 재귀호출
      } else {
        try {
          Class<?> c = loadClass(file);  // class 이름만 추출해서 리턴해준다.
          if (!isAbstract(c)) {         // 추상클래스인지 확인하는 조건문을 메서드로 추출하였다.
            classList.add(c);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
  private Class<?> loadClass(File file) throws IOException, ClassNotFoundException {
    String path = file.getCanonicalPath().replaceAll("\\\\", "/").replaceAll(".class", ""); 
    // "패키지명 + 클래스명"만 추출
    int pos = path.indexOf("/bin/");
    return Class.forName(path.substring(pos + 5).replaceAll("/", "."));
  }
  
  private boolean isAbstract(Class<?> clazz) {
    if ((clazz.getModifiers() & Modifier.ABSTRACT) == Modifier.ABSTRACT) {
      return true;
    }
    return false;
  }
  /*
  public static void main(String[] args) throws Exception{
    ApplicationContext appContext = new ApplicationContext (new String[]{
        "bitcamp.java89.ems.server.controller",
        "bitcamp.java89.ems.server.dao"});
  }
  */
}
