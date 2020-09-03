package com.example.ismydatasecure;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    public FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        final Context context = this;
        tabs.setupWithViewPager(viewPager);
        fab = findViewById(R.id.fab);


        //Floating action bar - interaction changes depending on which tab user is on
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() == 0){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage(Html.fromHtml("Search an email in data breaches leaked online"));
                    builder1.setCancelable(true);
                    builder1.setIcon(R.drawable.ic_email_foreground);
                    builder1.setTitle("Searching your Email");
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else if(viewPager.getCurrentItem() == 1){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage(Html.fromHtml("<p>Start typing a password to have real time statistics about its security.</p>\n" +
                            "<p>Leaked Online: Number of times the password has been seen via Pwned Passwords data breach subset. If no matches could be found, it does <strong>not&nbsp;</strong>mean your password is safe, especially if its strength is poor.</p>\n" +
                            "<p>Time to Crack: Estimated time the password would take to crack for password cracking software. This is not 100% accurate and only should be used for making sure your passwords are complex enough.</p>\n" +
                            "<p>Suggestions/Issues: Any glaring issues with the password will be shown, as in pattern matching, common password usages and mild suggestions to improve. Please use the &#39;Tips&#39; section to learn about how to create strong passwords.</p>\n" +
                            "<p>Data leak matching provided by Pwned Passwords API</p>\n" +
                            "<p><br></p>"));
                    builder1.setCancelable(true);
                    builder1.setIcon(R.drawable.ic_password_foreground);
                    builder1.setTitle("Searching your Password");
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Search your email in data breaches leaked online");
                    builder1.setCancelable(true);
                    builder1.setTitle("Searching your Email");
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Method used to search user password input into retrieved response array, outputs -1 if not found, else amount of hits in the array
    public int passwordSearcher(String[] list, String hash) {
        String prefix = hash.substring(0,5);
        int i = -1;
        for (String fullHash : list) {
            String checkHash = prefix + fullHash.substring(0, fullHash.indexOf(":"));
            //Log.d("passArray","pos "+j+" = "+checkHash);
            if (checkHash.equalsIgnoreCase(hash)) {
                //Log.d("Match!", "" + Integer.parseInt(fullHash.substring(fullHash.indexOf(":") + 1)));
                return Integer.parseInt(fullHash.substring(fullHash.indexOf(":") + 1));
            }
        }
        return i;
        }

        //Tab Navigation manager
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new FirstFragment();
                    break;
                case 1:
                    fragment = new SecondFragment();
                    break;
                case 2:
                    fragment = new ThirdFragment();
                    break;
            }
            assert fragment != null;
            return fragment;
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "EMAIL";
                case 1:
                    return "PASSWORD";
                case 2:
                    return "TIPS";
            }
            return null;
        }
    }

    //Hide virtual keyboard logic, used for when user enters email input etc
    public void hideKeyboard() {
        // Check if no view has focus:
        View view = MainActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //Generates tips based on hard coded input, generates Tip class Objects
    public ArrayList genTips() {
        ArrayList<Tip> tipList = new ArrayList<>();
        tipList.add(new Tip("Email Aliasing with the + Symbol", "<strong>Email, Tips</strong>", "<p>Gmail &amp; Outlook ignores anything after the plus character (<strong>+</strong>) allowing the text afterwards to be used for filtering and generating infinite amounts of alternate accounts which direct to the same inbox.</p>\n" +
                "<p><em><strong>test+spam@gmail.com</strong>, <u><strong>test+123@gmail.com</strong></u></em><em>&nbsp;&amp;</em><strong>&nbsp;test+shopping@gmail.com</strong><em>&nbsp;will all be deliver to the mailbox <strong>test@gmail.com</strong></em></p>\n" +
                "<p>Dots (<strong>.</strong>) are also completely ignored, therefore <strong>test@gmail.com</strong> and<strong>&nbsp;t.e.s.t@gmail.com</strong> are also the same inbox.</p>\n" +
                "<p>Using this technique for creating different email address aliases for different accounts can lead to higher account security</p>"));
        tipList.add(new Tip("Two Factor Authentication", "<strong>Account</strong>", "<p>Two Factor Authentication (2FA) is the best way to secure your account, even if your password is compromised.</p>\n" +
                "<p><br></p>\n" +
                "<p>2FA involves using your phone to confirm that it is you trying to access the account, either using SMS code or an Authentication App.</p>\n" +
                "<p><br></p>\n" +
                "<p>If any accounts you have support 2FA, turn it on. For a brief explanation on what 2FA is <a href=\"https://www.youtube.com/watch?v=hGRii5f_uSc\" rel=\"noopener noreferrer\" target=\"_blank\">click here</a></p>"));
        tipList.add(new Tip("Password Manager", "<strong>Password, Account</strong>", "<p>If you do not trust yourself to remember or manage different passwords for different accounts, use a <strong>Password Manager&nbsp;</strong></p>\n" +
                "<p>The App will generate complex passwords which won&#39;t need to be remembered by you, therefore can be extremely complex (&quot;fFG3k&#39;[564%3^$&pound;gf&quot; could easily be a password. Want to try and memorise it?)</p>\n" +
                "<p>Search your App store for Password Manager and find one which suits you</p>"));
        tipList.add(new Tip("Google Password Checkup", "<strong>Password, Account</strong>", "<p>If you use Google Chrome as your primary browser, <a href=\"https://passwords.google.com/\" rel=\"noopener noreferrer\" target=\"_blank\">https://passwords.google.com/</a> is a site which shows all your stored passwords.</p>\n" +
                "<p>From there, you can also check if any of your saved passwords have been leaked online and how to change them.</p>"));
        tipList.add(new Tip("Password Tips", "<strong>Password, Tips</strong>", "<p>A good password is both complex and easy to remember for the user. Good tips for making a password include:</p>\n" +
                "<p><strong>Mnemonic:&nbsp;</strong></p>\n" +
                "<p style=\"margin-left: 20px;\">Create a sentence (My girlfriend made me sign up to this and I&#39;m not happy.)&nbsp;</p>\n" +
                "<p style=\"margin-left: 20px;\">Take the first letter of each word, include some substitutions to include special characters, upper and lower case, and numbers.&nbsp;</p>\n" +
                "<p style=\"margin-left: 20px;\">Result being a strong password (Mgfmmsu2t&amp;Inh.)</p>\n" +
                "<p><strong>Quick Texter:&nbsp;</strong></p>\n" +
                "<p style=\"margin-left: 20px;\">If you&#39;re a particularly fast texter, you don&#39;t need to shorten the mnemonic, and your password can be the entire sentence, spaces and all.&nbsp;</p>\n" +
                "<p style=\"margin-left: 20px;\">e.g. &quot;My girlfriend made me sign up 2 this and I&#39;m not happy.&quot;&nbsp;</p>"));
        tipList.add(new Tip("How Password Search Works", "<strong>Password, Account, FAQ</strong>", "<p><strong>How is my password safely being searched?</strong></p>\n" +
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
        tipList.add(new Tip("What to do: Password Breach", "<strong>Password, Account, FAQ</strong>", "<p><strong>Verify</strong></p>\n" +
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
        tipList.add(new Tip("Password Search Help", "<strong>Password, FAQ, Tips</strong>", "<p>Secure Password Search</p>\n" +
                "<p>Start typing a password to have real time statistics about its security.</p>\n" +
                "<p>Leaked Online: Number of times the password has been seen via Pwned Passwords data breach subset. If no matches could be found, it does <strong>not&nbsp;</strong>mean your password is safe, especially if its strength is poor.</p>\n" +
                "<p>Time to Crack: Estimated time the password would take to crack for password cracking software. This is not 100% accurate and only should be used for making sure your passwords are complex enough.</p>\n" +
                "<p>Suggestions/Issues: Any glaring issues with the password will be shown, as in pattern matching, common password usages and mild suggestions to improve. Please use the &#39;Tips&#39; section to learn about how to create strong passwords.</p>\n" +
                "<p>Data leak matching provided by Pwned Passwords API</p>\n" +
                "<p><br></p>"));

        //Sorting ability for future development
        Collections.sort(tipList, new Comparator<Tip>() {
            @Override
            public int compare(Tip o1, Tip o2) {
                return o1.getCategory().compareToIgnoreCase(o2.getCategory());
            }
        });
        return tipList;
    }

}
