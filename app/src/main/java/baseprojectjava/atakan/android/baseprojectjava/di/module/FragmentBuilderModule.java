package baseprojectjava.atakan.android.baseprojectjava.di.module;

import baseprojectjava.atakan.android.baseprojectjava.view.fragment.TestFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
    
    @ContributesAndroidInjector
    abstract TestFragment testFragment();

}
