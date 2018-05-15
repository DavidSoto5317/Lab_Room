package com.example.david.labroom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.util.List;

public class Activity_B extends AppCompatActivity {
    protected TextView transferenciaString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__b);
        transferenciaString = findViewById(R.id.transferenciaString);
        AppDataBase database = AppDataBase.getDatabase(Activity_B.this);
        List<Weather> listaDatosFinales = database.dao().getDescriptionById(1);
        Weather objects = null;
        if(!listaDatosFinales.isEmpty()){
            objects = listaDatosFinales.get(0);
        }
        if(objects != null){
            transferenciaString.setText(objects.getDescription());
        } else
            transferenciaString.setText(R.string.error);
    }
}
