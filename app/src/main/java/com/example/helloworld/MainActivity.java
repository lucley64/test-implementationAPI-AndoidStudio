package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onBtnClick(View view) {
        TextView txtHello = findViewById(R.id.txtMsg);
        EditText edtTxtName = findViewById(R.id.edtTxtName);

//        String nomProduit = getProductNameFromBarCode(edtTxtName.getText().toString());
        String nomProduit = getProductNameFromBarCode("3274080005003");

        txtHello.setText("Nom du produit : " + nomProduit);


    }
    private String getProductNameFromBarCode(String barCode){
        String ret;
        try{
            URL url = new URL("https://fr.openfoodfacts.org/api/v0/product/" + barCode + ".json");
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "ScanEco - Android - Version 0.1");
            InputStream inputStream = connection.getInputStream(); // Crash
            Scanner scanner = new Scanner(inputStream);
            String json = scanner.useDelimiter("\\A").next();
            JsonReader jsonReader = new JsonReader(new StringReader(json));
            jsonReader.beginObject();
            ret = "none";
            while (jsonReader.hasNext()){
                String name = jsonReader.nextName();
                System.out.println(name);
            }
            jsonReader.close();
            scanner.close();
            inputStream.close();
        }
        catch (Exception e){
            ret = e.toString();
        }
        return ret;
    }

}