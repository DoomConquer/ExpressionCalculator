public class Digit implements Expression {

	private String name;
	public Digit(String name){
		this.name=name;
	}
	public String toString() {
		return name;
	}

	public double intepret(Context context) {
		return context.getValue(this);
	}
}