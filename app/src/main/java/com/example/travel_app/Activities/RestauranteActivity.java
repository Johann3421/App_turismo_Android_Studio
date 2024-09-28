package com.example.travel_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_app.Adapters.PopularAdapter;
import com.example.travel_app.Domains.PopularDomain;
import com.example.travel_app.R;

import java.util.ArrayList;

public class RestauranteActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterPopular, adapterCat;
    private RecyclerView recyclerViewPopular, recyclerViewCategory;
    private ArrayList<PopularDomain> items; // Lista original
    private ArrayList<PopularDomain> filteredItems;  // Lista filtrada
    private EditText searchEditText;

    private ImageView btnMainActivity, btnViajesActivity, btnHotelesActivity, btnRestauranteActivity, btnFacturacionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurante);

        searchEditText = findViewById(R.id.editTextText);

        // Inicializa las listas antes de usarlas
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();  // Inicialización correcta de filteredItems

        initRecyclerView();
        initSearch();
        btnMainActivity = findViewById(R.id.imageView12);
        btnViajesActivity = findViewById(R.id.imageView13);
        btnHotelesActivity = findViewById(R.id.imageView14);
        btnRestauranteActivity = findViewById(R.id.imageView15);
        btnFacturacionActivity = findViewById(R.id.imageView16);

        // Asignamos los onClickListeners para redirigir a las actividades correspondientes
        btnMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestauranteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnViajesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestauranteActivity.this, ViajesActivity.class);
                startActivity(intent);
            }
        });

        btnHotelesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestauranteActivity.this, HotelesActivity.class);
                startActivity(intent);
            }
        });

        btnRestauranteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestauranteActivity.this, RestauranteActivity.class);
                startActivity(intent);
            }
        });

        btnFacturacionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestauranteActivity.this, FacturacionActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initRecyclerView() {
        items.add(new PopularDomain("Botanika Food Park, Huanuco", "Huánuco", "Ven a descansar tras ese largo paseo por Plaza de Armas. Las comidas caribeña y peruana son buenas en este establecimiento. En este restaurante, sus clientes pueden probar una sorprendente ginebra.\n" +
                "\n" +
                "Comprueba por ti mismo lo bien preparado que es su personal. Un espectacular servicio es otro plus importante. Su singular atmósfera te espera en BOTANIKA FOOD PARK. La puntuación global de este lugar en Google es de 4,5.", 0, false, 4.5, "pic8", false, 20));
        items.add(new PopularDomain("Trapiche House", "Huánuco", "Desde el 2017, en Trapiche House nos inspiramos en la tradicional gastronomía huanuqueña, la cual nos ha permitido ofrecer a nuestra clientela, los más exquisitos sabores hechos artesanalmente en casa.", 1, false, 4.4, "pic9", false, 20));
        filteredItems.addAll(items); // Copiamos los items a filteredItems

        // Configura el RecyclerView para los elementos populares
        recyclerViewPopular = findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterPopular = new PopularAdapter(filteredItems);
        recyclerViewPopular.setAdapter(adapterPopular);
    }

    private void initSearch() {
        // Agrega un TextWatcher al EditText para detectar cambios en el texto
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se necesita implementar
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Llama a la función de filtrado cuando el texto cambie
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No se necesita implementar
            }
        });
    }

    private void filter(String text) {
        // Verifica que las listas no sean nulas antes de proceder
        if (items == null || filteredItems == null) {
            return; // Si alguna de las listas es nula, no hacer nada
        }

        // Limpia la lista filtrada
        filteredItems.clear();

        // Si el texto está vacío, muestra todos los elementos
        if (text.isEmpty()) {
            filteredItems.addAll(items);
        } else {
            // De lo contrario, filtra los elementos que coincidan con el texto
            for (PopularDomain item : items) {
                // Asegúrate de que la comparación ignore mayúsculas y minúsculas
                if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    filteredItems.add(item);
                }
            }
        }

        // Notifica al adaptador que los datos han cambiado
        adapterPopular.notifyDataSetChanged();
    }
}