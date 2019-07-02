
public class TestShapeV1 {

	public static void main(String[] args) {
		Shape s = new Shape(0, 0, 0, 0);
		System.out.println("Shape is " + s);
		Square sq = new Square(5, 6, 10);
		System.out.println("Shape is " + sq);

	}
}

class Shape {
	private int left, top, width, height;

	public Shape(int x, int y, int w, int h) {
		left = x;
		top = y;
		width = w;
		height = h;
	}

	@Override
	public String toString() {
		return "Shape(L:" + left + ",T:" + top + ",W:" + width + ",H:" + height + ")";
	}
}

class Square extends Shape {

	public Square(int x, int y, int w) {
		super(x, y, w, w);
	}
}
