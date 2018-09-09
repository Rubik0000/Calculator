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
          throw new InvalidExpressionFormException("Invalid expression");
        }
        double operand = values.pop();
        double result;
        // if the operator is unary 
        if (_operators.isUnary(exp[i])) {
          result = _operators.performOperation(exp[i], operand); 
        }
        // if it is binary
        else {
          double rightOperand = operand;
          if (values.isEmpty()) {
            throw new InvalidExpressionFormException("Invalid expression");
          }
          double leftOperand = values.pop();
          result = _operators.performOperation(exp[i], leftOperand, rightOperand);
        }
        values.push(result);
      }// else if ( _operators.isOperator(exp[i]) )
      else {
        throw new InvalidExpressionFormException("Uknown token");
      }
    }// for
    if (!values.isEmpty()) {
      return values.pop();
    }
    else {
      throw new InvalidExpressionFormException("Invalid expression");
    }
  }
}
