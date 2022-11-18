package yeamy.sqlit.reader.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView tv = new TextView(this);
        setContentView(tv);

        Handler handler = new Handler(Looper.getMainLooper());
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            Database db = new Database(getApplicationContext());
            String str = db.get();
            handler.post(() -> tv.setText(str));
            service.shutdown();
        });
    }

}
