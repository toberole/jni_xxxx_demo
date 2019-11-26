package com.xiaoge.org.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoge.org.R;
import com.xiaoge.org.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    public static final String TAG = Fragment1.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.i(TAG, "Fragment1#onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i(TAG, "Fragment1#onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i(TAG, "Fragment1#onStop");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG, "Fragment1#onCreate");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(TAG, "Fragment1#onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG, "Fragment1#onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "Fragment1#onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.i(TAG, "Fragment1#onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i(TAG, "Fragment1#onDestroyView");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG, "Fragment1#onCreateView");
        View v = inflater.inflate(R.layout.fragment1, container, false);
        return v;
    }
}
