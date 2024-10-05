package com.example.travel_app.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_app.R;

public class EditarPerfilActivity extends AppCompatActivity {

    private EditText etNombre, etUbicacion, etProfesion, etCompania, etTelefono;
    private Button btnGuardar;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "user_profile_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        // Inicializar las vistas
        etNombre = findViewById(R.id.et_nombre);
        etUbicacion = findViewById(R.id.et_ubicacion);
        etProfesion = findViewById(R.id.et_profesion);
        etCompania = findViewById(R.id.et_compania);
        etTelefono = findViewById(R.id.et_telefono);
        btnGuardar = findViewById(R.id.btn_guardar);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Cargar datos actuales si existen
        cargarDatosPerfil();

        // Guardar los datos al hacer clic en el botón
        btnGuardar.setOnClickListener(v -> guardarDatosPerfil());
    }

    private void cargarDatosPerfil() {
        etNombre.setText(sharedPreferences.getString("nombre", ""));
        etUbicacion.setText(sharedPreferences.getString("ubicacion", ""));
        etProfesion.setText(sharedPreferences.getString("profesion", ""));
        etCompania.setText(sharedPreferences.getString("compania", ""));
        etTelefono.setText(sharedPreferences.getString("telefono", ""));
    }

    private void guardarDatosPerfil() {
        String nombre = etNombre.getText().toString();
        String ubicacion = etUbicacion.getText().toString();
        String profesion = etProfesion.getText().toString();
        String compania = etCompania.getText().toString();
        String telefono = etTelefono.getText().toString();

        // Validar que los campos no estén vacíos
        if (nombre.isEmpty() || ubicacion.isEmpty() || profesion.isEmpty() || compania.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar los datos en SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombre", nombre);
        editor.putString("ubicacion", ubicacion);
        editor.putString("profesion", profesion);
        editor.putString("compania", compania);
        editor.putString("telefono", telefono);
        editor.apply();

        Toast.makeText(this, "Perfil guardado correctamente", Toast.LENGTH_SHORT).show();
    }
}
