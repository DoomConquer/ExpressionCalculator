import java.util.Stack;
import java.util.Vector;
public class ReversePolishNotation {

	public Stack<String> transform(String expression){
		Stack<String> operator=new Stack<String>();       //存储操作符
		Stack<String> operand=new Stack<String>();       //存储最终逆波兰式
		Vector<String> value=new Vector<String>();        //存储操作数
		String[] values=expression.split("\\(|\\)|\\+|-|\\*|/");
		for(String s:values){           //去除分割出来的空字符串
			if(s.length()!=0){
				value.add(s);
			}
		}
		int k=0;
		for(int i=0;i<expression.length();i++){
			if(isOperator(String.valueOf(expression.charAt(i)))){
				if("(".equals(String.valueOf(expression.charAt(i)))){        //如果是"("直接进操作符栈
					operator.push("(");
				}
				else if(")".equals(String.valueOf(expression.charAt(i)))){ //如果是")"出操作符栈压入operand，直到遇到"("
					while(!"(".equals(operator.peek()))
						operand.push(operator.pop());
					operator.pop();
				}
				else{                                                          //运算符
					if(operator.isEmpty()){                         //如果栈空，直接进栈
						operator.push(String.valueOf(expression.charAt(i)));
					}                                                          //如果当前运算符优先级大于栈中运算符，直接进栈
					else if(isPriority(String.valueOf(expression.charAt(i)),operator.peek())){
						operator.push(String.valueOf(expression.charAt(i)));
					}
					else{                                                  //否则，出栈后再比较优先级直到栈空或大于栈定运算符
						while(!operator.isEmpty() && !isPriority(String.valueOf(expression.charAt(i)),operator.peek())){
							operand.push(operator.pop());
						}
							operator.push(String.valueOf(expression.charAt(i)));
					}
				}
			}
			else{
				operand.push(value.get(k));
				while(i<expression.length()){         //当输入的数字不是一位时，找到数字之后的运算符位置
					if(isOperator(String.valueOf(expression.charAt(i)))){
						i--;
						break;
					}
					i++;
				}
				k++;
			}
		}
		while(!operator.isEmpty())                           //将operator中剩下元素压入operand栈中
			operand.push(operator.pop());
		while(!operand.isEmpty())                           //operand栈中的元素需要逆转才是后缀表达式
			operator.push(operand.pop());
		return operator;
	}
	public boolean isOperator(String s){
		return s.equals("(")||s.equals(")")||s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/");
	}
	private boolean isPriority(String s1,String s2){
		switch(s1){
			case "+": case "-":
				return s2.equals("(")?true:false;
			case "*": case "/":
				return s2.equals("+")||s2.equals("-")||s2.equals("(")?true:false;
			default:
				return false;
		}
	}
}