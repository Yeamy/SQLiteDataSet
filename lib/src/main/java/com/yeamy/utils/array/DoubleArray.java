package com.yeamy.utils.array;

public class DoubleArray {

	private int size = 0;
	private double[] array;

	public DoubleArray() {
		this(16);
	}

	public DoubleArray(int size) {
		array = new double[size];
	}

	public void add(double n) {
		double[] src = this.array;
		int index = size;
		if (index == array.length) {
			double[] dest = new double[array.length + 16];
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
		double[] dest = new double[size];
		System.arraycopy(array, 0, dest, 0, size);
		this.array = dest;
	}

	public double[] getArray() {
		double[] dest = new double[size];
		System.arraycopy(array, 0, dest, 0, size);
		return dest;
	}
}
