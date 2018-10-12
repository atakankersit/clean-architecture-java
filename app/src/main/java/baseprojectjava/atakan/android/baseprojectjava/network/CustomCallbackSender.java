package baseprojectjava.atakan.android.baseprojectjava.network;

import com.google.gson.Gson;


import baseprojectjava.atakan.android.baseprojectjava.base.BaseError;
import baseprojectjava.atakan.android.baseprojectjava.util.DataHolder;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class CustomCallbackSender {

    public <R> Observable<DataHolder<R>>  sendRequest(final Observable<R> call) {
        return call
                .doOnError(throwable -> throwable.printStackTrace()) //// FIXME: 11.01.2018 Görelim diye, sonra sileceğiz
                .map((Function<R, DataHolder<R>>) r -> DataHolder.createSuccessDataHolderForRemote(r))
                .onErrorReturn(throwable-> {
                    if (throwable instanceof com.jakewharton.retrofit2.adapter.rxjava2.HttpException) {
                        String errorJsonString = ((com.jakewharton.retrofit2.adapter.rxjava2.HttpException) throwable).response().errorBody().string();
                        BaseError baseError = new Gson().fromJson(errorJsonString, BaseError.class);
                        return DataHolder.createErrorDataHolder(baseError,DataHolder.SOURCE_TYPE_REMOTE);
                    }
                    return DataHolder.createErrorDataHolder(throwable,DataHolder.SOURCE_TYPE_REMOTE);
                }
                );
    }
}