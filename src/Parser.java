
import java.util.Stack;
import java.util.LinkedList;

public class Parser implements IParser {

  /**
   * Checks whether a character is a digit or dot
   * 
   * @param ch
   * @return 
   */
  private boolean isNumber(char ch) {
    return ch >= '0' && ch <= '9' || ch == '.';
  }
 
  /**
   * Checks whether a character is an operator
   * 
   * @param ch
   * @return
   */
  private boolean isOperator(String ch) {
    return ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/");
  }
  
  /**
   * Checks whether a one operator has higher priority then an another 
   * 
   * @param source
   * @param other
   * @return -1 if source has less priority then other <br>
   *          0 if priorities are equal <br>
   *          1 if source has higher priority then other 
   */
  private int hasHigherPriop(String source, String other) {
    if ((source.equals("*") || source.equals("/")) 
        && !other.equals("*") && !other.equals("/")) 
    {
      return 1;
    } 
    if ((source.equals("*") || source.equals("/")) && 
        (other.equals("*") || other.equals("/"))) 
    {
      return 0;
    }
    return -1;
  }
  
  /**
   * Checks whether a character is an open brace
   * 
   * @param ch
   * @return
   */
  private boolean isOpenBrace(String ch) {
    return ch.equals("(");
  }
  
  /**
   * Checks whether a character is an close brace
   * 
   * @param ch
   * @return
   */
  private boolean isCloseBrace(String ch) {
    return ch.equals(")");
  }
  
  @Override
  public String[] infixToPostfix(String infixExp) throws InvalidExpressionFormException {
    LinkedList<String> postFix = new LinkedList<String>();
    Stack<String> operators = new Stack<String>();
    String lit = "";
    for (int i = 0; i < infixExp.length(); ++i) {
      if ( isNumber( infixExp.charAt(i) ) ) {
        lit += infixExp.charAt(i);
      }
      else {
        if (lit != "") {
          postFix.add(lit);
          lit = "";
        }
        String token = String.valueOf( infixExp.charAt(i) );
        if ( isOperator( token ) ) {
          while ( !operators.isEmpty() && 
                  isOperator(operators.peek()) &&
                  hasHigherPriop(token, operators.peek()) <= 0) 
          {
            postFix.add( operators.pop() );
          }
          operators.push( token );
        }
        else if ( isOpenBrace( token ) ) {
          operators.push(token);
        }
        else if ( isCloseBrace(token) ) {
          while ( !operators.isEmpty() && !isOpenBrace(operators.peek()) ) {
            postFix.add( operators.pop() );
          }
          if (operators.isEmpty()) {
            throw new InvalidExpressionFormException("No braces balance");
          }
          operators.pop();
        }
        else {
          throw new InvalidExpressionFormException("Invalid symbols");
        }
      }// else
    }// for
    while (!operators.isEmpty()) {
      postFix.add( operators.pop() );
    }
    return postFix.toArray(new String[0]);
  }

	

}
