package com.uth.ejercicio_2_3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uth.e23.R;
import com.uth.ejercicio_2_3.adapter.adapterSignature;
import com.uth.ejercicio_2_3.database.databa_picture;
import com.uth.ejercicio_2_3.models.cPhotograh;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private FloatingActionButton oFloatingActionButton;
    private RecyclerView oRecyclerView;

    private adapterSignature oAdapterSignature;

    private databa_picture oDBPICTURE;
    private ArrayList<cPhotograh> oSignatureArrayList = new ArrayList<>();

    static final int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.oFloatingActionButton = findViewById(R.id.floatingButtonSignature);
        this.oRecyclerView = findViewById(R.id.RecyclerViewSignature);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        oDBPICTURE = new databa_picture(MainActivity.this);

        oSignatureArrayList = oDBPICTURE.leerSignature();
        oAdapterSignature = new adapterSignature(oSignatureArrayList);

        this.oFloatingActionButton.setOnClickListener(this);
        oRecyclerView.setLayoutManager(mLinearLayoutManager);

        oRecyclerView.setAdapter(oAdapterSignature);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateSignature();
        super.onResume();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateSignature()
    {
        oSignatureArrayList.clear();
        ArrayList<cPhotograh> oA = oDBPICTURE.leerSignature();
        for (int i = 0;i<oA.size();i++)
        {
            cPhotograh oS = new cPhotograh();
            oS.setDescripcion(oA.get(i).getDescripcion());
            oS.setFoto(oA.get(i).getFoto());
            oSignatureArrayList.add(oS);
        }
        oAdapterSignature.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.floatingButtonSignature)
        {
            Intent oIntent = new Intent(MainActivity.this, picture_data.class);
            startActivityForResult(oIntent,REQUEST_CODE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE)
        {
            updateSignature();
        }
    }

}