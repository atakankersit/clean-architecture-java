package baseprojectjava.atakan.android.baseprojectjava.view.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import baseprojectjava.atakan.android.baseprojectjava.R;
import baseprojectjava.atakan.android.baseprojectjava.base.BaseFragment;
import baseprojectjava.atakan.android.baseprojectjava.databinding.TestFragmentBinding;
import baseprojectjava.atakan.android.baseprojectjava.model.TestViewEntity;
import baseprojectjava.atakan.android.baseprojectjava.view.activity.TestActivity;


public class TestFragment extends BaseFragment<VMTest, TestFragmentBinding> {

    private static final String TAG = "TestFragment";
    private VMTest mViewModel;

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    protected Class<VMTest> getViewModel() {
        return VMTest.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.test_fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //viewModel.getTestViewEntity();
        setClickListeners();
        subscribe();

        return binding.getRoot();
    }

    private void setClickListeners() {
        binding.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TestActivity)getActivity()).showLoading(true);
                viewModel.getTestViewEntity();
            }
        });
    }

    private void subscribe() {

        viewModel.getData().observe(this, new Observer<TestViewEntity>() {
            @Override
            public void onChanged(@Nullable TestViewEntity testViewEntity) {
                Log.w(TAG, "Atakan " );
                Toast.makeText(TestFragment.this.getContext(), testViewEntity.getKeyEntity(), Toast.LENGTH_SHORT).show();
                ((TestActivity)getActivity()).showLoading(false);
            }
        });
    }
}
