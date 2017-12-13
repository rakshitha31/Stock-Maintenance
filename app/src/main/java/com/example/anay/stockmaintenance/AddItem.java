package com.example.anay.stockmaintenance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddItem extends AppCompatActivity {

    Button add;
    EditText nameid,quantityid;
    String name,quantity;
    SharedPreferences auth_sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        nameid=(EditText)findViewById(R.id.name);
        quantityid=(EditText)findViewById(R.id.quantity);
        add=(Button)findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get name and quantity fron user
                String name=nameid.getText().toString();
                String quantity=quantityid.getText().toString();
                auth_sharedPref=getApplicationContext().getSharedPreferences(getString(R.string.auth_sharedpref), Context.MODE_PRIVATE);

                String saved_name = auth_sharedPref.getString(getString(R.string.key_name), "");
                String saved_quantity = auth_sharedPref.getString(getString(R.string.key_quantity), "");


                if(name.isEmpty()||quantity.isEmpty())
                {
                    Toast.makeText(AddItem.this, "Fields Cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ItemModel item = new ItemModel(name, quantity);
                    NoteAdapter.itemlist.add(item);
                    writeToFile(item);
                    Intent intent = new Intent(AddItem.this, NewBill.class);
                    intent.putExtra(getString(R.string.key_name), name);
                    intent.putExtra(getString(R.string.key_quantity), quantity);
                    startActivity(intent);
                }

            }
        });

    }
        public void writeToFile(ItemModel item){
        String filename="Items.txt";
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