package yeamy.sqlite.android;

import android.database.Cursor;

import androidx.annotation.RequiresApi;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RequiresApi(33)
class DsExtendField9 extends DsField {
    private final InternalDsFactory<?> factory;
    private final List<DsField> list;
    private final VarHandle vh;

    public static DsExtendField9 create(Class<?> type, Field field, Cursor cursor, Map<Class<?>, DsFieldReader<?>> fieldMap) throws ReflectiveOperationException {
        InternalDsFactory<?> factory = new InternalDsFactory<>(field.getType());
        List<DsField> list = factory.createDsFields(cursor, fieldMap, true);
        return list.get(0).columnIndex == -1 ? null : new DsExtendField9(type, field, factory, list);
    }

    DsExtendField9(Class<?> type, Field field, InternalDsFactory<?> factory, List<DsField> list) throws ReflectiveOperationException {
        super(field, list.get(0).columnIndex);
        this.factory = factory;
        this.list = list;
        this.vh = MethodHandles.lookup().findVarHandle(field.getType(), field.getName(), type);
    }

    @Override
    int sortInt() {
        return 2;
    }

    @Override
    public void read(Cursor cursor, Object t) throws ReflectiveOperationException {
        vh.set(this.factory.read(cursor, this.list));
    }
}