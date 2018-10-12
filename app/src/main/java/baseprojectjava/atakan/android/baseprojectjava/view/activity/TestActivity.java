package baseprojectjava.atakan.android.baseprojectjava.view.activity;

import android.os.Bundle;
import baseprojectjava.atakan.android.baseprojectjava.R;
import baseprojectjava.atakan.android.baseprojectjava.base.BaseActivity;
import baseprojectjava.atakan.android.baseprojectjava.databinding.ActivityTestBinding;
import baseprojectjava.atakan.android.baseprojectjava.view.fragment.TestFragment;

public class TestActivity extends BaseActivity<ActivityTestBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_test;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadFragment(binding.container.getId(),TestFragment.newInstance(),false);
    }
}
