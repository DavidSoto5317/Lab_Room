package com.example.david.labroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Activity_A extends AppCompatActivity {
    String urlWeather = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
    JSONObject resultWeather;
    JSONObject objRequest;
    List<String> datos;
    ListView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        datos = new ArrayList<>();
        listado = findViewById(R.id.list_a);

        //Intent para hacer que al presionar un objeto de la lista nos mande al activity B con la informacion
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Activity_A.this, Activity_B.class);
                startActivity(intent);
            }
        });

        //Se realiza un adapter para el listado con los datos de la base de dato Room
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Activity_A.this, android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adapter);
        final AppDataBase database = AppDataBase.getDatabase(Activity_A.this);
        RequestQueue queue = Volley.newRequestQueue(Activity_A.this);

        JsonObjectRequest objRequest =
                new JsonObjectRequest(Request.Method.GET, urlWeather, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Referencia tomada
                            //Reference: http://www.vogella.com/tutorials/AndroidSQLite/article.html

                            List<Weather> listadoRespuesta = database.dao().getDescriptionById(1);
                            if(listadoRespuesta.isEmpty())
                            {
                                Weather data = new Weather(1, response.toString());
                                database.dao().insertWeather(data);
                                Toast.makeText(Activity_A.this, "Agregado", Toast.LENGTH_SHORT).show();
                            }
                            JSONObject respuesta = new JSONObject(listadoRespuesta.get(0).getDescription()).getJSONObject("main");
                            //Se agregan datos a la lista de tipo String
                            datos.add("temp:" + respuesta.getString("temp"));
                            datos.add("pressure:" + respuesta.getString("pressure"));
                            datos.add("humidity:" + respuesta.getString("humidity"));
                            datos.add("temp_min:" + respuesta.getString("temp_min"));
                            datos.add("temp_max:" + respuesta.getString("temp_max"));
                            adapter.notifyDataSetChanged();

                            //Prueba Inicial de Conexion
                            //resultWeather = (JSONObject) response.getJSONArray("weather").get(0);
                            //System.out.println(resultWeather.getString("description"));
                            //System.out.println(response.getJSONObject("main").getString("temp"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getMessage());
                    }
                });

        queue.add(objRequest);
    }
}
