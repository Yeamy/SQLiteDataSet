package com.yeamy.utils.array;

public class IntArray {

	private int size = 0;
	private int[] array;

	public IntArray() {
		this(16);
	}

	public IntArray(int size) {
		array = new int[size];
	}

	public void add(int n) {
		int[] src = this.array;
		int index = size;
		if (index == array.length) {
			int[] dest = new int[array.length + 16];
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
		int[] dest = new int[size];
		System.arraycopy(array, 0, dest, 0, size);
		this.array = dest;
	}

	public int[] getArray() {
		int[] dest = new int[size];
		System.arraycopy(array, 0, dest, 0, size);
		return dest;
	}
}
