/**
 * 
 */

import java.util.Stack;

/**
 * Provide ability to perform arithmetic expressions with context of operators and literals
 */
public class Calculator implements ICalculator {

  private IOperators _operators;
  
  /**
   * Constructor
   *
   * @param ops a context of current operators and literals
   */
  public Calculator(IOperators ops) {
    _operators = ops;
  }
  
  @Override
  public double calculatePostfix(String[] exp) throws InvalidExpressionFormException {
    Stack<Double> values = new Stack<Double>();
    for (int i = 0; i < exp.length; ++i) {
      // if it is a number push it to stack
      if (_operators.isNumber(exp[i])) {
        values.push( Double.parseDouble(exp[i]) );
      }
      // if it is an operator pop two numbers form the stack and perform the operation
      else if ( _operators.isOperator(exp[i]) ) {
        if (values.isEmpty()) {
          throw new InvalidExpressionFormException("");
        }
        //double operand = values.pop();
        double rightOperand = values.pop();
        //if ( values.isEmpty() || _operators.isOperator(values.peek()) ) {}
        double leftOperand = values.pop();
        values.push( _operators.performOperation(exp[i], leftOperand, rightOperand) );
      }
      else {
        throw new InvalidExpressionFormException("Uknows");
      }
    }// for
    if (!values.isEmpty()) {
      return values.pop();
    }
    else {
      throw new InvalidExpressionFormException("вв");
    }
  }
}
