package yeamy.utils.array;

public class ShortArray {

	private int length = 0;
	private short[] array;

	public ShortArray() {
		this(16);
	}

	public ShortArray(int length) {
		array = new short[length];
	}

	public void add(short n) {
		short[] src = this.array;
		int index = length;
		if (index == array.length) {
			short[] dest = new short[array.length + 16];
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
		short[] dest = new short[length];
		System.arraycopy(array, 0, dest, 0, length);
		this.array = dest;
	}

    public short get(int index) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        return array[index];
    }

    public void set(int index, short data) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        array[index] = data;
    }

	public short[] getArray() {
		short[] dest = new short[length];
		System.arraycopy(array, 0, dest, 0, length);
		return dest;
	}
}
