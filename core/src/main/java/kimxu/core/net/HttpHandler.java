package kimxu.core.net;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class HttpHandler extends Handler {
    WeakReference<IHandleMessage> reference;

    public HttpHandler(IHandleMessage activity) {
        reference = new WeakReference<IHandleMessage>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        final IHandleMessage activity = reference.get();
        if (activity != null) {
            activity.handleMessage(msg);
        }
    }
}