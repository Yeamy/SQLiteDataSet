package yeamy.utils.array;

public class BooleanArray {

    private int length = 0;
    private boolean[] array;

    public BooleanArray() {
        this(16);
    }

    public BooleanArray(int size) {
        array = new boolean[size];
    }

    public void add(boolean n) {
        boolean[] src = this.array;
        int index = length;
        if (index == array.length) {
            boolean[] dest = new boolean[array.length + 16];
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
        boolean[] dest = new boolean[length];
        System.arraycopy(array, 0, dest, 0, length);
        this.array = dest;
    }

    public boolean get(int index) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        return array[index];
    }

    public void set(int index, boolean data) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for length " + length);
        }
        array[index] = data;
    }

    public boolean[] getArray() {
        boolean[] dest = new boolean[length];
        System.arraycopy(array, 0, dest, 0, length);
        return dest;
    }
}
