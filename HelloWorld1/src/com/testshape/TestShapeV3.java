package com.testshape;

public class TestShapeV3 {
	public static void main(String[] args) {
		Shape s = new Shape(1, 2, 3, 4);
		System.out.println("Shape is " + s);
		Square sq = new Square(5, 6, 10);
		System.out.print("Square is ");
		sq.Draw();
		Circle c = new Circle(10, 20, 5);
		System.out.print("Circle is ");
		c.Draw();
		Triangle t = new Triangle(1, 2, 3, 4, 9, 9);
		System.out.print("Triangle is ");
		t.Draw();
	}
}

class Shape {
	protected int left, top, width, height;

	protected static int min(int a, int b) {
		return a < b ? a : b;
	}

	protected static int min(int a, int b, int c) {
		return min(a, min(b, c));
	}

	protected static int max(int a, int b) {
		return a > b ? a : b;
	}

	protected static int max(int a, int b, int c) {
		return max(a, max(b, c));
	}

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

	public void Draw() {
		System.out.println("Shape is " + this);
	}
}

class Square extends Shape {

	public Square(int x, int y, int w) {
		super(x, y, w, w);
	}

	public void Draw() {
		System.out.println("Square: at(" + left + "," + top + ") size " + width);
	}
}

class Circle extends Shape {
	public Circle(int x, int y, int r) {
		super(x - r, y - r, 2 * r, 2 * r);
	}

	public void Draw() {
		System.out.println("Circle: at(" + (left + width / 2) + "," + (top + width / 2) + ") radius " + width / 2);
	}
}

class Triangle extends Shape {
	int x1, x2, y1, y2, x3, y3;

	public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		super(min(x1, x2, x3), min(y1, y2, y3), max(x1, x2, x3) - min(x1, x2, x3), max(y1, y2, y3) - min(y1, y2, y3));
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
	}

	public void Draw() {
		System.out.println("Triangle: (" + x1 + "," + y1 + "), " + x2 + "," + y2 + "), " + x3 + "," + y3 + "), ");
	}
}
