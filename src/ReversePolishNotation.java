import java.util.Stack;
import java.util.Vector;
public class ReversePolishNotation {

	public Stack<String> transform(String expression){
		Stack<String> operator=new Stack<String>();       //�洢������
		Stack<String> operand=new Stack<String>();       //�洢�����沨��ʽ
		Vector<String> value=new Vector<String>();        //�洢������
		String[] values=expression.split("\\(|\\)|\\+|-|\\*|/");
		for(String s:values){           //ȥ���ָ�����Ŀ��ַ���
			if(s.length()!=0){
				value.add(s);
			}
		}
		int k=0;
		for(int i=0;i<expression.length();i++){
			if(isOperator(String.valueOf(expression.charAt(i)))){
				if("(".equals(String.valueOf(expression.charAt(i)))){        //�����"("ֱ�ӽ�������ջ
					operator.push("(");
				}
				else if(")".equals(String.valueOf(expression.charAt(i)))){ //�����")"��������ջѹ��operand��ֱ������"("
					while(!"(".equals(operator.peek()))
						operand.push(operator.pop());
					operator.pop();
				}
				else{                                                          //�����
					if(operator.isEmpty()){                         //���ջ�գ�ֱ�ӽ�ջ
						operator.push(String.valueOf(expression.charAt(i)));
					}                                                          //�����ǰ��������ȼ�����ջ���������ֱ�ӽ�ջ
					else if(isPriority(String.valueOf(expression.charAt(i)),operator.peek())){
						operator.push(String.valueOf(expression.charAt(i)));
					}
					else{                                                  //���򣬳�ջ���ٱȽ����ȼ�ֱ��ջ�ջ����ջ�������
						while(!operator.isEmpty() && !isPriority(String.valueOf(expression.charAt(i)),operator.peek())){
							operand.push(operator.pop());
						}
							operator.push(String.valueOf(expression.charAt(i)));
					}
				}
			}
			else{
				operand.push(value.get(k));
				while(i<expression.length()){         //����������ֲ���һλʱ���ҵ�����֮��������λ��
					if(isOperator(String.valueOf(expression.charAt(i)))){
						i--;
						break;
					}
					i++;
				}
				k++;
			}
		}
		while(!operator.isEmpty())                           //��operator��ʣ��Ԫ��ѹ��operandջ��
			operand.push(operator.pop());
		while(!operand.isEmpty())                           //operandջ�е�Ԫ����Ҫ��ת���Ǻ�׺���ʽ
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