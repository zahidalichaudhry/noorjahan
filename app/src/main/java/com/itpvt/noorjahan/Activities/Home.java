package com.itpvt.noorjahan.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.itpvt.noorjahan.Adapters.Recycler_Adapter_All_Products_new;
import com.itpvt.noorjahan.Config;
import com.itpvt.noorjahan.PojoClass.All_product_pojo;
import com.itpvt.noorjahan.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity  implements BaseSliderView.OnSliderClickListener {
    SliderLayout sliderLayout ;
    static String path0;
    LinearLayout footer;
    String id;
    ArrayList<All_product_pojo> arrayList=new ArrayList<>();
    ArrayList<All_product_pojo> arrayList2=new ArrayList<>();
    RecyclerView recyclerView,recyclerView2;
    String newArr_cat_id="3";
    String sale_cat_id="6";

    Recycler_Adapter_All_Products_new adapter;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog loading;
    Button facebook,instagram;
    String menimage,womenimage,saleimage,bajiImage;
    static String path1,path2;
    ImageView men,women,sale,New;
    TextView new_a,new_a2,new_tx,sale_tx;
    HashMap<String, String> HashMapForURL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove notification bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);


        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        path0 = Config.BANNER1;
        path1= Config.BANNER2;
//        path2=Config.BANNER3;
        saleimage=Config.HOMW_SALE;
        bajiImage  = Config.HOME_FOOTWARE;
        recyclerView=(RecyclerView)findViewById(R.id.model_recyclerView);
        recyclerView2=(RecyclerView)findViewById(R.id.model_recyclerView2);
        new_a=(TextView)findViewById(R.id.new_a);
        new_a2=(TextView)findViewById(R.id.new_a2);

        facebook=(Button)findViewById(R.id.facebook);
        instagram=(Button)findViewById(R.id.insta);

        new_tx=(TextView)findViewById(R.id.new_tx);
        sale_tx=(TextView)findViewById(R.id.sale_tx);
        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
        footer=(LinearLayout)findViewById(R.id.footer);
        new_a=(TextView)findViewById(R.id.new_a);
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
        AddImagesUrlOnline();
        GetAllProductsnew();
        GetAllProductssale();
    }

    private void GetAllProductsnew()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_ALL_PRODUCTS, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


//                loading.dismiss();
                new_a2.setVisibility(View.GONE);




                try {
                    JSONObject abc= new JSONObject(response);
                    int j=abc.length();
                    for (int i=j;i>=1;i--)
                    {
                        String num= String.valueOf(i);
                        JSONObject data=abc.getJSONObject(num);
                        if (data.getString("product_quantity").equals("1"))
                        {
                            arrayList2.add(new All_product_pojo(data.getString("product_id"),data.getString("pro_name")
                                    ,data.getString("img_url").replace("localhost",Config.ip),data.getString("sku")
                                    ,data.getString("product_quantity"),data.getString("price").replace(".0000","Rs")));
                        }

                    }

//                        do {JSONObject data = new getJSONObject.JSONObject("abc");
//                            String num= String.valueOf(i);
//                            obj_level1=data.getJSONObject(num);
//
//                            arrayList.add(new All_product_pojo(obj_level1.getString("product_id"),obj_level1.getString("pro_name")
//                                    ,obj_level1.getString("img_url")));
//                            i++;
//
//                        }while (obj_level1.getJSONObject(String.valueOf(i))==null);
//                        {
//                            i++;
//
//                        }
                    adapter=new Recycler_Adapter_All_Products_new(arrayList2,Home.this);
                    recyclerView2.setAdapter(adapter);


                }

                catch (JSONException e) {
                    e.printStackTrace();
//                    loading.dismiss();
                    new_a2.setVisibility(View.GONE);
                    recyclerView2.setVisibility(View.GONE);
                    sale_tx.setVisibility(View.GONE);

                }



                //  tvSurah.setText("Response is: "+ response.substring(0,500));
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                loading.dismiss();
                //  Log.e("Error",error.printStackTrace());
                new_a2.setVisibility(View.GONE);
                recyclerView2.setVisibility(View.GONE);
                sale_tx.setVisibility(View.GONE);
//                Toast.makeText(getActivity().getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("category_id", sale_cat_id);
                return params;
            }
        };
        //////to stop reties and wait for respone more than regular time/////
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(Home.this.getApplicationContext());
        requestQueue.add(request);
    }

    private void GetAllProductssale()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_ALL_PRODUCTS, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


//                loading.dismiss();
                new_a.setVisibility(View.GONE);




                try {
                    JSONObject abc= new JSONObject(response);
                    int j=abc.length();
                    for (int i=j;i>=1;i--)
                    {
                        String num= String.valueOf(i);
                        JSONObject data=abc.getJSONObject(num);
                        if (data.getString("product_quantity").equals("1"))
                        {
                            arrayList2.add(new All_product_pojo(data.getString("product_id"),data.getString("pro_name")
                                    ,data.getString("img_url").replace("localhost",Config.ip),data.getString("sku")
                                    ,data.getString("product_quantity"),data.getString("price").replace(".0000","Rs")));
                        }

                    }

//                        do {JSONObject data = new getJSONObject.JSONObject("abc");
//                            String num= String.valueOf(i);
//                            obj_level1=data.getJSONObject(num);
//
//                            arrayList.add(new All_product_pojo(obj_level1.getString("product_id"),obj_level1.getString("pro_name")
//                                    ,obj_level1.getString("img_url")));
//                            i++;
//
//                        }while (obj_level1.getJSONObject(String.valueOf(i))==null);
//                        {
//                            i++;
//
//                        }
                    adapter=new Recycler_Adapter_All_Products_new(arrayList2,Home.this);
                    recyclerView.setAdapter(adapter);


                }

                catch (JSONException e) {
                    e.printStackTrace();
//                    loading.dismiss();
                    new_a.setVisibility(View.GONE);

                    recyclerView.setVisibility(View.GONE);
                    new_tx.setVisibility(View.GONE);

                }



                //  tvSurah.setText("Response is: "+ response.substring(0,500));
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                loading.dismiss();
                //  Log.e("Error",error.printStackTrace());
                new_a.setVisibility(View.GONE);
                new_tx.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
//                Toast.makeText(getActivity().getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("category_id", newArr_cat_id);
                return params;
            }
        };
        //////to stop reties and wait for respone more than regular time/////
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(Home.this.getApplicationContext());
        requestQueue.add(request);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
    private void AddImagesUrlOnline()

    {

        HashMapForURL = new HashMap<String, String>();

        HashMapForURL.put(" ", path0);
        HashMapForURL.put("  ", path1);
//        HashMapForURL.put("  ", path1);

        callSlider();
    }
    private void callSlider() {

        for(String name : HashMapForURL.keySet()){

            TextSliderView textSliderView = new TextSliderView(Home.this.getApplicationContext());

            textSliderView
                    .description(name)
                    .image(HashMapForURL.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(8000);
    }
}
