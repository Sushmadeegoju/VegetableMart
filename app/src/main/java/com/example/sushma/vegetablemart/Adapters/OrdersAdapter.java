package com.example.sushma.vegetablemart.Adapters;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sushma.vegetablemart.Databases.CartDatabase;
import com.example.sushma.vegetablemart.MyOrdersActivity;
import com.example.sushma.vegetablemart.Pozos.OrdersPojo;
import com.example.sushma.vegetablemart.R;

import java.util.Calendar;
import java.util.List;

import static com.example.sushma.vegetablemart.LoginActivity.sp;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder>{
    CartDatabase cartDatabase;
    List<OrdersPojo> list;
    Context context;
    Calendar calendar=Calendar.getInstance();
    public OrdersAdapter(MyOrdersActivity myOrdersActivity,List<OrdersPojo> list) {
        context=myOrdersActivity;
        this.list=list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.myorders_custom_layout,parent,false);
        cartDatabase=new CartDatabase(context);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrdersPojo pojo=list.get(position);
        holder.tv3.setText("Total Amount: Rs. "+ pojo.getAmount() +"/-");
        holder.tv2.setText("Date : "+pojo.getDate());
    }
    @Override
    public int getItemCount() {

        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv1,tv2,tv3;
        public MyViewHolder(View itemView) {
            super(itemView);


            tv1=itemView.findViewById(R.id.textView38);//order id
            tv2=itemView.findViewById(R.id.textView41);//date
            tv3=itemView.findViewById(R.id.textView42);//total amount
            tv1.setText("Order_ID : "+sp.getString("mobile",null));

        }
    }
}
