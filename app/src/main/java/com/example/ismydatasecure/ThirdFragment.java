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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Tip> tipList = new ArrayList<>();
        tipList.add(new Tip("Email Aliasing with the + Symbol","Email, Tip","<p>Gmail &amp; Outlook ignores anything after the plus character (<strong>+</strong>) allowing the text afterwards to be used for filtering and generating infinite amounts of alternate accounts which direct to the same inbox.</p>\n" +
                "<p><em><strong>test+spam@gmail.com</strong>, <u><strong>test+123@gmail.com</strong></u></em><em>&nbsp;&amp;</em><strong>&nbsp;test+shopping@gmail.com</strong><em>&nbsp;will all be deliver to the mailbox <strong>test@gmail.com</strong></em></p>\n" +
                "<p>Dots (<strong>.</strong>) are also completely ignored, therefore <strong>test@gmail.com</strong> and<strong>&nbsp;t.e.s.t@gmail.com</strong> are also the same inbox.</p>\n" +
                "<p>Using this technique for creating different email address aliases for different accounts can lead to higher account security</p>"));


        RecyclerView mRecyclerview = view.findViewById(R.id.tip_view);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));

        RecAdapter recAdapter = new RecAdapter(getContext(), tipList);
        mRecyclerview.setAdapter(recAdapter);


    }

}

/* TODO:
    Implement GUI improvement for password strength (hsimp jquery? custom java?)
    help button implementation for explanation of app features
    tips implementation for help on how to improve security & create stronger passwords*/