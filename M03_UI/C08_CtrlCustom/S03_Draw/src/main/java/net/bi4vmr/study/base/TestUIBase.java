package net.bi4vmr.study.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().post(() -> {
            // 创建画布
            int width = binding.ivDraft.getWidth();
            int height = binding.ivDraft.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bmp);

            // 创建画笔，颜色为绿色。
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);

            // 将整个画布填充为黑色
            canvas.drawColor(Color.BLACK);
            // 以 `(100, 100)` 为顶点，绘制一个宽度为400像素、高度为100像素的矩形。
            canvas.drawRect(100, 100, 500, 200, paint);

            // 将画笔颜色改为青色
            paint.setColor(Color.CYAN);
            // 以 `(600, 150)` 为圆心，绘制一个半径为50像素的圆。
            canvas.drawCircle(600, 150, 50, paint);

            binding.ivDraft.setImageBitmap(bmp);
        });
    }
}
