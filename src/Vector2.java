
/**
 * This class calculates is responsible for representing a vector 2
 * It allows addition and multiplication
 */
public class Vector2 <T extends Number> {
	public T x;
	public T y;
	
	Vector2(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	//Adds this vector with the in vector and returns it
	public Vector2<Integer> add(Vector2<Integer> in){
		return new Vector2<Integer>(in.x.intValue() + 
				this.x.intValue(), in.y.intValue() + this.y.intValue());
	}
	
	//Multiplies this vector with the in vector and returns it
	public Vector2<Integer> multiply(Vector2<Integer> in){
		return new Vector2<Integer>(in.x.intValue() * 
				this.x.intValue(), in.y.intValue() * this.y.intValue());
	}
	
	//Multiplies this vector with the scalar value
	public Vector2<Integer> multiply(Integer value){
		return new Vector2<Integer>(this.x.intValue() * value, this.y.intValue() * value);
	}
}
