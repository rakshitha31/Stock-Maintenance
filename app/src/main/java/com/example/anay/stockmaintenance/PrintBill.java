package com.example.anay.stockmaintenance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class PrintBill extends AppCompatActivity {static ArrayList<ItemModel> itemlist = new ArrayList<>();
    RecyclerView recyclerView;
    NoteAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemlist = readFromFile();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        itemAdapter= new NoteAdapter(itemlist);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public ArrayList<ItemModel> readFromFile(){
        String filename="Items.txt";
        ArrayList<ItemModel> itemlist = new ArrayList<>();
        Gson gson=new Gson();
        try{
            File file=new File(getApplicationContext().getFilesDir(),filename);
            String line;
            BufferedReader br=new BufferedReader(new FileReader(file));
            while((line=br.readLine())!=null){
                ItemModel item=gson.fromJson(line,ItemModel.class);
                itemlist.add(item);
            }
            br.close();
        }catch (Exception e){
            e.getMessage();
        }
        return itemlist;
    }
}
