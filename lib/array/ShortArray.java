package com.yeamy.utils.array;

public class ShortArray {

	private int size = 0;
	private short[] array;

	public ShortArray() {
		this(16);
	}

	public ShortArray(int size) {
		array = new short[size];
	}

	public void add(short n) {
		short[] src = this.array;
		int index = size;
		if (index == array.length) {
			short[] dest = new short[array.length + 16];
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
		short[] dest = new short[size];
		System.arraycopy(array, 0, dest, 0, size);
		this.array = dest;
	}

	public short[] getArray() {
		short[] dest = new short[size];
		System.arraycopy(array, 0, dest, 0, size);
		return dest;
	}
}
