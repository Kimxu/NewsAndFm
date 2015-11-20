package kimxu.api.net.utils;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.params.HttpParams;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@SuppressLint("DefaultLocale")
@SuppressWarnings("deprecation")
public final class NetworkUtil {
    public static final String WIFI = "WiFi";

    public static boolean isWifiNetworkAvailable(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            return false;
        }

        return info.getType() == ConnectivityManager.TYPE_WIFI;

    }

    public static void resetHttpClientWap(Context context, HttpClient httpClient) {
        HttpParams params = httpClient.getParams();
        if (isWifiNetworkAvailable(context)) {
            params.removeParameter(ConnRoutePNames.DEFAULT_PROXY);
        } else {
            Object proxy = params.getParameter(ConnRoutePNames.DEFAULT_PROXY);
            if (proxy == null) {
                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = manager.getActiveNetworkInfo();
                String extraInfo = null;
                if (info != null) {
                    extraInfo = VolleyStringUtil.strip(info.getExtraInfo());
                }
                if (!VolleyStringUtil.isEmptyOrWhitespace(extraInfo)) {
                    extraInfo = extraInfo.toLowerCase();
                    if (extraInfo.equals("cmwap") || extraInfo.equals("uniwap") || extraInfo.equals("3gwap")) {
                        params.setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost("10.0.0.172", 80, "http"));
                    }
                }
            }
        }
    }

    public static boolean hasMoreThanOneConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        } else {
            NetworkInfo[] info = manager.getAllNetworkInfo();
            int counter = 0;
            for (int i = 0; i < info.length; i++) {
                if (info[i].isConnected()) {
                    counter++;
                }
            }
            if (counter > 1) {
                return true;
            }
        }

        return false;
    }
}
