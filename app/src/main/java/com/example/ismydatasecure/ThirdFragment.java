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

public class ThirdFragment extends Fragment {

    public TextView result;
    public EditText input;
    String pUrl = "https://api.pwnedpasswords.com/range/";
    String pHash;


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
        result = view.findViewById(R.id.resultPw);


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

            }
        });






    }
    public String passwordGen(String s){
        String pw = "";
        String[] firstLetter = s.split(" ");
        for (int i=0;i<firstLetter.length;i++){
             pw += firstLetter[i];
        }
        pw.toLowerCase();

        for(int i=0;i<pw.length();i++){


            pw.indexOf(i);
        }


        return pw;
    }

    public boolean password_Validation(String password)
    {
        {
            Pattern letter = Pattern.compile("[a-zA-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            Matcher hasLetter = letter.matcher(password);
            Matcher hasDigit = digit.matcher(password);
            Matcher hasSpecial = special.matcher(password);
            return hasLetter.find() && hasDigit.find() && hasSpecial.find();
        }

    }

    public void pwAPI(String m){
        if(m.length() > 6 ){
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
                        result.setText("" + j);
                        result.setTextColor(Color.RED);
                    }
                    else {
                        result.setText("No Match!");
                        result.setTextColor(Color.GREEN);
                    }
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
    }

}

/* TODO:
    Implement GUI improvement for password strength (hsimp jquery? custom java?)
    help button implementation for explanation of app features
    tips implementation for help on how to improve security & create stronger passwords*/