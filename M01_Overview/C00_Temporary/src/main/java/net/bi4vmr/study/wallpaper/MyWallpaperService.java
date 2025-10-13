package net.bi4vmr.study.wallpaper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

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
