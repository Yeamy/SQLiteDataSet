package com.yeamy.utils.array;

public class BooleanArray {

	private int size = 0;
	private boolean[] array;

	public BooleanArray() {
		this(16);
	}

	public BooleanArray(int size) {
		array = new boolean[size];
	}

	public void add(boolean n) {
		boolean[] src = this.array;
		int index = size;
		if (index == array.length) {
			boolean[] dest = new boolean[array.length + 16];
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
		boolean[] dest = new boolean[size];
		System.arraycopy(array, 0, dest, 0, size);
		this.array = dest;
	}

	public boolean[] getArray() {
		boolean[] dest = new boolean[size];
		System.arraycopy(array, 0, dest, 0, size);
		return dest;
	}
}
