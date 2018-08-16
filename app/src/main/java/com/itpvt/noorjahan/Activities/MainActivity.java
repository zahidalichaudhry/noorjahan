package com.itpvt.noorjahan.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itpvt.noorjahan.Config;
import com.itpvt.noorjahan.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView newa,lawn,chifone,sales,Home;
    String Sales,Chifone,Newarriaval,Lawn,home;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        newa=(ImageView)findViewById(R.id.newa);
        lawn=(ImageView)findViewById(R.id.lawn);
        sales=(ImageView)findViewById(R.id.sale);
        Home=(ImageView)findViewById(R.id.Home);
        chifone=(ImageView)findViewById(R.id.chifone);
        Newarriaval= Config.NEWARRIVAL;
        Lawn=Config.LAWN;
        Sales=Config.SALE;
        Chifone=Config.CHIFONE;
        home=Config.HOME;
        Glide.with(MainActivity.this).load(Newarriaval).into(newa);
        Glide.with(MainActivity.this).load(Lawn).into(lawn);
        Glide.with(MainActivity.this).load(Sales).into(sales);
        Glide.with(MainActivity.this).load(Chifone).into(chifone);
        Glide.with(MainActivity.this).load(home).into(Home);
        newa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,All_Products_Design.class);
                intent.putExtra("id","3");
                intent.putExtra("title",Config.NEWARRIVAL);
                startActivity(intent);
            }
        });
        lawn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,All_Products_Design.class);
                intent.putExtra("id","10");
                intent.putExtra("title",Config.LAWN);
                startActivity(intent);
            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Home.class);
                startActivity(intent);
            }
        });
        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,All_Products_Design.class);
                intent.putExtra("id","11");
                intent.putExtra("title",Config.SALE);
                startActivity(intent);

            }
        });
        chifone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,All_Products_Design.class);
                intent.putExtra("id","8");
                intent.putExtra("title",Config.SALE);
                startActivity(intent);
            }
        });
        Button facebook=(Button)findViewById(R.id.facebook);
        Button instagram=(Button)findViewById(R.id.insta);
        TextView textView=(TextView)findViewById(R.id.textView4) ;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://noorjahan.pk/index.php/"));
                startActivity(myIntent);
            }
        });
        LinearLayout footer=(LinearLayout)findViewById(R.id.footer);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://itpvt.net/"));
                startActivity(myIntent);
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Noor-Jahan-Collection-740901429357673/"));
                startActivity(myIntent);
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/noorjahancollection/"));
                startActivity(myIntent);
            }
        });
//        setSupportActionBar(toolbar);

//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home)
        {
            // Handle the camera action
//        } else if (id == R.id.Store)
//        {
//
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);

        } else if (id == R.id.Categories) {
            Intent intent = new Intent(MainActivity.this, Categories.class);
            startActivity(intent);

        } else if (id == R.id.Cart) {

            Intent intent = new Intent(MainActivity.this, My_Cart.class);
            finish();
            startActivity(intent);
        } else if (id == R.id.Whatsapp)
        {
            Uri uri  = Uri.parse("smsto:"+"+923000225587");
            Intent intent =new Intent(Intent.ACTION_SENDTO,uri);
            intent.setPackage("com.whatsapp");
            startActivity(intent);

        } else if (id == R.id.Innovators)
        {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://itpvt.net/"));
            startActivity(myIntent);

        }else if (id == R.id.web) {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://noorjahan.pk/index.php/"));
            startActivity(myIntent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
