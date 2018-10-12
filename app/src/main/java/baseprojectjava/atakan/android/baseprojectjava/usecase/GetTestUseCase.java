package baseprojectjava.atakan.android.baseprojectjava.usecase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import baseprojectjava.atakan.android.baseprojectjava.model.CustomTempModel;
import baseprojectjava.atakan.android.baseprojectjava.model.TestViewEntity;
import baseprojectjava.atakan.android.baseprojectjava.repository.TestRepository;
import baseprojectjava.atakan.android.baseprojectjava.util.DataHolder;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class GetTestUseCase {
    private TestRepository testRepository;

    @Inject
    public GetTestUseCase(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Observable<DataHolder<TestViewEntity>> getTestEntity(){

        return mapEntityToViewEntity(testRepository.getCustomTemps());
    }

    private Observable<DataHolder<TestViewEntity>> mapEntityToViewEntity(Observable<DataHolder<CustomTempModel>> customTemps) {

        return customTemps.map(
                (Function<DataHolder<CustomTempModel>, DataHolder<TestViewEntity>>) dataholder -> {
                    if (dataholder.isSuccess()) {
                        TestViewEntity testViewEntity = new TestViewEntity();
                        testViewEntity.setKeyEntity(dataholder.getData().getKey());
                        testViewEntity.setOneEntity(dataholder.getData().getOne());
                        return DataHolder.createSuccessDataHolderForRemote(testViewEntity);
                    }
                    return DataHolder.createErrorDataHolderForRemote(dataholder.getError());
                });
    }


}
