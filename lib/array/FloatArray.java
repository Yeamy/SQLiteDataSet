package com.yeamy.utils.array;

public class FloatArray {

	private int size = 0;
	private float[] array;

	public FloatArray() {
		this(16);
	}

	public FloatArray(int size) {
		array = new float[size];
	}

	public void add(float n) {
		float[] src = this.array;
		int index = size;
		if (index == array.length) {
			float[] dest = new float[array.length + 16];
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
		float[] dest = new float[size];
		System.arraycopy(array, 0, dest, 0, size);
		this.array = dest;
	}

	public float[] getArray() {
		float[] dest = new float[size];
		System.arraycopy(array, 0, dest, 0, size);
		return dest;
	}
}
