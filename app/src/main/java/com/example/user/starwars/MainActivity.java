package com.example.user.starwars;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_people);
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_people));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction transaction;
        switch(id){
            case R.id.nav_people:
                Timber.i("podmieniam fragment");
                PeopleFragment peopleFragment = new PeopleFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container_layout, peopleFragment);
                transaction.commit();
                break;
            case R.id.nav_films:
                Timber.i("podmieniam fragment");
                FilmsFragment filmsFragment = new FilmsFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container_layout, filmsFragment);
                transaction.commit();
                break;
            case R.id.nav_planets:
                Timber.i("podmieniam fragment");
                PlanetsFragment planetsFragment = new PlanetsFragment();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container_layout, planetsFragment);
                transaction.commit();
                break;
            case R.id.nav_starships:
                Timber.i("podmieniam fragment");
                StarshipsFragment starshipsFragment = new StarshipsFragment();
                transaction =  getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container_layout, starshipsFragment);
                transaction.commit();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
