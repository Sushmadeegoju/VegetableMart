package com.example.sushma.vegetablemart.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sushma.vegetablemart.Adapters.MarketAdapter;
import com.example.sushma.vegetablemart.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketFragment extends Fragment {
    public MarketAdapter adapter;
    RecyclerView rv;
    public MarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_market, container, false);
        rv=v.findViewById(R.id.recyclerView3);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        String[] names={"Potato","Onion","Tomato","Mirchi","Brinjal","Apple","Banana","Black Grapes","Green Grapes","Guava","Mango","Oranges","PineApple"};
        Integer[] pics={R.drawable.potato,R.drawable.onion,R.drawable.tomato,R.drawable.mirchi,R.drawable.brinjal,R.drawable.apple,R.drawable.banana2,R.drawable.blackgrapes,R.drawable.greengrapes,R.drawable.guava,R.drawable.mango,R.drawable.oranges,R.drawable.pineapple};
        Double[] price={60.00,50.00,30.00,50.00,70.00,60.00,50.00,30.00,50.00,70.00,60.00,50.00,30.00};
        adapter=new MarketAdapter(getActivity(),names,pics,price);
        rv.setAdapter(adapter);
        return v;
    }

}
