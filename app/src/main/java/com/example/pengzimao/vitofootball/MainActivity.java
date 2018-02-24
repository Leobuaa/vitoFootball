package com.example.pengzimao.vitofootball;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.pengzimao.vitofootball.vito.fragments.VitoRecycleViewFragment;
import com.example.pengzimao.vitofootball.vito.fragments.VitoUserProfileFragment;
import com.example.pengzimao.vitofootball.vito.fragments.VitoVideoFragment;

import java.util.concurrent.ConcurrentHashMap;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigation;
    private int mCurrentFragmentID;
    private ConcurrentHashMap<Integer, Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        mBottomNavigation = findViewById(R.id.bottomNavigationView);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFragments = new ConcurrentHashMap<Integer, Fragment>();

        Fragment fragment = getFragmentByID(R.id.news);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentConstraintLayout, fragment);
        transaction.commit();

        setCurrentFragmentID(R.id.news);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() != mCurrentFragmentID) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contentConstraintLayout, getFragmentByID(item.getItemId())).commit();
                setCurrentFragmentID(item.getItemId());
            }
            return true;
        }
    };

    private void setCurrentFragmentID(int id) {
        mCurrentFragmentID = id;
    }

    private Fragment getFragmentByID(int id) {
        if (mFragments.get(id) == null) {
            switch (id) {
                case R.id.news:
                    mFragments.put(R.id.news, new VitoRecycleViewFragment());
                    break;
                case R.id.video:
                    mFragments.put(R.id.video, new VitoVideoFragment());
                    break;
                case R.id.user:
                    mFragments.put(R.id.user, new VitoUserProfileFragment());
                    break;
            }
        }
        return mFragments.get(id);
    }
}
