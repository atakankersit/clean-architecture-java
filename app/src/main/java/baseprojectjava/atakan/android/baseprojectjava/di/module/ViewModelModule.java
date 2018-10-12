package baseprojectjava.atakan.android.baseprojectjava.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import baseprojectjava.atakan.android.baseprojectjava.util.ViewModelFactory;
import baseprojectjava.atakan.android.baseprojectjava.view.fragment.VMTest;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VMTest.class)
    abstract ViewModel bindVMAnnouncementDetail(VMTest viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
