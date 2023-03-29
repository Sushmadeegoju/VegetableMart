package com.example.sushma.vegetablemart;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

import com.example.sushma.vegetablemart.Adapters.MarketAdapter;
import com.example.sushma.vegetablemart.Fragments.FruitsFragment;
import com.example.sushma.vegetablemart.Fragments.MarketFragment;
import com.example.sushma.vegetablemart.Fragments.VegetablesFragment;

import static com.example.sushma.vegetablemart.LoginActivity.sp;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public Fragment fragment;
    public FragmentManager fragmentManager;
    public MarketAdapter marketAdapter;
    /*TabLayout tabLayout;
    ViewPager viewPager;
    public SectionsPagerAdapter sectionsPagerAdapter;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(fragment==null){
            fragment=new MarketFragment();
            onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));
            fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
        }

        /*tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        sectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);*/
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
        getMenuInflater().inflate(R.menu.home, menu);
      /*  MenuItem item=menu.findItem(R.id.search);
        SearchManager searchManager= (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      if(id==R.id.logout){
            SharedPreferences.Editor editor=sp.edit();
            editor.remove("mobile");
            editor.remove("password");
            editor.remove("key");
            editor.apply();
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.cart){
            Intent intent=new Intent(this,CartActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_market) {
            setTitle(item.getTitle());
            fragment=new MarketFragment();
            fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
        } else if (id == R.id.nav_vegetables) {
            setTitle(item.getTitle());
            fragment=new VegetablesFragment();
            fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
        } else if (id == R.id.nav_fruits) {
            setTitle(item.getTitle());
            fragment=new FruitsFragment();
            fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
        } else if (id == R.id.nav_cart) {
            Intent intent=new Intent(this,CartActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_myOrders){
            Intent intent=new Intent(this,MyOrdersActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_account) {
            Intent intent=new Intent(this,ProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_rateUs) {

        }
        else if (id == R.id.nav_contactUs) {
            Intent intent=new Intent(this,ContactUsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void vegetables(View view) {
        fragment=new VegetablesFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }

    public void fruits(View view) {
        fragment=new FruitsFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }

    public void all(View view) {
        fragment=new MarketFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();
    }



}
