package com.example.anay.stockmaintenance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Bill extends AppCompatActivity {
    static ArrayList<ItemModel> billlist = new ArrayList<>();
    RecyclerView recyclerView;
    NoteAdapter itemAdapter;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bill);

        billlist = readFromFile();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        itemAdapter= new NoteAdapter(billlist);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //home = (Button)findViewById(R.id.backtohome);
        //home.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View view) {
            //    startActivity(new Intent(Bill.this, HomeActivity.class));
            //}
        //});
    }


    public ArrayList<ItemModel> readFromFile(){
        String filename="Items.txt";
        ArrayList<ItemModel> billlist = new ArrayList<>();
        Gson gson=new Gson();
        try{
            File file=new File(getApplicationContext().getFilesDir(),filename);
            String line;
            BufferedReader br=new BufferedReader(new FileReader(file));
            while((line=br.readLine())!=null){
                ItemModel item=gson.fromJson(line,ItemModel.class);
                billlist.add(item);
                writeToFile(item);
            }
            br.close();
            file.delete();
        }catch (Exception e){
            e.getMessage();
        }
        return billlist;
    }
    public void writeToFile(ItemModel item){
        String filename="Bills.txt";
        Gson gson=new Gson();
        String jsonItem =gson.toJson(item);
        try{
            File file=new File(getApplicationContext().getFilesDir(),filename);
            FileWriter fw=new FileWriter(file,true);
            fw.write(jsonItem+"\n");
            fw.close();
        }
        catch (IOException e){
            e.getMessage();
        }
    }
}
