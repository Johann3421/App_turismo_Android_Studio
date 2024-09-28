package com.example.travel_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.travel_app.Domains.PopularDomain;
import com.example.travel_app.R;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTxt, locationTxt, bedTxt, guiaTxt, wifiTxt, descripcionTxt, scoreTxt;
    private PopularDomain item;
    private ImageView picImg, backBtn;
    private Button btnReservarAhora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        initView();
        setVariable();

        // Configurar el botón "Reservar Ahora"
        btnReservarAhora.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, FacturacionActivity.class);

            // Pasar los datos a la actividad de facturación
            intent.putExtra("title", item.getTitle());
            intent.putExtra("location", item.getLocation());
            intent.putExtra("score", item.getScore());
            intent.putExtra("bed", item.getBed());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("guide", item.isGuide());
            intent.putExtra("wifi", item.isWifi());
            intent.putExtra("pic", item.getPic());

            startActivity(intent);
        });
    }

    private void setVariable() {
        item = (PopularDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        scoreTxt.setText(String.valueOf(item.getScore()));
        locationTxt.setText(item.getLocation());
        bedTxt.setText(item.getBed() + " Bed");
        descripcionTxt.setText(item.getDescription());

        guiaTxt.setText(item.isGuide() ? "Guía" : "Sin Guía");
        wifiTxt.setText(item.isWifi() ? "Wifi" : "Sin Wifi");

        int drawableResId = getResources().getIdentifier(item.getPic(), "drawable", getPackageName());
        Glide.with(this)
                .load(drawableResId)
                .into(picImg);

        backBtn.setOnClickListener(v -> finish());
    }

    private void initView() {
        titleTxt = findViewById(R.id.titleTxt);
        locationTxt = findViewById(R.id.locationTxt);
        bedTxt = findViewById(R.id.bedTxt);
        guiaTxt = findViewById(R.id.guiaTxt);
        wifiTxt = findViewById(R.id.wifiTxt);
        descripcionTxt = findViewById(R.id.descripcionTxt);
        scoreTxt = findViewById(R.id.scoreTxt);
        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backBtn);
        btnReservarAhora = findViewById(R.id.button);
    }
}
