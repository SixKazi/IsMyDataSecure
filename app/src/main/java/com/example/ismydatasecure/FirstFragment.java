package com.example.ismydatasecure;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*
*
*
* */
public class FirstFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private WebsiteAdapter mWebsiteAdapter;
    private ArrayList<WebsiteItem> mWebsiteList;
    private RequestQueue mRequestQueue;

    Button buttonEmail;
    String url = "https://haveibeenpwned.com/api/v3/breachedaccount/";
    String key = "53350b58873742d1b58ee5dda75cc6d6";
    EditText emailAddress;
    TextView warning;
    Button help;
    Button getHelp;
    Button newSearch;
    Button share;
    Button secure;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailAddress = view.findViewById(R.id.emailInput);
        buttonEmail = view.findViewById(R.id.button_first);
        mRecyclerView = view.findViewById(R.id.result);
        warning = view.findViewById(R.id.result_warning);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        getHelp = view.findViewById(R.id.help_button);
        newSearch = view.findViewById(R.id.new_button);
        newSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebsiteList.clear();
                emailAddress.setText("");
                newSearch.setVisibility(View.GONE);
                getHelp.setVisibility(View.GONE);
                share.setVisibility(View.GONE);
                warning.setText("...");
                warning.setTextColor(Color.BLACK);
            }
        });
        secure = view.findViewById(R.id.help_button);
        secure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage(Html.fromHtml("<p><strong>Verify</strong></p>\n" +
                        "<p>&nbsp;Verify what data has been leaked, making note of any passwords which may have been used on other accounts.</p>\n" +
                        "<p><strong>Protect</strong></p>\n" +
                        "<p>Change Passwords,&nbsp;</p>\n" +
                        "<p>Enable 2 Factor Authentication,&nbsp;</p>\n" +
                        "<p>If necessary, contact your bank to block bank account access</p>\n" +
                        "<p><strong>Monitor</strong></p>\n" +
                        "<p>Keep an eye for suspicious activity on your accounts</p>\n" +
                        "<p>Check emails regularly for any login attempts not from yourself</p>\n" +
                        "<p>Sign up to breach notification services for future leaks</p>\n" +
                        "<p><br></p>"));
                builder1.setCancelable(true);
                builder1.setTitle("ACT NOW");
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        share = view.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Uh oh. I searched " + emailAddress.getText().toString() + " using the 'Is My Data Secure?' app and found that it's been leaked " + mWebsiteList.size() + " times! Time to update passwords. Download the App to search for emails and passwords, and also tips to make you more secure!";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Email Search");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        mWebsiteList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this.getContext());

        emailAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)|| (actionId == EditorInfo.IME_ACTION_NEXT)) {
                    buttonEmail.callOnClick();
                }
                return false;
            }
        });

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).hideKeyboard();
                if(!isValidEmail(emailAddress.getText().toString())){
                    Toast toast = Toast.makeText(getContext(), "Please enter a valid E-mail", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP,0,0);
                    toast.show();
                }
                else {
                    parseJSON();
            }
        }});
    }

    // Main logic to query Have I Been Pwned API and display results

    public void parseJSON(){
        StringBuilder s = new StringBuilder();
        mWebsiteList.clear();
        getHelp.setVisibility(View.GONE);
        s.append(url);
        s.append(emailAddress.getText().toString());
        s.append("?truncateResponse=false");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, s.toString(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                     mWebsiteList.clear();                                                                                   //code to display to user if pwned or not (visual cues, check if 404 (no entry)
                    for(int i=0;i<response.length();i++){
                        JSONObject website = response.getJSONObject(i);
                        String websiteTitle = website.getString("Title");
                        int pwnCount = website.getInt("PwnCount");
                        String imageUrl = website.getString("LogoPath");
                        String date = website.getString("BreachDate");
                        String text = website.getString("Description");

                        mWebsiteList.add(new WebsiteItem(imageUrl,websiteTitle,pwnCount,date,text));

                    }
                    mWebsiteAdapter = new WebsiteAdapter(getContext(), mWebsiteList);
                    warning.setText(mWebsiteList.size() + " matches found. \n Act now to secure your data");
                    warning.setTextColor(Color.RED);
                    getHelp.setVisibility(View.VISIBLE);
                    newSearch.setVisibility(View.VISIBLE);
                    share.setVisibility(View.VISIBLE);
                    mRecyclerView.setAdapter(mWebsiteAdapter);
                    mWebsiteAdapter.setOnItemClickListener(new WebsiteAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            boolean expanded = mWebsiteList.get(position).isExpanded();
                            mWebsiteList.get(position).setExpanded(!expanded);
                            mWebsiteAdapter.notifyItemChanged(position);

                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if(error.networkResponse.statusCode == 404) {
                    warning.setText("No results found!");
                    warning.setTextColor(Color.BLACK);
                }
            }
        }){


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                String headerName = "hibp-api-key";
                params.put(headerName, key);

                return params;
            }
        };

        mRequestQueue.add(request);
    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    }
