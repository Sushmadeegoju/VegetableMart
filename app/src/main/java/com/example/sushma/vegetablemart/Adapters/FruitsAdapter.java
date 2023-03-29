package com.example.sushma.vegetablemart.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sushma.vegetablemart.Databases.CartDatabase;
import com.example.sushma.vegetablemart.Pozos.CartPozo;
import com.example.sushma.vegetablemart.R;

public class FruitsAdapter extends RecyclerView.Adapter<FruitsAdapter.MyViewHolder> {
    Context context;
    String[] names;
    Integer[] pics;
    public Double[] price;
    String name,cost,quantity;
    ImageView image;
    CartPozo pozo;
    CartDatabase cartDatabase;
    public FruitsAdapter(Context context, String[] names, Integer[] pics, Double[] price) {
        this.context=context;
        this.names=names;
        this.pics=pics;
        this.price=price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout,parent,false);
        cartDatabase=new CartDatabase(context);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.iv.setImageResource(pics[position]);
        holder.tv1.setText(names[position]);
        holder.tv2.setText(""+price[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv1,tv2,tv3;
        Button b1,b2,b3;
        double count=0.5;
        int pos;
        double pr;
        int image;
        double totalAmount;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.imageView3);
            tv1=itemView.findViewById(R.id.textView27);//name
            tv2=itemView.findViewById(R.id.textView26);//price
            tv3=itemView.findViewById(R.id.textView25);//QUANTITY
            tv3.setText(""+count+" KG");
            b1=itemView.findViewById(R.id.button20);//+
            b2=itemView.findViewById(R.id.button21);//-
            b3=itemView.findViewById(R.id.button22);//add to cart
            b3.setOnClickListener(new View.OnClickListener() {
                class MyAnimationListener implements Animation.AnimationListener {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        iv.clearAnimation();
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(iv.getWidth(), iv.getHeight());
                        lp.setMargins(20, 20, 0, 0);
                        iv.setLayoutParams(lp);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                }

                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Added to Cart successfully",Toast.LENGTH_LONG).show();
                    pos=getAdapterPosition();
                    TranslateAnimation animation = new TranslateAnimation(0,325,0,325);
                    animation.setDuration(1000);
                    animation.setFillAfter(false);
                    animation.setAnimationListener(new MyAnimationListener());

                    iv.setImageResource(pics[pos]);
                    iv.startAnimation(animation);


                    name=tv1.getText().toString();
                    cost=tv2.getText().toString();
                    quantity=tv3.getText().toString();
                    pr=(price[pos]/count)/2;
                    totalAmount+=price[pos];
                    image=pics[pos];

                    pozo=new CartPozo(name,cost,quantity,image,pr*2);
                    if(cartDatabase.check(name)){
                        cartDatabase.deleteSingleRecord(name);
                    }
                    cartDatabase.insertData(pozo);
                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos=getAdapterPosition();
                    pr=(price[pos]/count)/2;
                    count+=0.5;
                    price[pos]+=pr;
                    tv3.setText(""+count+"KG");
                    tv2.setText(""+(price[pos]));
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos=getAdapterPosition();
                    pr=(price[pos]/count)/2;
                    if(count!=0.5) {
                        count -= 0.5;
                        price[pos] -= pr;
                        tv3.setText("" + count + "KG");
                        tv2.setText("" + (price[pos]));
                    }
                    else{
                        final AlertDialog.Builder builder=new AlertDialog.Builder(context);
                        builder.setTitle("Invalid Choice").setMessage("Please select atleast 500gm").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                builder.setCancelable(true);
                            }
                        }).setCancelable(false).show();
                    }
                }
            });
        }
    }
}
