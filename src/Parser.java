
import java.util.Stack;
import java.util.LinkedList;

/**
 * Parses different forms of arithmetic expressions 
 * with given context of operators and literals
 */
public class Parser implements IParser {
  
  private IOperators _operators;
  
  /**
   * Constructor
   *
   * @param ops a context of current operators and literals
   */
  public Parser(IOperators ops) {
    _operators = ops;
  }

  /**
   * Returns a given string without spaces
   * 
   * @param str
   * @return
   */
  private String getPureString(String str) {
    StringBuilder pureStr = new StringBuilder(str.length());
    for (int i = 0; i < str.length(); ++i) {
      if (str.charAt(i) != ' ') {
        pureStr.append(str.charAt(i));
      }
    }
    return pureStr.toString();
  }
  
  /**
   * Uses shunting-yard algorithm
   */
  @Override
  public String[] infixToPostfix(String infixExp) throws InvalidExpressionFormException {
    infixExp = getPureString(infixExp);
    
    // output postfix representation
    LinkedList<String> postFix = new LinkedList<String>();
    
    // stack of operators and braces
    Stack<String> operators = new Stack<String>();
    String numberLiteral = "";
    String prev = "";
    for (int i = 0; i < infixExp.length(); ++i) {
      // if a character is a digit then concatenate it to number  
      if ( _operators.isDigit( infixExp.charAt(i) ) ) {
        numberLiteral += infixExp.charAt(i);
      }
      // if the character is not a digit
      else {
        // push a number to the stack
        if (numberLiteral != "") {
          postFix.add(numberLiteral);
          prev = numberLiteral;
          numberLiteral = "";
        }
        // get current token from the character
        String token = String.valueOf( infixExp.charAt(i) );
        // if it is an operator
        if ( _operators.isOperator( token ) ) {
          if (_operators.canBeUnary(token) && !_operators.isNumber(prev)) {
            operators.push(_operators.markAsUnary(token) );
          }
          else {
            // while the top of the stack is an operator 
            // and has lower or equal priority then current one
            // append the top to output value
            while ( !operators.isEmpty() && 
                    _operators.isOperator(operators.peek()) &&
                    _operators.equalPriority(token, operators.peek()) <= 0) {
              
              postFix.add( operators.pop() );
            }
            operators.push( token );
          }
        }
        // if it is an open brace push it to the stack
        else if ( _operators.isOpenBrace( token ) ) {
          operators.push(token);
        }
        // if it is a close brace
        else if ( _operators.isCloseBrace(token) ) {
          // while the top of the stack is not an open brace
          // append it to output value
          while ( !operators.isEmpty() && 
                  !_operators.isOpenBrace(operators.peek()) ) {
            
            postFix.add( operators.pop() );
          }
          // if after end of the cycle there is no open brace
          // it means that braces are not balances
          if ( operators.isEmpty() || !_operators.isOpenBrace(operators.pop()) ) {
            throw new InvalidExpressionFormException("No braces balance");
          }
        }
        // if token is unknown
        else {
          throw new InvalidExpressionFormException("Invalid symbols");
        }
        prev = token;
      }// else
    }// for
    
    // add left numbers and operators
    if (numberLiteral != "") {
      postFix.add(numberLiteral);
    }
    while (!operators.isEmpty()) {
      postFix.add( operators.pop() );
    }
    return postFix.toArray(new String[0]);
  }
}
