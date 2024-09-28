package com.example.travel_app.Activities;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.travel_app.R;

public class FacturacionActivity extends AppCompatActivity {

    private EditText etTitle, etLocation, etDescription, etBed;
    private TextView tvScore, tvGuide, tvWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturacion);

        // Enlazar vistas del XML
        etTitle = findViewById(R.id.etTitle);
        etLocation = findViewById(R.id.etLocation);
        etDescription = findViewById(R.id.etDescription);
        etBed = findViewById(R.id.etBed);
        tvScore = findViewById(R.id.tvScore);
        tvGuide = findViewById(R.id.tvGuide);
        tvWifi = findViewById(R.id.tvWifi);

        // Obtener los datos del Intent
        String title = getIntent().getStringExtra("title");
        String location = getIntent().getStringExtra("location");
        String description = getIntent().getStringExtra("description");
        int bed = getIntent().getIntExtra("bed", 0);
        double score = getIntent().getDoubleExtra("score", 0.0);
        boolean guide = getIntent().getBooleanExtra("guide", false);
        boolean wifi = getIntent().getBooleanExtra("wifi", false);

        // Rellenar los campos automáticamente
        etTitle.setText(title);
        etLocation.setText(location);
        etDescription.setText(description);
        etBed.setText(String.valueOf(bed));
        tvScore.setText("Puntuación: " + score);
        tvGuide.setText(guide ? "Guía disponible" : "Sin Guía");
        tvWifi.setText(wifi ? "Wifi disponible" : "Sin Wifi");
    }
}
