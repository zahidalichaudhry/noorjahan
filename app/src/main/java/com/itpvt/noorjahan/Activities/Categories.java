package com.itpvt.noorjahan.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itpvt.noorjahan.Adapters.Main_Catagory_Adapter;
import com.itpvt.noorjahan.Config;
import com.itpvt.noorjahan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Categories extends AppCompatActivity {

    ArrayList<com.itpvt.noorjahan.PojoClass.Catagories> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    Main_Catagory_Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Categories.this,MainActivity.class);
                startActivity(intent);
            }
        });
        ImageView whatsapp=(ImageView) findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri  = Uri.parse("smsto:"+"+923000225587");
                Intent intent =new Intent(Intent.ACTION_SENDTO,uri);
                intent.setPackage("com.whatsapp");
                startActivity(intent);
            }
        });
        ImageView bag=(ImageView)findViewById(R.id.bag);
        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Categories.this,My_Cart.class);
                startActivity(intent);
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.model_recyclerView);
        layoutManager=new GridLayoutManager(Categories.this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        MainCategories();
    }
    private void MainCategories()

    {

        loading = ProgressDialog.show(Categories.this,"Loading...","Please wait...",false,false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_All_Categories, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                try {
                    JSONObject obj_level0 = new JSONObject(response);

                    JSONArray data_level0 = obj_level0.getJSONArray("children");
                    for (int i=0;i<data_level0.length();i++)
                    {
                        JSONObject obj_level1=data_level0.getJSONObject(i);
                        JSONArray data_level1 =obj_level1.getJSONArray("children");

                        for (int j =0 ;j< data_level1.length(); j++){

                            JSONObject cat = data_level1.getJSONObject(j);
                            JSONArray data=cat.getJSONArray("children");
                            int childs=data.length();

                            if (cat.getString("is_active").equals("1"))
                            {
                                arrayList.add(new com.itpvt.noorjahan.PojoClass.Catagories(cat.getString("category_id"),cat.getString("parent_id"),
                                        cat.getString("name"),
                                        cat.getString("is_active"),cat.getString("position"),cat.getString("level"),childs));}
                        }
                    }
                    adapter=new Main_Catagory_Adapter(arrayList,Categories.this);
                    recyclerView.setAdapter(adapter);


                }

                catch (JSONException e) {
                    e.printStackTrace();
                    loading.dismiss();
                    //  Log.e("Error",error.printStackTrace());
                    //Toast.makeText(getActivity().getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Categories.this.getApplicationContext(), "Network Connection Error" , Toast.LENGTH_SHORT).show();
                }
                //  tvSurah.setText("Response is: "+ response.substring(0,500));
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                //  Log.e("Error",error.printStackTrace());
                //Toast.makeText(getActivity().getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();
                Toast.makeText(Categories.this.getApplicationContext(), "Network Connection Error" , Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(Categories.this.getApplicationContext());
        requestQueue.add(request);
    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(Categories.this,MainActivity.class);
        finish();
        startActivity(intent);
    }
}
