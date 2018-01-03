package hsj.com.tab_host_fragment.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hsj.com.tab_host_fragment.R;

/**
 * Created by hsj on 2018/1/3.
 */

public class MessageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.message_layout, container, false);
        return messageLayout;
    }

}
