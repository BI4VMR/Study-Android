package net.bi4vmr.study.permission;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PermissionChangeListener implements InvocationHandler {
    private static final String METHOD_EQUALS = "equals";
    private static final String METHOD_HASH_CODE = "hashCode";
    private static final String METHOD_ON_PERMISSION_CHANGE = "onPermissionsChanged";
    private static final String METHOD_TO_STRING = "toString";

    private final ChangeCallback mCallback;

    public static Object newInstance(Class<?>[] interfaces, ChangeCallback callback) {
        return Proxy.newProxyInstance(PermissionChangeListener.class.getClassLoader(),
                interfaces, new PermissionChangeListener(callback));
    }

    private PermissionChangeListener(ChangeCallback callback) {
        this.mCallback = callback;
    }

    /**
     *
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method == null) {
            Log.d("TestApp","invoke methodName is null!");
            return null;
        }
        proxy = method.getName();
        boolean isArgsValid = args != null && args[0] != null;
        if (METHOD_ON_PERMISSION_CHANGE.equals(proxy) && isArgsValid) {
            int uid = (int) args[0];
            if (mCallback != null) {
                mCallback.onRuntimePermissionChange(uid);
            }
            return null;
        }
        if (METHOD_HASH_CODE.equals(proxy))
            return hashCode();
        if (METHOD_EQUALS.equals(proxy)) {
            if (isArgsValid) {
                return hashCode() == args[0].hashCode();
            } else {
                return false;
            }
        }
        return null;
    }

    interface ChangeCallback {
        void onRuntimePermissionChange(int uid);
    }
}
