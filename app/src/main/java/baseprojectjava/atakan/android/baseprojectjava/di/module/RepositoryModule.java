package baseprojectjava.atakan.android.baseprojectjava.di.module;



import baseprojectjava.atakan.android.baseprojectjava.dataSource.InMemoryTestDataSource;
import baseprojectjava.atakan.android.baseprojectjava.di.scopes.ApplicationScope;
import baseprojectjava.atakan.android.baseprojectjava.network.AbstractApiUtils;
import baseprojectjava.atakan.android.baseprojectjava.repository.TestRepository;
import dagger.Module;
import dagger.Provides;

@ApplicationScope
@Module(includes = {ApiUtilsModule.class,DataSourceModule.class})
public class RepositoryModule {

    @ApplicationScope
    @Provides
    public TestRepository provideTestRepository(AbstractApiUtils apiUtils, InMemoryTestDataSource inMemoryDataSource){
        return new TestRepository(apiUtils, inMemoryDataSource);
    }

}