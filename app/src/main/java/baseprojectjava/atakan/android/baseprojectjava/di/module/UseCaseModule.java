package baseprojectjava.atakan.android.baseprojectjava.di.module;



import baseprojectjava.atakan.android.baseprojectjava.di.scopes.ApplicationScope;
import baseprojectjava.atakan.android.baseprojectjava.repository.TestRepository;
import baseprojectjava.atakan.android.baseprojectjava.usecase.GetTestUseCase;
import dagger.Module;
import dagger.Provides;

@ApplicationScope
@Module(includes = RepositoryModule.class)
public class UseCaseModule {

    @ApplicationScope @Provides
    public GetTestUseCase provideGetTestUseCase(TestRepository testRepository){
        return new GetTestUseCase(testRepository);
    }

   /* @ApplicationScope @Provides
    public GetMarathonCampaignUseCase provideGetMarathonCampaignUseCase(MarathonRepository repository,FileUrlConvertor fileUrlConvertor) {
        return new GetMarathonCampaignUseCase(repository,fileUrlConvertor);
    }*/
}