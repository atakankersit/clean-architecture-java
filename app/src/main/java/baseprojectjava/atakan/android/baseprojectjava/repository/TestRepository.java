package baseprojectjava.atakan.android.baseprojectjava.repository;

import java.util.concurrent.TimeUnit;

import baseprojectjava.atakan.android.baseprojectjava.dataSource.InMemoryTestDataSource;
import baseprojectjava.atakan.android.baseprojectjava.model.CustomTempModel;
import baseprojectjava.atakan.android.baseprojectjava.network.AbstractApiUtils;
import baseprojectjava.atakan.android.baseprojectjava.util.DataHolder;
import io.reactivex.Observable;

public class TestRepository {

    private AbstractApiUtils apiUtils;
    private InMemoryTestDataSource inMemoryTestDataSource;

    public TestRepository(AbstractApiUtils apiUtils, InMemoryTestDataSource inMemoryTestDataSource) {
        this.apiUtils = apiUtils;
        this.inMemoryTestDataSource = inMemoryTestDataSource;
    }

    public Observable<DataHolder<CustomTempModel>> getCustomTemps() {

        // TODO: 11.10.2018 Bu neden olmadi ya?
          return inMemoryTestDataSource.getTestViewEntity()
                .concatWith(apiUtils.getCustomTempData())
                .doOnNext(
                        dataHolder ->
                                inMemoryTestDataSource.saveTestEntity(dataHolder.getData())
                        );
    }
}
