package net.bi4vmr.study.path;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiPathBinding;

/**
 * 测试界面：路径。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIPath extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIPath.class.getSimpleName();

    private TestuiPathBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiPathBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().post(() -> {
            test_Single();
        });
    }

    private void test_Single() {
        int width = binding.ivLines.getWidth();
        int height = binding.ivLines.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        paint.setPathEffect(new CornerPathEffect(10));

        Path path = new Path();
        path.lineTo(100, 100);
        path.lineTo(150, 100);
        path.lineTo(200, 150);

        canvas.drawPath(path, paint);

        binding.ivLines.setImageBitmap(bmp);
    }
}
