package net.bi4vmr.study.permission;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AospPermissionChangeListener implements InvocationHandler {
    private static final String METHOD_EQUALS = "equals";
    private static final String METHOD_HASH_CODE = "hashCode";
    private static final String METHOD_ON_PERMISSION_CHANGE = "onPermissionsChanged";
    private static final String METHOD_TO_STRING = "toString";

    private final ChangeCallback mCallback;

    public static Object newInstance(Class<?>[] interfaces, ChangeCallback callback) {
        return Proxy.newProxyInstance(AospPermissionChangeListener.class.getClassLoader(),
                interfaces, new AospPermissionChangeListener(callback));
    }

    private AospPermissionChangeListener(ChangeCallback callback) {
        this.mCallback = callback;
    }

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
        if (METHOD_TO_STRING.equals(proxy))
            return toString();
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