package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onBtnClick(View view) {
        TextView txtHello = findViewById(R.id.txtMsg);
        EditText edtTxtName = findViewById(R.id.edtTxtName);

        String nomProduit;

        try {
            nomProduit = Product.getProductFromBarCode(edtTxtName.getText().toString()).getNom();
        }
        catch (Exception e){
            if(Objects.equals(e.getMessage(), "product not found")){
                nomProduit = "Produit non trouvé";
            }
            else {
                nomProduit = e.toString();
            }
        }

        txtHello.setText("Nom du produit : " + nomProduit);
    }

}