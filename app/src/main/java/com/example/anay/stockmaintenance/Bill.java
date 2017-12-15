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
    static ArrayList<ItemModel> itemlist = new ArrayList<>();
    static ArrayList<ItemModel> stocklist = new ArrayList<>();
    static ArrayList<ItemModel> billlist = new ArrayList<>();
    RecyclerView recyclerView;
    NoteAdapter itemAdapter;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        itemlist = readFromFile();
        stocklist = readFromStock();
        for(int k=0;k<stocklist.size();k++)
        {
            ItemModel stockitem=stocklist.get(k);
            for(int l=0;l<itemlist.size();l++)
            {
                ItemModel listitem=itemlist.get(l);
                if(listitem.name.equalsIgnoreCase(stockitem.name))
                {
                    stockitem.quantity=Integer.toString(Integer.parseInt(stockitem.quantity)-Integer.parseInt(listitem.quantity));
                    break;
                }
            }
            writeToStock(stockitem);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        itemAdapter= new NoteAdapter(itemlist);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        home = (Button)findViewById(R.id.backtohomeplz);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Bill.this, HomeActivity.class));
            }
        });
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

        ArrayList<ItemModel> itemlist = new ArrayList<>();

        ArrayList<ItemModel> billlist = new ArrayList<>();

        Gson gson=new Gson();
        try{
            File file=new File(getApplicationContext().getFilesDir(),filename);
            String line;
            BufferedReader br=new BufferedReader(new FileReader(file));
            while((line=br.readLine())!=null){
                ItemModel item=gson.fromJson(line,ItemModel.class);

                itemlist.add(item);
                writeToFile(item);
            }
            file.delete();
            br.close();
        }
        catch (Exception e){
            e.getMessage();
        }
        return itemlist;

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

    public ArrayList<ItemModel> readFromStock(){
        String filename="Stock.txt";
        File file=new File(getApplicationContext().getFilesDir(),filename);
        ArrayList<ItemModel> stocklist = new ArrayList<>();
        Gson gson=new Gson();
        try{
            String line;
            BufferedReader br=new BufferedReader(new FileReader(file));
            while((line=br.readLine())!=null){
                ItemModel item=gson.fromJson(line,ItemModel.class);
                stocklist.add(item);
            }
            file.delete();
            br.close();
        }catch (Exception e){
            e.getMessage();
        }

        return stocklist;
    }
    public void writeToStock(ItemModel item) {
        String filename = "Stock.txt";
        Gson gson = new Gson();
        String jsonItem = gson.toJson(item);
        try {
            File file = new File(getApplicationContext().getFilesDir(), filename);
            FileWriter fw = new FileWriter(file, true);
            fw.write(jsonItem + "\n");
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}

}

