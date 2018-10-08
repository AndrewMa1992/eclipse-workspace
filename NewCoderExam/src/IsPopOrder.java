import java.util.ArrayList;

public class IsPopOrder {

	public boolean isPopOrder(int[] pushA, int[] popA) {
		if (pushA.length == 0)
			return false;
		ArrayList<Integer> arraylist = new ArrayList<Integer>();
		for (int i = 0, j = 0; i < pushA.length; i++) {
			arraylist.add(pushA[i]);
			while (j < popA.length && arraylist.get(arraylist.size() - 1) == popA[j]) {
				arraylist.remove(arraylist.size() - 1);
				j++;
			}
		}
		return arraylist.isEmpty();
	}
}
