public class Multiply implements Expression {

	private Expression leftExpression;
	private Expression rightExpression;
	public Multiply(Expression leftExpression,Expression rightExpression){
		this.leftExpression=leftExpression;
		this.rightExpression=rightExpression;
	}
	public String toString() {
		return "("+leftExpression.toString()+"*"+rightExpression.toString()+")";
	}

	public double intepret(Context context) {
		return leftExpression.intepret(context)*rightExpression.intepret(context);
	}
}