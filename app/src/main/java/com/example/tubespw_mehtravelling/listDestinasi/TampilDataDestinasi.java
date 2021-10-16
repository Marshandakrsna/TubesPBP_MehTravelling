package com.example.tubespw_mehtravelling.listDestinasi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.tubespw_mehtravelling.databinding.ActivityTampilListDestinasiBinding;
import com.example.tubespw_mehtravelling.R;

import java.util.ArrayList;

public class TampilDataDestinasi extends AppCompatActivity {

    ArrayList<DataDestinasi> DestinasiList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private  ActivityTampilListDestinasiBinding binding;
    private RecyclerView rvDestinasi;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tampil_list_destinasi);

        DestinasiList = new DaftarDestinasi().DataDestinasi;

        //recyler view
        recyclerView = binding.rvDestinasi;
        adapter = new RecyclerViewAdapter(TampilDataDestinasi.this,DestinasiList);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);


//        binding.rvDestinasi.setLayoutManager(new LinearLayoutManager(this.getBaseContext(),LinearLayoutManager.VERTICAL,false));
//        binding.rvDestinasi.setAdapter(new RecyclerViewAdapter(DestinasiList));

    }
}