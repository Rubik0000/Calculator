
/** 
 * Provides an interface to parse distinct forms of arithmetic expressions 
 */
public interface IParser {
  
  /**
   * Converts an infix expression to postfix one
   * 
   * @param infixExp an expression in infix form
   * @return an array of constants, operators, etc. in postfix notation
   */
  String[] infixToPostfix(String infixExp) throws InvalidExpressionFormException;
  
}
