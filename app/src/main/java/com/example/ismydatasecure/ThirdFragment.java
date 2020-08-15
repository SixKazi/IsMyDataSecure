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
import androidx.appcompat.widget.SearchView;
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
public SearchView search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ArrayList<Tip> tipList = new ArrayList<>();

        search = view.findViewById(R.id.search_bar);


        tipList.add(new Tip("Email Aliasing with the + Symbol","<strong>Email, Tips</strong>","<p>Gmail &amp; Outlook ignores anything after the plus character (<strong>+</strong>) allowing the text afterwards to be used for filtering and generating infinite amounts of alternate accounts which direct to the same inbox.</p>\n" +
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
        tipList.add(new Tip("How Password Search Works","<strong>Password, Account, FAQ</strong>","<p><strong>How is my password safely being searched?</strong></p>\n" +
                "<p>Your password is <strong>NOT </strong>being sent over the internet, but using k-Anonymity. This is how it works:</p>\n" +
                "<p>First you input your password locally, for this example &ldquo;<strong>P@ssw0rd</strong>&rdquo;.</p>\n" +
                "<p>This app uses SHA-1 to hash the password into &quot;<strong>21BD12DC183F740EE76F27B78EB39C8AD972A757</strong>&quot;</p>\n" +
                "<p>The first five characters of the hashed password (<strong>21BD1</strong>) is the only information sent over the internet</p>\n" +
                "<p>The Pwned Passwords API then checks 21BD1, and returns the suffixes for all hashes which start with 21DB1, which would be, say, <strong>475 </strong>hits.</p>\n" +
                "<p>1.<span style=\"white-space:pre;\">&nbsp; &nbsp;&nbsp;</span>(21BD1) 0018A45C4D1DEF81644B54AB7F969B88D65:1 (password &quot;lauragpe&quot;)</p>\n" +
                "<p>2.<span style=\"white-space:pre;\">&nbsp; &nbsp;&nbsp;</span>(21BD1) 00D4F6E8FA6EECAD2A3AA415EEC418D38EC:2 (password &quot;alexguo029&quot;)</p>\n" +
                "<p>3.<span style=\"white-space:pre;\">&nbsp; &nbsp;&nbsp;</span>(21BD1) 011053FD0102E94D6AE2F8B83D76FAF94F6:1 (password &quot;BDnd9102&quot;)</p>\n" +
                "<p>4.<span style=\"white-space:pre;\">&nbsp; &nbsp;&nbsp;</span>(21BD1) 012A7CA357541F0AC487871FEEC1891C49C:2 (password &quot;melobie&quot;)</p>\n" +
                "<p>5.<span style=\"white-space:pre;\">&nbsp; &nbsp;&nbsp;</span>(21BD1) 0136E006E24E7D152139815FB0FC6A50B15:2 (password &quot;quvekyny&quot;)</p>\n" +
                "<p>6.<span style=\"white-space:pre;\">&nbsp; &nbsp;&nbsp;</span>...</p>\n" +
                "<p>With this information passed back, we can locally check the data to see whether any of the returned suffixes, combined again with 21BD1 as the prefix, matches the same hash as with &ldquo;P@ssw0rd&rdquo;. If we get a match, then it has been found online, and the app displays how many times its been shown in leaks online.</p>\n" +
                "<p><br></p>"));

        RecyclerView mRecyclerview = view.findViewById(R.id.tip_view);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));

        final RecAdapter recAdapter = new RecAdapter(getContext(), tipList);
        mRecyclerview.setAdapter(recAdapter);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recAdapter.getFilter().filter(newText);

                return false;
            }
        });
        recAdapter.setOnItemClickListener(new RecAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ((MainActivity)getActivity()).hideKeyboard();

                boolean expanded = tipList.get(position).isExpanded();
                tipList.get(position).setExpanded(!expanded);
                recAdapter.notifyItemChanged(position);

            }
        });


    }

}

/* TODO:
    Implement GUI improvement for password strength (hsimp jquery? custom java?)
    help button implementation for explanation of app features
    tips implementation for help on how to improve security & create stronger passwords*/