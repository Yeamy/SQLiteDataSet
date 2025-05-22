package yeamy.sqlite.android;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

class DsExtendField extends DsField {
    private final InternalDsFactory<?> factory;
    private final List<DsField> list;

    public static DsExtendField create(Field field, Cursor cursor, Map<Class<?>, DsFieldReader<?>> fieldMap)
            throws ReflectiveOperationException {
        InternalDsFactory<?> factory = new InternalDsFactory<>(field.getType());
        List<DsField> list = factory.createDsFields(cursor, fieldMap, false);
        return list.get(0).columnIndex == -1 ? null : new DsExtendField(field, factory, list);
    }

    DsExtendField(Field field, InternalDsFactory<?> factory, List<DsField> list) {
        super(field, list.get(0).columnIndex);
        this.factory = factory;
        this.list = list;
    }

    @Override
    int sortInt() {
        return 2;
    }

    @Override
    public void read(Cursor cursor, Object t) throws ReflectiveOperationException {
        field.set(t, this.factory.read(cursor, this.list));
    }
}