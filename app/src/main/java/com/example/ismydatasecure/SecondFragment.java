package com.example.ismydatasecure;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
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
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nulabinc.zxcvbn.*;

public class SecondFragment extends Fragment {

    public Fragment frag;
    public TextView result1;
    public TextView result2;
    public TextView result3;
    public TextView result4;
    private TextView strength;
    public EditText input;
    String pUrl = "https://api.pwnedpasswords.com/range/";
    String pHash;
    Button secure;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        result1 = view.findViewById(R.id.resultPw);
        result2 = view.findViewById(R.id.resultPw2);
        result3 = view.findViewById(R.id.resultPw3);
        result4 = view.findViewById(R.id.resultPw4);
        strength = view.findViewById(R.id.strength);
        secure = view.findViewById(R.id.lock);
        secure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage(Html.fromHtml("<p><strong>How does my password get searched securely?</strong></p>\n" +
                        "<p>Using <strong>K-Anonymity</strong>, your password does <strong>not&nbsp;</strong>get sent over the internet, making the search 100% safe to use.</p>\n" +
                        "<p>For a full explanation of how K-Anonymity allows for safe searching, search in the <strong>Tips section.</strong></p>\n" +
                        "<p><br></p>\n" +
                        "<p><strong>Can hackers get my password from this app?</strong></p>\n" +
                        "<p>Absolutely not. Your sensitive data does not get exposed online. This app saves no tracking or personal information.</p>"));
                builder1.setCancelable(true);
                builder1.setTitle("Secure Password Search");
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });



        input = view.findViewById(R.id.mnemonicInput);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable m) {

                //code to alter layout based on password strength/ toggles for
                pwAPI(m.toString());

                Strength s = Zxcvbn(m.toString());
                String score = "#636161";
                String str = "Strength";
                switch(s.getScore()) {
                    case 0 : score = "#000000";
                    str = "UNSAFE";
                    break;
                    case 1 : score = "#eb7d7d";
                    str = "WEAK";
                    break;
                    case 2 : score = "#e9d252";
                    str = "OK";
                    break;
                    case 3 : score = "#b4ee7c";
                    str = "GOOD";
                    break;
                    case 4 : score = "#64c95a";
                    str = "SAFE";

                }
                strength.setTextColor(Color.parseColor(score));
                strength.setText(str);
                result3.setText("Time to Crack: " + s.getCrackTimesDisplay().getOnlineNoThrottling10perSecond());
                result4.setText("Notes: " + s.getFeedback().getWarning());
                result4.append("" + s.getFeedback().getSuggestions().toString());


            }
        });
    }
    public Strength Zxcvbn(String s){
        Strength pw;
        Zxcvbn zxcvbn = new Zxcvbn();
        pw = zxcvbn.measure(s);

        return pw;
    }

    public void pwAPI(String m){
        if(m.length()>0){
            RequestQueue queue = Volley.newRequestQueue(requireContext());
            StringBuilder s = new StringBuilder();
            s.append(pUrl);
            try {
                pHash = AeSimpleSHA1.SHA1(input.getText().toString());
                //Log.d("hash = ",pHash);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            s.append(pHash.substring(0, 5));
            Log.d("site: ", s.toString());
            StringRequest passwordRequest = new StringRequest(Request.Method.GET, s.toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String responseArray[] = response.split("\\r?\\n");
                    //Log.d("Array size: ", "" + responseArray.length);
                    //Log.d("# of matches: ","" + ((MainActivity)getActivity()).passwordSearcher(responseArray,pHash));
                    int j = ((MainActivity)getActivity()).passwordSearcher(responseArray,pHash);
                    if(j != -1) {
                        result1.setText("Leaked Online: " + j);
                    }
                    else result1.setText(R.string.leakednone);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error",error.toString());
                }
            }


            );

            queue.add(passwordRequest);
        }
        else result1.setText(R.string.leakednone);
    }

}

/* TODO:
    Implement GUI improvement for password strength (hsimp jquery? custom java?)
    help button implementation for explanation of app features
    tips implementation for help on how to improve security & create stronger passwords*/