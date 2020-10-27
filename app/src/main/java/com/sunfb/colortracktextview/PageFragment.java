package com.sunfb.colortracktextview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {
    private View view;

    public static PageFragment getInstance(String item){
        PageFragment pageFragment=new PageFragment();
        Bundle bundle =new Bundle();
        bundle.putString("item",item);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_item,null);
        TextView title=view.findViewById(R.id.tv_fragment);
        String item= getArguments().getString("item");
        title.setText(item);
        return view;


    }
}
