package yeamy.utils.array;

public class ByteArray {

	private int size = 0;
	private byte[] array;

	public ByteArray() {
		this(16);
	}

	public ByteArray(int size) {
		array = new byte[size];
	}

	public void add(byte n) {
		byte[] src = this.array;
		int index = size;
		if (index == array.length) {
			byte[] dest = new byte[array.length + 16];
			System.arraycopy(src, 0, dest, 0, index);
			this.array = src = dest;
		}
		src[index] = n;
		++size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append('[');
		boolean f = true;
		for (int i = 0; i < size; i++) {
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
		byte[] dest = new byte[size];
		System.arraycopy(array, 0, dest, 0, size);
		this.array = dest;
	}

	public byte[] getArray() {
		byte[] dest = new byte[size];
		System.arraycopy(array, 0, dest, 0, size);
		return dest;
	}
}
