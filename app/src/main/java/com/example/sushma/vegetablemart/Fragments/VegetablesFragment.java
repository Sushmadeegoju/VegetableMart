package com.example.sushma.vegetablemart.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sushma.vegetablemart.Adapters.VegetableAdapter;
import com.example.sushma.vegetablemart.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VegetablesFragment extends Fragment {
    public VegetableAdapter adapter;
    RecyclerView rv;
    public VegetablesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_vegetables, container, false);
        rv=v.findViewById(R.id.recyclerView2);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        String[] names={"Potato","Onion","Tomato","Mirchi","Brinjal"};
        Integer[] pics={R.drawable.potato,R.drawable.onion,R.drawable.tomato,R.drawable.mirchi,R.drawable.brinjal};
        Double[] price={60.00,50.00,30.00,50.00,70.00};
        adapter=new VegetableAdapter(getActivity(),names,pics,price);
        rv.setAdapter(adapter);
        return v;
    }

}
