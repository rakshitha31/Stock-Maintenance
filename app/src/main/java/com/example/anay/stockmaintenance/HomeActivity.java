package com.example.anay.stockmaintenance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void act(View view)
    {
        Intent intent = new Intent(this,NewBill.class);
        this.startActivity(intent);
    }
    public void Click(View view) //onCLick Listener for going to another activity named UpdateStock
    {
        Intent intent = new Intent(this,UpdateStock.class);
        this.startActivity(intent);
    }
}
