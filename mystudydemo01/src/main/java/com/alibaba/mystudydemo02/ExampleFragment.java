package com.alibaba.mystudydemo02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mystudydemo01.R;

import org.w3c.dom.Text;


public class ExampleFragment extends Fragment {

    public ExampleFragment() {
        super(R.layout.example_fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.example_fragment, container, false);

        

        // Inflate the layout for this fragment
        return view;
    }

}
