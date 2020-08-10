package com.example.ismydatasecure;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
                    return "HELP";
            }
            return null;
        }
    }


    }
