package kimxu.test;

import android.util.Log;

/**
 * Created by xuzhiguo on 15/12/10.
 */
public class Test {
    private static final String TAG = "Test";


    public void test(){
        IHouse house = new House("Downton Abbey", 5000);
        IHouse proxyHouse = new ProxyHouse(house);
        Log.i(TAG, "looking for a perfect house");
        proxyHouse.getHouseInfo();
        Log.i(TAG, "thinking");
        proxyHouse.signContract();
        proxyHouse.payFees();
        Log.i(TAG, "so easy");
    }
}
