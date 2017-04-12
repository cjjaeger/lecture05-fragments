package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";
    private SearchFragment searchFragment;
    private DetailFragment detailFragment;
    private MoviesFragment moviesFragment;
    private ViewPager mPager;
    private MoviePagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchFragment = SearchFragment.newInstance();

        mAdapter = new MoviePagerAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.viewPager);
        mPager.setAdapter(mAdapter);

    }
    public class MoviePagerAdapter extends FragmentStatePagerAdapter {
        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if(position == 0){
                fragment = searchFragment;
            }else if(position== 1){
                fragment = moviesFragment;
            }else if(position == 2){
                fragment = detailFragment;
            }else{
                fragment = null;
            }

            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            if(moviesFragment == null){
                return 1;
            } else if (detailFragment == null) {
                return 2;
            } else {
                return 3;
            }
        }
    }



    @Override
    public void onMovieSelected(Movie movie) {
        detailFragment = DetailFragment.newInstance(movie.toString(), movie.imdbId);
        mAdapter.notifyDataSetChanged();
        mPager.setCurrentItem(2);

    }

    @Override
    public void onSearchSubmitted(String searchTerm) {
        moviesFragment = MoviesFragment.newInstance(searchTerm);
        mAdapter.notifyDataSetChanged();
        mPager.setCurrentItem(1);
    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
}
