package com.xiaoge.org.navigation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xiaoge.org.R;
import com.xiaoge.org.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class NavigationFragment2 extends Fragment implements View.OnClickListener {
    public static final String TAG = NavigationFragment2.class.getSimpleName();

    private Activity activity;

    public NavigationFragment2() {
        super();
        LogUtil.i(TAG, TAG + "#NavigationFragment2");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
        LogUtil.i(TAG, TAG + "#onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG, TAG + "#onCreateView");

        View v = inflater.inflate(R.layout.navigationfragment_layout, container, false);
        Button btn1 = v.findViewById(R.id.btn1);
        Button btn2 = v.findViewById(R.id.btn2);
        btn1.setText(this.getClass().getSimpleName() + "_btn1");
        btn2.setText(this.getClass().getSimpleName() + "_btn2");
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 获取传递过来的参数
        int test = NavigationFragment1Args.fromBundle(getArguments()).getTest();
        LogUtil.i(TAG, TAG + "#onViewCreated test: " + test);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.i(TAG, TAG + "#onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i(TAG, TAG + "#onDestroyView");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                // 返回上一个页面
                Navigation.findNavController(v).navigateUp();
                break;
            case R.id.btn2:
                Navigation.findNavController(v).navigate(R.id.action_page3);
                break;
            default:
                break;
        }
    }
}
