package com.example.ismydatasecure;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThirdFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private RecAdapter recAdapter;
    private ArrayList<Tip> tipList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tipList = new ArrayList<>();
        String tip1 = "Gmail ignores after the plus character (+) allowing the text afterward to be used for filtering and generating infinite amounts of alternate accounts which direct to the same inbox.\\n\" +\n" +
                "                \"E.g. test+spam@gmail.com, test+123@gmail.com, test+shopping@gmail.com will all deliver to the mailbox test@gmail.com \\n\" +\n" +
                "                \"Dots in the email address are also completely ignored, therefore test@gmail.com and t.e.s.t@gmail.com are the same inbox.\\n\" +\n" +
                "                \"This is also true for test@gmail.com and test@googlemail.com. It does not matter if you use Gmail or Googlemail.\\n";
        tipList.add(new Tip("Test Tip","Test Category","Gmail ignores after the plus character (+) allowing the text afterward to be used for filtering and generating infinite amounts of alternate accounts which direct to the same inbox.\n" +
                "E.g. test+spam@gmail.com, test+123@gmail.com, test+shopping@gmail.com will all deliver to the mailbox test@gmail.com \n" +
                "Dots in the email address are also completely ignored, therefore test@gmail.com and t.e.s.t@gmail.com are the same inbox.\n" +
                "This is also true for test@gmail.com and test@googlemail.com. It does not matter if you use Gmail or Googlemail.\n"));


        mRecyclerview = view.findViewById(R.id.tip_view);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));

        recAdapter = new RecAdapter(getContext(),tipList);
        mRecyclerview.setAdapter(recAdapter);


    }

}

/* TODO:
    Implement GUI improvement for password strength (hsimp jquery? custom java?)
    help button implementation for explanation of app features
    tips implementation for help on how to improve security & create stronger passwords*/