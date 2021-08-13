public class TestClass {

  public void newMethod() {
    System.out.println("Hello");
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("This is my new class");

    return sb.toString();
  }
}
