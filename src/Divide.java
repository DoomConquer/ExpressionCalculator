public class Divide implements Expression {

	private Expression leftExpression;
	private Expression rightExpression;
	public Divide(Expression leftExpression,Expression rightExpression){
		this.leftExpression=leftExpression;
		this.rightExpression=rightExpression;
	}
	public String toString() {
		return "("+leftExpression.toString()+"/"+rightExpression.toString()+")";
	}

	public double intepret(Context context) {
		return leftExpression.intepret(context)/rightExpression.intepret(context);
	}
}