
/**
 * Launch the program from command line with an expression as argument 
 *
 */
public class Calc {

  public static void main(String[] args) {
    try {
      IOperators ops = new ArithmeticOperators();
      IParser parser = new Parser(ops);
      ICalculator calc = new Calculator(ops);
      String[] postExp = parser.infixToPostfix(args[0]);
      System.out.println( calc.calculatePostfix( postExp ) );
    }
    catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }
}
