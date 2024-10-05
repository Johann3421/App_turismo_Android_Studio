package com.example.travel_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.travel_app.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_app.R;

public class ConfigurarPerfilActivity extends AppCompatActivity {

    private TextView tvNombre, tvUbicacion, tvProfesion, tvCompania, tvTelefono;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "user_profile_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_perfil);

        // Inicializar las vistas
        tvNombre = findViewById(R.id.textView);
        tvUbicacion = findViewById(R.id.textView2);
        tvProfesion = findViewById(R.id.textView7);
        tvCompania = findViewById(R.id.textView9);
        tvTelefono = findViewById(R.id.textView11);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Cargar los datos
        cargarDatosPerfil();

        // Referencia al ImageButton para editar perfil
        ImageButton editProfileButton = findViewById(R.id.imageButton5);

        // Configurar el clic en el botón para abrir la actividad de edición
        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(ConfigurarPerfilActivity.this, EditarPerfilActivity.class);
            startActivity(intent);
        });
    }
    private void cargarDatosPerfil() {
        tvNombre.setText(sharedPreferences.getString("nombre", "Nombre no configurado"));
        tvUbicacion.setText(sharedPreferences.getString("ubicacion", "Ubicación no configurada"));
        tvProfesion.setText(sharedPreferences.getString("profesion", "Profesión no configurada"));
        tvCompania.setText(sharedPreferences.getString("compania", "Compañía no configurada"));
        tvTelefono.setText(sharedPreferences.getString("telefono", "Teléfono no configurado"));
    }
}