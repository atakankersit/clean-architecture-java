package baseprojectjava.atakan.android.baseprojectjava.view.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import baseprojectjava.atakan.android.baseprojectjava.base.BaseViewModel;
import baseprojectjava.atakan.android.baseprojectjava.model.TestViewEntity;
import baseprojectjava.atakan.android.baseprojectjava.usecase.GetTestUseCase;
import baseprojectjava.atakan.android.baseprojectjava.util.DataHolder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class VMTest extends BaseViewModel {
    private GetTestUseCase getTestUseCase;
    private MutableLiveData<TestViewEntity> data = new MutableLiveData<>();

    @Inject
    public VMTest(GetTestUseCase getTestUseCase) {
        this.getTestUseCase = getTestUseCase;
    }

    void getTestViewEntity(){
        Disposable disposable = getTestUseCase.getTestEntity().subscribe(new Consumer<DataHolder<TestViewEntity>>() {
            @Override
            public void accept(DataHolder<TestViewEntity> testViewEntityDataHolder) throws Exception {
                if (testViewEntityDataHolder.isSuccess()){
                    data.postValue(testViewEntityDataHolder.getData());
                }
            }
        });
    };

    public MutableLiveData<TestViewEntity> getData() {
        return data;
    }
}
