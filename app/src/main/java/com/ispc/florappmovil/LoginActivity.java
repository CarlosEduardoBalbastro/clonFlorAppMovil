package com.ispc.florappmovil;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    // 1. Declaración de variables para los componentes visuales
    private EditText etUsuario;
    private EditText etPassword;
    private Button btnLogin;
    private android.widget.TextView tvIrARegistro;
    private TextView tvInvitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 2. Vinculación: Buscamos los componentes XML por su ID
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvIrARegistro = findViewById(R.id.tvIrARegistro);
        tvInvitado = findViewById(R.id.tvInvitado);

        btnLogin.setOnClickListener(v -> ejecutarLogin());


        tvIrARegistro.setOnClickListener( v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        tvInvitado.setOnClickListener( v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void ejecutarLogin(){
        String email = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor completá todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        // Crear la petición con el modelo LoginRequest
        LoginRequest loginRequest = new LoginRequest(email, password);

        // Petición HTTP a Django vía Retrofit
        ApiClient.getApiService().login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    String accessToken = response.body().getAccess();

                    SharedPreferences preferences = getSharedPreferences("AppSession", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token", accessToken);

                    if (loginResponse.getUser() != null) {

                        editor.putInt("user_rol", loginResponse.getUser().getRol());
                        editor.putString("user_nombre", loginResponse.getUser().getNombre());
                        editor.putString("user_email", loginResponse.getUser().getEmail());

                    }
                    editor.apply();


                    Toast.makeText(LoginActivity.this, "¡Bienvenida/o !", Toast.LENGTH_SHORT).show();

                    // Navegar hacia ProfileActivity pasando datos del usuario
                    Intent intent = new Intent(LoginActivity.this, GaleriaActivity.class);
                    intent.putExtra("TOKEN", accessToken);
                    intent.putExtra("EMAIL", email);
                    startActivity(intent);
                    finish(); // Cerrar LoginActivity
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}