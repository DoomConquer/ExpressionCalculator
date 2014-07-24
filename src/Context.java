import java.util.Map;
import java.util.HashMap;
public class Context {

	private Map<Expression,Double>map;
	public Context(){
		map=new HashMap<Expression,Double>();
	}
	public void addDigit(Expression digit,double value){
		map.put(digit,value);
	}
	public double getValue(Digit digit){
		return map.get(digit);
	}
}