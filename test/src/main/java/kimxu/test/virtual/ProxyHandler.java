package kimxu.test.virtual;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by xuzhiguo on 15/12/10.
 */
public class ProxyHandler implements InvocationHandler {

    private final String TAG = ProxyHandler.class.getSimpleName();
    Object targetObj;
    public Object newProxyInstance(Object targetObj){
        this.targetObj = targetObj;
        return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(),
                targetObj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret;
        Log.i(TAG, "method name:" + method.getName());
        ret = method.invoke(targetObj, args);
        return ret;
    }
}
