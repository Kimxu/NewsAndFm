package kimxu.mvp.communication;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kimxu on 16/1/28.
 * 函数的参数，当函数的参数涉及到多个值时，可以用此类，
 * 此类使用规则：存参数与取参数的顺序必须一致，否则报错
 */
public class FunctionParams {
    private Bundle mParams = new Bundle(1);
    private int mIndex = -1;
    private Map mObjectParams = new HashMap(1);

    FunctionParams(Bundle mParams, Map mObjectParams) {
        this.mParams = mParams;
        this.mObjectParams = mObjectParams;
    }

    public <Param> Param getObject(Class<Param> p) {
        if (mObjectParams == null) {
            return null;
        }
        return p.cast(mObjectParams.get((mIndex++) + ""));
    }

    /**
     * 获取int值
     *
     * @return
     */
    public int getInt() {
        if (mParams != null) {
            return mParams.getInt((mIndex++) + "");
        }
        return 0;
    }

    /**
     * 获取int值
     *
     * @param defalut
     * @return
     */
    public int getInt(int defalut) {
        if (mParams != null) {
            return mParams.getInt((mIndex++) + "");
        }
        return defalut;
    }

    /**
     * 获取字符串
     *
     * @param defalut
     * @return
     */
    public String getString(String defalut) {
        if (mParams != null) {
            return mParams.getString((mIndex++) + "");
        }
        return defalut;
    }

    /**
     * 获取字符串
     *
     * @return
     */
    public String getString() {
        if (mParams != null) {
            return mParams.getString((mIndex++) + "");
        }
        return null;
    }


    /**
     * 获取Boolean值
     *
     * @return 默认返回false
     */
    public boolean getBoolean() {
        if (mParams != null) {
            return mParams.getBoolean((mIndex++) + "");
        }
        return false;
    }

    /**
     * 该类用来创建函数参数
     */
    public static class FunctionParamsBuilder {
        private Bundle mParams;
        private int mIndex = -1;
        private Map mObjectParams = new HashMap(1);

        public FunctionParamsBuilder() {

        }

        public FunctionParamsBuilder putInt(int value) {
            if (mParams == null) {
                mParams = new Bundle(2);
            }
            mParams.putInt((mIndex++) + "", value);
            return this;
        }

        public FunctionParamsBuilder putString(String value) {
            if (mParams == null) {
                mParams = new Bundle(2);
            }
            mParams.putString((mIndex++) + "", value);
            return this;
        }

        public FunctionParamsBuilder putBoolean(boolean value) {
            if (mParams == null) {
                mParams = new Bundle(2);
            }
            mParams.putBoolean((mIndex++) + "", value);
            return this;
        }

        public FunctionParamsBuilder putObject(Object value) {

            if (mObjectParams == null) {
                mObjectParams = new HashMap(1);
            }
            mObjectParams.put((mIndex++) + "", value);
            return this;
        }

        public FunctionParams create() {
            FunctionParams instance = new FunctionParams(mParams, mObjectParams);
            return instance;
        }
    }

}