package yeamy.sql;

import java.lang.reflect.Field;

public enum DsType {
	_int(int.class), //
	_long(long.class), //
	_float(float.class), //
	_double(double.class), //
	_boolean(boolean.class), //
	_short(short.class), //
	Integer(Integer.class), //
	Long(Long.class), //
	Float(Float.class), //
	Double(Double.class), //
	Short(Short.class), //
	Boolean(Boolean.class), //
	BigDecimal(java.math.BigDecimal.class), //
	String(String.class), //
    Blob(byte[].class),
    Extra(Object.class), //
	;

	private final Class<?> type;

	DsType(Class<?> type) {
		this.type = type;
	}

	public static DsType getDsType(Field field) {
		DsType[] types = DsType.values();
		Class<?> src = field.getType();
		for (DsType type : types) {
			if (src.isAssignableFrom(type.type)) {
				return type;
			}
		}
		return Extra;
	}

}
