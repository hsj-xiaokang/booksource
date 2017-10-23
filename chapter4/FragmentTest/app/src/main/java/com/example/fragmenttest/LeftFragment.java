package com.example.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * android.support.v4.app.Fragment;
 * 使用的包的碎片类
 */
public class LeftFragment extends Fragment {

    private static final String TAG = "LeftFragment";

    /**
     * 创建view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * 碎片里面获取关联的activity
         */
        MainActivity ma = (MainActivity) getActivity();
        Toast.makeText(ma,ma.toString(),Toast.LENGTH_SHORT).show();
        RightFragment rf = (RightFragment) ma.getSupportFragmentManager().findFragmentById(R.id.right_fragment);
        Toast.makeText(ma,"leftFragment 获取 rightFragment "+rf.toString(),Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onActivityCreated");
    }

}
