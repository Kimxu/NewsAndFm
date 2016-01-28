package kimxu.mvp.communication;

import java.util.HashMap;

/**
 * Created by kimxu on 16/1/28.
 * Activity 与 Fragment 进行数据传递，
 * 一般是使用接口的方式，但是如果项目过大，
 * 就会造成接口过多，所以使用该方式。
 * <p>
 * 本类是各种方法集合的类，可以把一个方法类
 * 以key-value的形式放入本类， 可以通过key
 * 值来调用相应的方法
 */

public class Functions {
    //带参数的方法集合，key值为方法的名字
    private HashMap<String, FunctionWithParam> mFunctionWithParam;
    //不带参数和返回值的方法集合，key值为方法的名字
    private HashMap<String, FunctionNoParamAndResult> mFunctionNoParamAndResult;
    //带返回值的方法集合，key值为方法的名字
    private HashMap<String, FunctionWithResult> mFunctionWithResult;
    //带参数和返回值的方法集合，key值为方法的名字
    private HashMap<String, FunctionWithParamAndResult> mFunctionWithParamAndResult;

    //基础方法类
    public abstract static class Function {
        //方法名字
        public String mFunctionName;

        public Function(String mFunctionName) {
            this.mFunctionName = mFunctionName;
        }
    }

    //带有参数没有返回值的方法
    public abstract static class FunctionWithParam<Param> extends Function {


        public FunctionWithParam(String mFunctionName) {
            super(mFunctionName);
        }

        public void function(Param param) {

        }
    }

    //无参数和返回值的方法
    public abstract static class FunctionNoParamAndResult extends Function {

        public FunctionNoParamAndResult(String mFunctionName) {
            super(mFunctionName);
        }

        public abstract void function();
    }

    // 有返回值，没有参数的方法
    public static abstract class FunctionWithResult<Result> extends Function {

        public FunctionWithResult(String functionName) {
            super(functionName);
        }

        public abstract Result function();
    }

    // 带有参数和返回值的 方法
    public static abstract class FunctionWithParamAndResult<Result, Param> extends Function {
        public FunctionWithParamAndResult(String functionName) {
            super(functionName);
        }

        public abstract Result function(Param data);
    }

    //添加带参数的方法
    public Functions addFunction(FunctionWithParam function) {
        if (function == null) {
            return this;
        }
        if (mFunctionWithParam == null) {
            mFunctionWithParam = new HashMap<>(1);
        }

        mFunctionWithParam.put(function.mFunctionName, function);
        return this;
    }

    // 添加无参数无返回值的方法
    public Functions addFunction(FunctionNoParamAndResult function) {
        if (function == null) {
            return this;
        }
        if (mFunctionNoParamAndResult == null) {
            mFunctionNoParamAndResult = new HashMap<>(1);
        }
        mFunctionNoParamAndResult.put(function.mFunctionName, function);
        return this;
    }
    //添加有返回值的方法
    public Functions addFunction(FunctionWithResult function) {
        if (function == null) {
            return this;
        }
        if (mFunctionWithResult == null) {
            mFunctionWithResult = new HashMap<>(1);
        }
        mFunctionWithResult.put(function.mFunctionName, function);
        return this;
    }
    //添加有参数和返回值的方法
    public Functions addFunction(FunctionWithParamAndResult function) {
        if (function == null) {
            return this;
        }
        if (mFunctionWithParamAndResult == null) {
            mFunctionWithParamAndResult = new HashMap<>(1);
        }
        mFunctionWithParamAndResult.put(function.mFunctionName, function);
        return this;
    }

    // 调用具无参数和返回值的函数
    public void invokeFunc(String funcName) throws FunctionException {
        FunctionNoParamAndResult f = null;
        if (mFunctionNoParamAndResult != null) {
            f = mFunctionNoParamAndResult.get(funcName);
            if (f != null) {
                f.function();
            }
        }
        if (f == null) {
            throw new FunctionException("没有此函数");
        }
    }

    // 调用具有参数的函数
    public <Param> void invokeFunc(String funcName, Param param) throws FunctionException {
        FunctionWithParam f = null;
        if (mFunctionWithParam != null) {
            f = mFunctionWithParam.get(funcName);
            if (f != null) {
                f.function(param);
            }
        }
        if (f == null) {
            throw new FunctionException("没有此函数");
        }
    }
    // 调用具无参数和返回值的函数
    public <Result> Result invokeFuncWithResult(String funcName, Class<Result> c) throws FunctionException {
        FunctionWithResult f = null;
        if(mFunctionWithResult != null){
            f = mFunctionWithResult.get(funcName);
            if(f != null){
                if(c != null){
                    return c.cast(f.function());
                }else{
                    return (Result)f.function();
                }

            }
        }

        if(f == null){
            throw new FunctionException("没有此函数");
        }
        return null;
    }
    // 调用具有参数和返回值的函数
    public <Result,Param> Result invokeFunc(String funcName,Param param,Class<Result> c) throws FunctionException {
        FunctionWithParamAndResult f = null;
        if(mFunctionWithParamAndResult != null){
            f = mFunctionWithParamAndResult.get(funcName);
            if(f != null){
                if(c != null){
                    return c.cast(f.function(param));
                }else{
                    return (Result)f.function(param);
                }
            }
        }

        if( f == null){
            throw new FunctionException("没有此函数");

        }
        return null;
    }
    public class FunctionException extends Exception{
        public FunctionException(String detailMessage) {
            super(detailMessage);
        }
    }
}
