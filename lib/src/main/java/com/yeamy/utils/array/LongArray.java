package com.yeamy.utils.array;

public class LongArray {

	private int size = 0;
	private long[] array;

	public LongArray() {
		this(16);
	}

	public LongArray(int size) {
		array = new long[size];
	}

	public void add(long n) {
		long[] src = this.array;
		int index = size;
		if (index == array.length) {
			long[] dest = new long[array.length + 16];
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
		long[] dest = new long[size];
		System.arraycopy(array, 0, dest, 0, size);
		this.array = dest;
	}

	public long[] getArray() {
		long[] dest = new long[size];
		System.arraycopy(array, 0, dest, 0, size);
		return dest;
	}
}
