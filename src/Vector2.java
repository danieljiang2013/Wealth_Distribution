
public class Vector2 <T extends Number> {
	public T x;
	public T y;
	
	Vector2(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2<Integer> add(Vector2<Integer> in){
		return new Vector2<Integer>(in.x.intValue() + 
				this.x.intValue(), in.y.intValue() + this.y.intValue());
	}
	
	public Vector2<Integer> multiply(Vector2<Integer> in){
		return new Vector2<Integer>(in.x.intValue() * 
				this.x.intValue(), in.y.intValue() * this.y.intValue());
	}
	
	public Vector2<Integer> multiply(Integer value){
		return new Vector2<Integer>(this.x.intValue() * value, this.y.intValue() * value);
	}
}
