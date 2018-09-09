
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
    return isUnary(ch) || ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/");
  }
  
  @Override
  public boolean canBeUnary(String op) {
    return op.equals("-") || op.equals("+");
  }
  
  @Override
  public boolean isUnary(String op) {
    return op.matches("^u-") || op.matches("^u\\+");
  }
  
  @Override
  public String markAsUnary(String op) {
    return "u" + op;
  }
  
  /**
   * Removes unary mark from an operator
   * 
   * @param op the operator
   * @return the operator without unary mark
   */
  private String unMarkUnary(String op) {
    return isUnary(op) ? op.substring(1) : op;
  }
  
  @Override
  public int equalPriority(String op1, String op2) {
    boolean isUnOp1 = isUnary(op1);
    boolean isUnOp2 = isUnary(op2);
    if (isUnOp1 && !isUnOp2) {
      return 1;
    }
    if (!isUnOp1 && isUnOp2) {
      return -1;
    }
    if (isUnOp1 && isUnOp2) {
      return 0;
    }
    if ((op1.equals("*") || op1.equals("/")) && 
         !op2.equals("*") && !op2.equals("/")) {
      
      return 1;
    } 
    if ((op1.equals("*") || op1.equals("/")) && 
        (op2.equals("*") || op2.equals("/"))) {
      
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

  @Override
  public double performOperation(String op, double operand) 
      throws InvalidExpressionFormException {
    
    if (!isUnary(op)) { 
      throw new InvalidExpressionFormException("Unknown operator");
    }
    return performOperation(unMarkUnary(op), 0, operand);
  }
}
