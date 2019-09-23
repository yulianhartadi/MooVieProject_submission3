package com.rdstudio.moovieproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initContent();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_logo);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    // Toolbar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //Search
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }

    // Menu more action
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.choose_language) {
            // Choose Language menu
            //showOptionLanguage();
            //Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            //startActivity(mIntent);
            Toast.makeText(this, "language dialog", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    // Content
    private void initContent() {
        // Tabs
        ViewPager viewPager = findViewById(R.id.main_view_pager);
        TabLayout tabLayout = findViewById(R.id.main_tab_layout);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_local_movies);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_live_tv);

        // Set icon color pre-selected
        Objects.requireNonNull(Objects.requireNonNull(tabLayout.getTabAt(0)).getIcon()).setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        Objects.requireNonNull(Objects.requireNonNull(tabLayout.getTabAt(1)).getIcon()).setColorFilter(getResources().getColor(R.color.cyan_800), PorterDuff.Mode.SRC_IN);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //getSupportActionBar().setTitle(viewPagerAdapter.getTitle(tab.getPosition()));
                Objects.requireNonNull(tab.getIcon()).setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.cyan_800), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(@NonNull FragmentManager manager) {
            super(Objects.requireNonNull(manager));
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // switch tab for each fragment
            switch (position) {
                case 0:
                    return new MoviesFragment();
                case 1:
                    return new TVShowFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            //String title = getItem(position).getClass().getName();
            //return title.subSequence(title.lastIndexOf(".") + 1, title.length());
            return null;
        }


    }

}
