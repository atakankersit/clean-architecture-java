package baseprojectjava.atakan.android.baseprojectjava.util;

import android.support.annotation.IntDef;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import baseprojectjava.atakan.android.baseprojectjava.base.BaseError;

public class DataHolder<T> {
    public static final int SOURCE_TYPE_REMOTE = 1;
    public static final int SOURCE_TYPE_CACHE = 2;
    public static final int SOURCE_TYPE_DATABASE = 3;
    private boolean isStarted = false;

    public DataHolder() {

    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            SOURCE_TYPE_CACHE,
            SOURCE_TYPE_DATABASE,
            SOURCE_TYPE_REMOTE
    })
    public @interface DataSourceType {}

    private BaseError error;
    private T data;
    @DataSourceType private int dataSourceType;

    public DataHolder(T data, @DataSourceType int dataSourceType) {
        this.data = data;
        this.dataSourceType = dataSourceType;
    }



    public DataHolder(BaseError error, @DataSourceType int dataSourceType) {
        this.error = error;
        this.dataSourceType = dataSourceType;
    }

    public BaseError getError() {
        return error;
    }

    public T getData() {
        return data;
    }


    public static DataHolder createErrorDataHolder(BaseError baseError, @DataSourceType int dataSourceType) {
        return new DataHolder(baseError,dataSourceType);
    }

    public static <T> DataHolder createErrorDataHolder(Throwable throwable, @DataSourceType int dataSourceType){
        BaseError baseError=new BaseError();
        baseError.setMessage(throwable.getMessage());
        return new DataHolder<T>(baseError,dataSourceType);
    }

    public static DataHolder createErrorDataHolderForRemote(BaseError baseError) {
        return new DataHolder(baseError,SOURCE_TYPE_REMOTE);
    }

    public static <T> DataHolder createSuccessDataHolder(T data, @DataSourceType int dataSourceType) {
        return new DataHolder(data,dataSourceType);
    }


    public static <T> DataHolder createSuccessDataHolderForRemote(T data) {
        return new DataHolder<T>(data,SOURCE_TYPE_REMOTE);
    }
  /*  public static <T> DataHolder createSuccessDataHolderForRemote(T data, PageInfo pageInfo) {
        return new DataHolder(data,SOURCE_TYPE_REMOTE,pageInfo);
    }*/

    public static <T> DataHolder createSuccessDataHolderForCache(T data) {
        return new DataHolder(data,SOURCE_TYPE_CACHE);
    }

    public static <T> DataHolder createStartedDataHolderForCache() {
        DataHolder dataHolder = new DataHolder(null, SOURCE_TYPE_CACHE);
        dataHolder.setStarted(true);
        return dataHolder;
    }

    public static <T> DataHolder createStartedDataHolderForRemote() {
        DataHolder dataHolder = new DataHolder(null, SOURCE_TYPE_REMOTE);
        dataHolder.setStarted(true);
        return dataHolder;
    }

    public static <T> DataHolder createStartedDataHolderForDatabase() {
        DataHolder dataHolder = new DataHolder(null,  SOURCE_TYPE_DATABASE);
        dataHolder.setStarted(true);
        return dataHolder;
    }

    private void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public boolean isSuccess() {
        return error == null;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public int getDataSourceType() {
        return dataSourceType;
    }


}
