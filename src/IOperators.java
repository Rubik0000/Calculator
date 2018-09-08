
/**
 * Provide an interface to determine operators and literals in string form
 */
public interface IOperators {
  
  /**
   * Checks whether a character is a digit
   * 
   * @param ch
   * @return true if it is else false
   */
  boolean isDigit(char ch);
  
  /**
   * Checks whether a given string can be represented as number
   * 
   * @param num a string with a number
   * @return true if it can else false
   */
  default boolean isNumber(String num) {
    for (int i = 0; i < num.length(); ++i) {
      if (!isDigit(num.charAt(i))) {
        return false;
      }
    }
    return true;
  }
 
  /**
   * Checks whether a character is an operator
   * 
   * @param ch
   * @return true if it is else false
   */
  boolean isOperator(String ch);
  
  /**
   * Checks whether a one operator has higher priority then an another 
   * 
   * @param op1
   * @param op2
   * @return -1 if op1 has less priority then op2 <br>
   *          0 if priorities are equal <br>
   *          1 if op1 has higher priority then op2 
   */
   int equalPriority(String op1, String op2);
  
  /**
   * Checks whether a string is an open brace
   * 
   * @param ch string
   * @return true if it is else false
   */
   boolean isOpenBrace(String ch);
  
  /**
   * Checks whether a character is an close brace
   * 
   * @param ch string
   * @return true if it is else false
   */
   boolean isCloseBrace(String ch);
   
   
   /**
    * Executes an operation represented as a string
    * 
    * @param op the operation
    * @param left a left operand
    * @param right a right operand
    * @return a result of executing the operation
    * @throws InvalidExpressionFormException if the operation is unknown
    */
   double performOperation(String op, double left, double right) 
       throws InvalidExpressionFormException;
}
