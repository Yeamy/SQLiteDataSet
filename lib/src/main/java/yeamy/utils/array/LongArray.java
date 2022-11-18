package yeamy.utils.array;

public class LongArray {

	private int length = 0;
	private long[] array;

	public LongArray() {
		this(16);
	}

	public LongArray(int length) {
		array = new long[length];
	}

	public void add(long n) {
		long[] src = this.array;
		int index = length;
		if (index == array.length) {
			long[] dest = new long[array.length + 16];
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
		long[] dest = new long[length];
		System.arraycopy(array, 0, dest, 0, length);
		this.array = dest;
	}

    public long get(int index) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        return array[index];
    }

    public void set(int index, long data) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        array[index] = data;
    }

	public long[] getArray() {
		long[] dest = new long[length];
		System.arraycopy(array, 0, dest, 0, length);
		return dest;
	}
}
