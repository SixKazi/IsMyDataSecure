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
        tipList.add(new Tip("Email Aliasing with the + Symbol","<strong>Email, Tip</strong>","<p>Gmail &amp; Outlook ignores anything after the plus character (<strong>+</strong>) allowing the text afterwards to be used for filtering and generating infinite amounts of alternate accounts which direct to the same inbox.</p>\n" +
                "<p><em><strong>test+spam@gmail.com</strong>, <u><strong>test+123@gmail.com</strong></u></em><em>&nbsp;&amp;</em><strong>&nbsp;test+shopping@gmail.com</strong><em>&nbsp;will all be deliver to the mailbox <strong>test@gmail.com</strong></em></p>\n" +
                "<p>Dots (<strong>.</strong>) are also completely ignored, therefore <strong>test@gmail.com</strong> and<strong>&nbsp;t.e.s.t@gmail.com</strong> are also the same inbox.</p>\n" +
                "<p>Using this technique for creating different email address aliases for different accounts can lead to higher account security</p>"));
        tipList.add(new Tip("Two Factor Authentication","<strong>Account</strong>","<p>Two Factor Authentication (2FA) is the best way to secure your account, even if your password is compromised.</p>\n" +
                "<p><br></p>\n" +
                "<p>2FA involves using your phone to confirm that it is you trying to access the account, either using SMS code or an Authentication App.</p>\n" +
                "<p><br></p>\n" +
                "<p>If any accounts you have support 2FA, turn it on. For a brief explanation on what 2FA is <a href=\"https://www.youtube.com/watch?v=hGRii5f_uSc\" rel=\"noopener noreferrer\" target=\"_blank\">click here</a></p>"));
        tipList.add(new Tip("Password Manager","<strong>Password, Account</strong>","<p>If you do not trust yourself to remember or manage different passwords for different accounts, use a <strong>Password Manager&nbsp;</strong></p>\n" +
                "<p>The App will generate complex passwords which won&#39;t need to be remembered by you, therefore can be extremely complex (&quot;fFG3k&#39;[564%3^$&pound;gf&quot; could easily be a password. Want to try and memorise it?)</p>\n" +
                "<p>Search your App store for Password Manager and find one which suits you</p>"));
        tipList.add(new Tip("Google Password Checkup","<strong>Password, Account</strong>","<p>If you use Google Chrome as your primary browser, <a href=\"https://passwords.google.com/\" rel=\"noopener noreferrer\" target=\"_blank\">https://passwords.google.com/</a> is a site which shows all your stored passwords.</p>\n" +
                "<p>From there, you can also check if any of your saved passwords have been leaked online and how to change them.</p>"));
        tipList.add(new Tip("Password Tips","<strong>Password, Tips</strong>","<p>A good password is both complex and easy to remember for the user. Good tips for making a password include:</p>\n" +
                "<p><strong>Mnemonic:&nbsp;</strong></p>\n" +
                "<p style=\"margin-left: 20px;\">Create a sentence (My girlfriend made me sign up to this and I&#39;m not happy.)&nbsp;</p>\n" +
                "<p style=\"margin-left: 20px;\">Take the first letter of each word, include some substitutions to include special characters, upper and lower case, and numbers.&nbsp;</p>\n" +
                "<p style=\"margin-left: 20px;\">Result being a strong password (Mgfmmsu2t&amp;Inh.)</p>\n" +
                "<p><strong>Quick Texter:&nbsp;</strong></p>\n" +
                "<p style=\"margin-left: 20px;\">If you&#39;re a particularly fast texter, you don&#39;t need to shorten the mnemonic, and your password can be the entire sentence, spaces and all.&nbsp;</p>\n" +
                "<p style=\"margin-left: 20px;\">e.g. &quot;My girlfriend made me sign up 2 this and I&#39;m not happy.&quot;&nbsp;</p>"));


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