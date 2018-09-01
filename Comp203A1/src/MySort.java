import java.util.Random;

public class MySort {

	public static void main(String[] args) {
		String sort = args[0];
		Integer number = Integer.parseInt(args[1]);
		IntList list = new IntList();
		Random rd = new Random();
		for (int i = 1; i <= number; i++) {
			list.Add(rd.nextInt(number + 1));
		}
		if (sort.equals("i")) {
			list.Dump();
			list.InsertionSort();;
			list.Dump();
		} else if(sort.equals("q")) {
			list.Dump();
			list.QuickSort(1, number);
			System.out.println("The numbers of comparison of quick sort is " + IntList.qsortCount);
			list.Dump();
		}else if(sort.equals("b")) {
			list.Dump();
			list.BubbleSort();
			list.Dump();
		}
	}

}
