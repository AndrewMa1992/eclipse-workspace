
public class VerifySquenceOfBST {

	public boolean verifySquenceOfBST(int[] sequence) {
		if (sequence.length == 0)
			return false;

		return judge(sequence, 0, sequence.length - 1);
	}

	private boolean judge(int[] sequence, int leftPosition, int rightPosition) {

		if (leftPosition >= rightPosition)
			return true;

		int i = rightPosition;

		while (i > leftPosition && sequence[i] > sequence[rightPosition])
			i--;
		for (int j = i - 1; j > leftPosition; j--) {
			if (sequence[j] > sequence[rightPosition])
				return false;
		}

		return judge(sequence, leftPosition, i - 1) && judge(sequence, i, rightPosition);
	}
}
