package com.example.anay.stockmaintenance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {
Button add;
EditText nameid,quantityid;
String name,quantity;
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
                String name=nameid.getText().toString();
                String quantity=quantityid.getText().toString();
                ItemModel item =new ItemModel(name,quantity);
                //NoteAdapter.itemlist.add(item);
                Intent intent =new Intent(AddItem.this,NewBill.class);
                       startActivity(intent);

            }
        });

    }


}
