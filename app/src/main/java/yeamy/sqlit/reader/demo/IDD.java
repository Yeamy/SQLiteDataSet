package yeamy.sqlit.reader.demo;

import androidx.annotation.NonNull;


public class IDD {
    public int age;

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "age=" + age +
                '}';
    }
}