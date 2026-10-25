package com.ispc.florappmovil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvVolver = findViewById(R.id.tvVolverGaleria);
        Button btnCerrar = findViewById(R.id.btnCerrar);
        Button btnModificar = findViewById(R.id.btnModificar);

        tvVolver.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, GaleriaActivity.class);
            startActivity(intent);
            finish();
        });

        btnModificar.setOnClickListener(v -> {
            Toast.makeText(ProfileActivity.this, "Usuario actualizado con éxito.", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnCerrar.setOnClickListener(v -> {
            // Limpiamos la sesión guardada al salir
            SharedPreferences preferences = getSharedPreferences("AppSession", MODE_PRIVATE);
            preferences.edit().clear().apply();

            Intent intent = new Intent(ProfileActivity.this, IngresoActivity.class);
            startActivity(intent);
            finish();
        });
    }
}