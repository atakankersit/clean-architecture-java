package baseprojectjava.atakan.android.baseprojectjava.dataSource;

import baseprojectjava.atakan.android.baseprojectjava.model.CustomTempModel;
import baseprojectjava.atakan.android.baseprojectjava.model.TestViewEntity;
import baseprojectjava.atakan.android.baseprojectjava.util.DataHolder;

import io.reactivex.Observable;

public class InMemoryTestDataSource {
    private CustomTempModel customTempModel;


    public Observable<DataHolder<CustomTempModel>> getTestViewEntity(){
        return customTempModel == null
                ? Observable.empty()
                : Observable.<DataHolder<TestViewEntity>>just(DataHolder.createSuccessDataHolderForCache(customTempModel));
    }



    public void saveTestEntity(CustomTempModel entity) {
        this.customTempModel = entity;
    }
}
