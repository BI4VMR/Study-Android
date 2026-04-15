package net.bi4vmr.study.point;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiPointBinding;

/**
 * 测试界面：点。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIPoint extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIPoint.class.getSimpleName();

    private TestuiPointBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiPointBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().post(() -> {
            test_Single();
            test_Batch();
        });
    }

    private void test_Single() {
        int width = binding.ivSingle.getWidth();
        int height = binding.ivSingle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        // 创建画笔并设置样式：尺寸为5像素，颜色为红色。
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);

        // 在画布 `(50, 50)` 的位置绘制一个点
        canvas.drawPoint(50, 50, paint);
        // 在画布 `(100, 100)` 的位置绘制一个点
        canvas.drawPoint(100, 100, paint);

        binding.ivSingle.setImageBitmap(bmp);
    }

    private void test_Batch() {
        int width = binding.ivSingle.getWidth();
        int height = binding.ivSingle.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint p = new Paint();
        p.setStrokeWidth(10);
        p.setColor(Color.GREEN);

        // 使用数组表示一批点，每两个元素为一组，分别表示点的X坐标与Y坐标。
        float[] points = {50, 50, 100, 100, 50, 100, 100, 50};
        canvas.drawPoints(points, p);

        binding.ivBatch.setImageBitmap(bmp);
    }
}
