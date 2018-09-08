
/**
 * Provides an interface to calculate given expression
 */
public interface ICalculator {
  
  /**
   * Calculate a postfix expression
   * 
   * @param exp an arithmetic expression in postfix form
   * @return result of the expression
   * @throws InvalidExpressionFormException
   */
  double calculatePostfix(String[] exp) throws InvalidExpressionFormException;
}
