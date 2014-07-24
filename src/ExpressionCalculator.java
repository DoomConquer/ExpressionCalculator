/**
 * @(#)ExpressionCalculator.java
 *
 * @author Black
 *
 * @version 1.00 2014/7/23
 */
import java.util.Scanner;
import java.util.Stack;
public class ExpressionCalculator {

    public static void main(String[] args) {
    	String expression;
    	Scanner scanner=new Scanner(System.in);
    	expression=scanner.nextLine();
		ReversePolishNotation reversePolishNotation=new ReversePolishNotation();
		Stack<String> operation=reversePolishNotation.transform(expression);
		Stack<Expression> digitStack=new Stack<Expression>();
		Context context=new Context();
		while(!operation.isEmpty()){
			if(reversePolishNotation.isOperator(operation.peek())){
				Expression op2=digitStack.pop();
				Expression op1=digitStack.pop();
				Expression outcome=null;
				switch(operation.peek()){
					case "+":
						outcome=new Add(op1,op2);
						break;
					case "-":
						outcome=new Minus(op1,op2);
						break;
					case "*":
						outcome=new Multiply(op1,op2);
						break;
					case "/":
						outcome=new Divide(op1,op2);
						break;
				}
				digitStack.push(outcome);
				operation.pop();
			}
			else{
				String str_op=operation.pop();
				double op=Double.parseDouble(str_op);
				Expression digit=new Digit(str_op);
				context.addDigit(digit,op);
				digitStack.push(digit);
			}
		}
		Expression answer=digitStack.pop();
		System.out.println(answer.toString());
		System.out.println(answer.intepret(context));
    }
}