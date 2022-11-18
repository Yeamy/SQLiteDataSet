package yeamy.utils.array;

public class FloatArray {

	private int length = 0;
	private float[] array;

	public FloatArray() {
		this(16);
	}

	public FloatArray(int length) {
		array = new float[length];
	}

	public void add(float n) {
		float[] src = this.array;
		int index = length;
		if (index == array.length) {
			float[] dest = new float[array.length + 16];
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
		float[] dest = new float[length];
		System.arraycopy(array, 0, dest, 0, length);
		this.array = dest;
	}

    public float get(int index) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        return array[index];
    }

    public void set(int index, float data) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        array[index] = data;
    }

	public float[] getArray() {
		float[] dest = new float[length];
		System.arraycopy(array, 0, dest, 0, length);
		return dest;
	}
}
