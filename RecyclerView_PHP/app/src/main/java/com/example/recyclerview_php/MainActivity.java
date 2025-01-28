package com.example.recyclerview_php;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclerview_php.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private RecyclerView recyclerView;
    private adaptadorPelicula adaptadorPelicula;
    private ArrayList<Pelicula> peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar RecyclerView y Adapter
        recyclerView = findViewById(R.id.recyclerView);
        peliculas = new ArrayList<>();


        // Obtener datos y llenar el RecyclerView
        obtenerDatos(peliculas);
        adaptadorPelicula = new adaptadorPelicula(peliculas);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorPelicula);
    }

    private void obtenerDatos(ArrayList<Pelicula> peliculas) {
        queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/peliculas/obtenerTodasPeliculas.php";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            JSONArray datospeliculas = response.getJSONObject(0).getJSONArray("mensaje");
                            for (int i = 0; i < datospeliculas.length(); i++) {
                                JSONObject per = datospeliculas.getJSONObject(i);
                                int ID = per.getInt("id");
                                String nombre = per.getString("nombre");
                                String director = per.getString("director");
                                String descripcion = per.getString("descripcion");
                                Pelicula pelicula = new Pelicula(ID, nombre, director, descripcion);
                                peliculas.add(pelicula);
                            }
                            // Notificar al Adapter que los datos han cambiado
                            adaptadorPelicula.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                });

        queue.add(jsonObjectRequest);
    }
}
