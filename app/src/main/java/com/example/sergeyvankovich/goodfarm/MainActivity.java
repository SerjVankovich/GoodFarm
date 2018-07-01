package com.example.sergeyvankovich.goodfarm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sergeyvankovich.goodfarm.adapters.RecAdapter;
import com.example.sergeyvankovich.goodfarm.fragments.CartFragment;
import com.example.sergeyvankovich.goodfarm.models.Product;
import com.example.sergeyvankovich.goodfarm.models.Set;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private static Display display;
    private CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cartFragment = new CartFragment();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, cartFragment).commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_camera);
        navigationView.setNavigationItemSelectedListener(this);

        display = getWindowManager().getDefaultDisplay();

        int width = getWindowWidth();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecAdapter(mock(), this, width - 80));
        Log.d("MyLog", "" + width);
    }

    private List<Set> mock() {
        List<Set> sets = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("Люля-кебаб", 500);
        product1.setImgUrl("https://esh-derevenskoe.ru/image/cache/catalog/product/672/zd3a4121-400x400.jpg");
        Product product2 = new Product("Край с рёбрышком в маринаде", 1);
        product2.setImgUrl("https://esh-derevenskoe.ru/image/cache/catalog/product/1551/3f5033-400x400.png");
        Product product3 = new Product("Хлеб \"Кукурузный с сыром\"", 260);
        product3.setImgUrl("https://esh-derevenskoe.ru/image/cache/catalog/product/81/img_1360-400x400.jpg");
        products.add(product1);
        products.add(product2);
        products.add(product3);
        sets.add(new Set("НАБОР ДЛЯ ГРИЛЯ", "ЛЮЛЯ-КЕБАБ, СТЕЙКИ ИЗ ГОВЯДИНЫ, СВЕЖИЕ ОВОЩИ, МАЙОНЕЗ И ХЛЕБ",
                2355, "https://esh-derevenskoe.ru/image/cache/catalog/set/6479d3-340x180.png", products));
        sets.add(new Set("СЕМЕЙНЫЙ НАБОР", "НАБОР НА НЕДЕЛЮ ИЗ 17 ПРОДУКТОВ", 3000, "https://esh-derevenskoe.ru/image/cache/catalog/set/2-340x180.jpg", new ArrayList<Product>()));
        return  sets;
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
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static int getWindowWidth() {
        return display.getWidth();
    }
}
