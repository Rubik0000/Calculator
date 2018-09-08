
/**
 * Provides an string representation of basic arithmetic operations (+, -, *, /)
 * and fractional numbers
 */
public class ArithmeticOperators implements IOperators {
  
  @Override
  public boolean isDigit(char ch) {
    return ch >= '0' && ch <= '9' || ch == '.';
  }
 
  @Override
  public boolean isOperator(String ch) {
    return ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/");
  }
  
  @Override
  public int equalPriority(String source, String other) {
    if ((source.equals("*") || source.equals("/")) && 
         !other.equals("*") && !other.equals("/")) {
      
      return 1;
    } 
    if ((source.equals("*") || source.equals("/")) && 
        (other.equals("*") || other.equals("/"))) {
      
      return 0;
    }
    return -1;
  }
  
  @Override
  public boolean isOpenBrace(String ch) {
    return ch.equals("(");
  }
  
  @Override
  public boolean isCloseBrace(String ch) {
    return ch.equals(")");
  }

  @Override
  public double performOperation(String op, double left, double right) 
      throws InvalidExpressionFormException {
    
    if (op.equals("+")) {
      return left + right;
    }
    if (op.equals("-")) {
      return left - right;
    }
    if (op.equals("*")) {
      return left * right;
    }
    if (op.equals("/")) {
      return left / right;
    }
    throw new InvalidExpressionFormException("Unknown operator");
  }
}
