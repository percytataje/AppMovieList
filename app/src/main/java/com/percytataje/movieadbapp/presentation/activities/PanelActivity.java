package com.percytataje.movieadbapp.presentation.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.percytataje.movieadbapp.R;
import com.percytataje.movieadbapp.core.BaseActivity;
import com.percytataje.movieadbapp.presentation.fragments.PersonsFragment;
import com.percytataje.movieadbapp.presentation.fragments.MoviesFragment;
import com.percytataje.movieadbapp.presentation.fragments.SeriesFragment;
import com.percytataje.movieadbapp.utils.ActivityUtils;


public class PanelActivity extends BaseActivity {

    private DrawerLayout mDrawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Peliculas");

        MoviesFragment fragment = (MoviesFragment) getSupportFragmentManager()
                .findFragmentById(R.id.layout_content_frame);

        if (fragment == null) {
            fragment = MoviesFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.layout_content_frame);
        }


        /**
         * Setup click events on the Navigation View Items.
         */

        setupDrawerContent(navigationView);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                    /* host Activity */
                mDrawer,                    /* DrawerLayout object */
                toolbar,
                R.string.app_name,  /* "open drawer" description for accessibility */
                R.string.app_name  /* "close drawer" description for accessibility */
        );
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        menuItem.setChecked(true);
                        menuItem.setCheckable(true);
                        Fragment fragment = null;
                        boolean FragmentTransaction = false;

                        switch (menuItem.getItemId()) {
                            case R.id.movie:
                                fragment = MoviesFragment.newInstance();
                                FragmentTransaction= true;
                                break;
                            case R.id.series:
                                fragment = SeriesFragment.newInstance();
                                FragmentTransaction= true;

                                break;
                            case R.id.actors:
                                fragment = PersonsFragment.newInstance();
                                FragmentTransaction= true;

                                break;


                            default:

                                break;
                        }
                        if(FragmentTransaction){
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.layout_content_frame,fragment)
                                    .commit();
                        }

                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;


                      //  mDrawer.closeDrawers();

                    }

                });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onBackPressed() {
        if (this.mDrawer.isDrawerOpen(GravityCompat.START)) {
            this.mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
