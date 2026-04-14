package net.bi4vmr.study.quicksetting;

import static android.service.quicksettings.Tile.STATE_ACTIVE;
import static android.service.quicksettings.Tile.STATE_INACTIVE;

import android.service.quicksettings.TileService;
import android.util.Log;

/**
 * 示例Tile。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MyTile extends TileService {

    private static final String TAG = MyTile.class.getSimpleName();

    private int count = 0;

    @Override
    public void onStartListening() {
        Log.i(TAG, "OnStartListening!");
    }

    @Override
    public void onStopListening() {
        Log.i(TAG, "OnStopListening!");
    }

    @Override
    public void onClick() {
        super.onClick();
        Log.i(TAG, "Tile clicked!");
        count++;
        if (count % 2 == 0) {
            getQsTile().setState(STATE_ACTIVE);
        } else {
            getQsTile().setState(STATE_INACTIVE);
        }
        getQsTile().updateTile();
    }
}
