package net.bi4vmr.study.wallpaper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

/**
 * Name        : MyWallpaperService
 * <p>
 * Author      : 詹屹罡
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-10-19 10:32
 * <p>
 * Description : TODO 添加描述
 */
public class MyWallpaperService extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new MyEngine();
    }

    public class MyEngine extends WallpaperService.Engine {

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.CYAN);
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
