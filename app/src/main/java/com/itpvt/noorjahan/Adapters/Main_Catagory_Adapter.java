package com.itpvt.noorjahan.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.itpvt.noorjahan.Activities.All_Products;
import com.itpvt.noorjahan.Activities.Sub_Categories;
import com.itpvt.noorjahan.Config;
import com.itpvt.noorjahan.PojoClass.Catagories;
import com.itpvt.noorjahan.R;

import java.util.ArrayList;

/**
 * Created by CH-Hamza on 2/19/2018.
 */

public class Main_Catagory_Adapter extends RecyclerView.Adapter<Main_Catagory_Adapter.MyViewHolder> {
    ArrayList<Catagories> arrayList= new ArrayList<>();
    Activity activity;
    String WEB_URL= Config.URL_BASE_WEBVIEW;
    public Main_Catagory_Adapter(ArrayList<Catagories> arrayList, Context context)
    {
        this.arrayList=arrayList;
        activity=(Activity)context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.catagories,parent,false);
        return new MyViewHolder(view);
    }


//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
//    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int length=arrayList.get(position).getChild();
                if (length!=0)
                {
                    String Subpart=WEB_URL+"/"+arrayList.get(position).getName().toLowerCase().replace(" ","-");
                    Intent intent = new Intent(activity,Sub_Categories.class);
                    intent.putExtra("id",arrayList.get(position).getCategory_id());
                    intent.putExtra("title",arrayList.get(position).getName());
                    intent.putExtra("weburl",Subpart);
                    activity.startActivity(intent);
                }
                else
                {
                    String Subpart=WEB_URL+"/"+arrayList.get(position).getName().toLowerCase().replace(" ","-");
                    Intent intent=new Intent(activity,All_Products.class);
                    intent.putExtra("id",arrayList.get(position).getCategory_id());
                    intent.putExtra("title",arrayList.get(position).getName());
                    activity.startActivity(intent);
//                   Toast.makeText(activity,"Open Product Activity",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public int getItemCount(){return arrayList.size();}

    public static class MyViewHolder extends  RecyclerView.ViewHolder
    {
        Button name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(Button)itemView.findViewById(R.id.name);
        }
    }
}