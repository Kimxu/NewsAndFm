package kimxu.api.net;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.HttpHost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import kimxu.api.net.utils.NetworkUtil;
import kimxu.api.net.utils.VolleyStringUtil;

@SuppressWarnings("deprecation")
class IWHttpsClient extends DefaultHttpClient {

    public final String DEFAULT_USER_AGENT = "AppChinaClient";
    public final int CONNECTION_TIMEOUT = 20 * 1000;

    @SuppressLint("DefaultLocale")
    public IWHttpsClient(Context ctx) {
        HttpParams params = this.getParams();
        HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, CONNECTION_TIMEOUT);
        ConnManagerParams.setTimeout(params, CONNECTION_TIMEOUT);
        HttpProtocolParams.setUserAgent(params, DEFAULT_USER_AGENT);
		
		/*
		 * 移动 cmwap 10.0.0.172 80 
		 * 联通2G uniwap 10.0.0.172 80 
		 * 联通3G 3gwap 10.0.0.172 80 
		 * 电信 ctwap:cdma 10.0.0.200 80
		 */
        if (!NetworkUtil.hasMoreThanOneConnection(ctx) && !NetworkUtil.isWifiNetworkAvailable(ctx)) {
            ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
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
        params.setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
    }

    @Override
    protected ClientConnectionManager createClientConnectionManager() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        registry.register(new Scheme("https", newSslSocketFactory(), 443));
        return new SingleClientConnManager(getParams(), registry);
    }

    private SSLSocketFactory newSslSocketFactory() {
        try {
            KeyStore trustStore;
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            return new EasySSLSocketFactory(trustStore);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        return null;
    }
}
