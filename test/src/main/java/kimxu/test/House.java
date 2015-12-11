package kimxu.test;

import android.os.SystemClock;
import android.util.Log;

import java.text.SimpleDateFormat;

/**
 * Created by xuzhiguo on 15/12/10.
 */
public class House implements IHouse {
    private final String TAG = House.class.getSimpleName();
    private String name;
    private double price;

    public House(String name, double price){
        this.name = name;
        this.price = price;
    }

    @Override
    public void getHouseInfo() {
        Log.i(TAG, "House Info- name:" + name + "  ￥:" + price);
    }

    @Override
    public void signContract() {
        Log.i(TAG, "Contract:" + name + "  signed at" +
                new SimpleDateFormat("HH:mm:ss").format(SystemClock.uptimeMillis()));
    }

    @Override
    public void payFees() {
        Log.i(TAG, "Bill: name-" + name + "  $-" + price);
    }
}
