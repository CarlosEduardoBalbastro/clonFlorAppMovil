package com.ispc.florappmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GaleriaActivity extends AppCompatActivity {

    private Button btnFiltroTodas;
    private Button btnFiltroArboles;
    private Button btnFiltroArbustos;
    private Button btnFiltroHierbas;

    private Button btnContacto;
    private Button btnPerfil;

    private LinearLayout ficha1;
    private LinearLayout ficha2;
    private LinearLayout ficha3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        btnFiltroTodas = findViewById(R.id.btnFiltroTodas);
        btnFiltroArboles = findViewById(R.id.btnFiltroArboles);
        btnFiltroArbustos = findViewById(R.id.btnFiltroArbustos);
        btnFiltroHierbas = findViewById(R.id.btnFiltroHierbas);
        btnContacto = findViewById(R.id.btnContacto);
        btnPerfil = findViewById(R.id.btnPerfil);

        ficha1 = findViewById(R.id.ficha1);
        ficha2 = findViewById(R.id.ficha2);
        ficha3 = findViewById(R.id.ficha3);

        cargarEspecies();

        btnFiltroTodas.setOnClickListener(v ->
                Toast.makeText(GaleriaActivity.this, "Filtro: Todas", Toast.LENGTH_SHORT).show()
        );

        btnContacto.setOnClickListener(v -> {
            Intent intent = new Intent(GaleriaActivity.this, ContactoActivity.class);
            startActivity(intent);
        });

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(GaleriaActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        btnFiltroArboles.setOnClickListener(v ->
                Toast.makeText(GaleriaActivity.this, "Filtro: Árboles", Toast.LENGTH_SHORT).show()
        );

        btnFiltroArbustos.setOnClickListener(v ->
                Toast.makeText(GaleriaActivity.this, "Filtro: Arbustos", Toast.LENGTH_SHORT).show()
        );

        btnFiltroHierbas.setOnClickListener(v ->
                Toast.makeText(GaleriaActivity.this, "Filtro: Hierbas", Toast.LENGTH_SHORT).show()
        );
    }

    private void cargarEspecies() {
        String token = getIntent().getStringExtra("TOKEN");

        if (token == null || token.isEmpty()) {
            SharedPreferences preferences = getSharedPreferences("AppSession", MODE_PRIVATE);
            token = preferences.getString("token", "");
        }

        String tokenLimpio = token.replace("\"", "").trim();
        String authHeader = "Bearer " + tokenLimpio;

        ApiClient.getApiService().getEspecies(authHeader).enqueue(new Callback<List<Especie>>() {
            @Override
            public void onResponse(Call<List<Especie>> call, Response<List<Especie>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Especie> listaEspecies = response.body();
                    Toast.makeText(GaleriaActivity.this, "Conectado a API: " + listaEspecies.size() + " especies.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GaleriaActivity.this, "Respuesta servidor: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Especie>> call, Throwable t) {
                Toast.makeText(GaleriaActivity.this, "Modo local activo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}