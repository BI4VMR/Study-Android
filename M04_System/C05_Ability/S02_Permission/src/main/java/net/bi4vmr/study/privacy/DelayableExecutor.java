package net.bi4vmr.study.privacy;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * A sub-class of {@link Executor} that allows Runnables to be delayed and/or cancelled.
 */
public interface DelayableExecutor extends Executor {
    /**
     * Execute supplied Runnable on the Executors thread after a specified delay.
     * <p>
     * See {@link android.os.Handler#postDelayed(Runnable, long)}.
     *
     * @return A Runnable that, when run, removes the supplied argument from the Executor queue.
     */
    default Runnable executeDelayed(Runnable r, long delayMillis) {
        return executeDelayed(r, delayMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Execute supplied Runnable on the Executors thread after a specified delay.
     * <p>
     * See {@link android.os.Handler#postDelayed(Runnable, long)}.
     *
     * @return A Runnable that, when run, removes the supplied argument from the Executor queue..
     */
    Runnable executeDelayed(Runnable r, long delay, TimeUnit unit);

    /**
     * Execute supplied Runnable on the Executors thread at a specified uptime.
     * <p>
     * See {@link android.os.Handler#postAtTime(Runnable, long)}.
     *
     * @return A Runnable that, when run, removes the supplied argument from the Executor queue.
     */
    default Runnable executeAtTime(Runnable r, long uptime) {
        return executeAtTime(r, uptime, TimeUnit.MILLISECONDS);
    }

    /**
     * Execute supplied Runnable on the Executors thread at a specified uptime.
     * <p>
     * See {@link android.os.Handler#postAtTime(Runnable, long)}.
     *
     * @return A Runnable that, when run, removes the supplied argument from the Executor queue.
     */
    Runnable executeAtTime(Runnable r, long uptimeMillis, TimeUnit unit);
}
