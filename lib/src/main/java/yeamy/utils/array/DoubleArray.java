package yeamy.utils.array;

public class DoubleArray {

	private int length = 0;
	private double[] array;

	public DoubleArray() {
		this(16);
	}

	public DoubleArray(int length) {
		array = new double[length];
	}

	public void add(double n) {
		double[] src = this.array;
		int index = length;
		if (index == array.length) {
			double[] dest = new double[array.length + 16];
			System.arraycopy(src, 0, dest, 0, index);
			this.array = src = dest;
		}
		src[index] = n;
		++length;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append('[');
		boolean f = true;
		for (int i = 0; i < length; i++) {
			if (f) {
				f = false;
			} else {
				sb.append(',');
			}
			sb.append(array[i]);
		}
		sb.append(']');
		return sb.toString();
	}

	public void trimToSize() {
		double[] dest = new double[length];
		System.arraycopy(array, 0, dest, 0, length);
		this.array = dest;
	}

    public double get(int index) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        return array[index];
    }

    public void set(int index, double data) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        array[index] = data;
    }

	public double[] getArray() {
		double[] dest = new double[length];
		System.arraycopy(array, 0, dest, 0, length);
		return dest;
	}
}
