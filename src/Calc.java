
public class Calc {

  public static void main(String[] args) {
    try {
      Parser parser = new Parser();
      var post = parser.infixToPostfix( "3+4*2/(1-5)" );
      for (int i = 0; i < post.length; ++i) {
        System.out.print(post[i]);
      }
    }
    catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }

}
