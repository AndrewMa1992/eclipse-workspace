
public class TestShapeV2 {

	public static void main(String[] args) {
		Shape2 s = new Shape2(0, 0, 0, 0);
		System.out.println("Shape is " + s);
		Square2 sq = new Square2(5, 6, 10);
		System.out.println("Shape is " + sq);

	}
}

class Shape2 {
	private int left, top, width, height;

	static protected int min(int a, int b) {
		return a < b ? a : b;
	}

	static protected int min(int a, int b, int c) {
		return min(a, min(b, c));
	}

	static protected int max(int a, int b) {
		return a > b ? a : b;
	}

	static protected int max(int a, int b, int c) {
		return max(a, max(b, c));
	}

	public Shape2(int x, int y, int w, int h) {
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

class Square2 extends Shape2 {

	public Square2(int x, int y, int w) {
		super(x, y, w, w);
	}
}

class Circle2 extends Shape2 {
	public Circle2(int x, int y, int r) {
		super(x - r, y - r, 2 * r, 2 * r);
	}
}

class Triangle2 extends Shape2 {
	int x1, x2, y1, y2, x3, y3;

	public Triangle2(int x1, int y1, int x2, int y2, int x3, int y3) {
		super(min(x1, x2, x3), min(y1, y2, y3), max(x1, x2, x3) - min(x1, x2, x3), max(y1, y2, y3) - min(y1, y2, y3));
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
	}
}