package com.testshape;

import java.util.LinkedList;

public class TestShapeV4 {

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

		LinkedList<Shape> list = new LinkedList<Shape>();
		list.addLast(sq);
		list.addLast(c);
		list.addLast(t);
		for (Shape ss : list) {
			System.out.print("Shape from list is ");
			ss.Draw();
		}
	}
}
