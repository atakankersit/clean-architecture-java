package baseprojectjava.atakan.android.baseprojectjava.di.module;

import baseprojectjava.atakan.android.baseprojectjava.dataSource.InMemoryTestDataSource;
import baseprojectjava.atakan.android.baseprojectjava.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@ApplicationScope
@Module
public class DataSourceModule {

    @ApplicationScope
    @Provides
    public InMemoryTestDataSource provideInMemoryTestDataSource(){
        return new InMemoryTestDataSource();
    }



}