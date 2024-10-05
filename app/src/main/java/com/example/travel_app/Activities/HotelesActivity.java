package com.example.travel_app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_app.Adapters.CategoryAdapter;
import com.example.travel_app.Adapters.PopularAdapter;
import com.example.travel_app.Domains.CategoryDomain;
import com.example.travel_app.Domains.PopularDomain;
import com.example.travel_app.R;

import java.util.ArrayList;

public class HotelesActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterPopular, adapterCat;
    private RecyclerView recyclerViewPopular, recyclerViewCategory;
    private ArrayList<PopularDomain> items; // Lista original
    private ArrayList<PopularDomain> filteredItems;  // Lista filtrada
    private EditText searchEditText;

    private TextView tvNombreModificado, tvNombreSecundario;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "user_profile_prefs";

    private ImageView btnMainActivity, btnViajesActivity, btnHotelesActivity, btnRestauranteActivity, btnFacturacionActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hoteles);
        // Inicializa el buscador
        searchEditText = findViewById(R.id.editTextText);
        ImageView profileImageView = findViewById(R.id.imageView3);

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
                Intent intent = new Intent(HotelesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        profileImageView.setOnClickListener(v -> {
            Intent intent = new Intent(HotelesActivity.this, ConfigurarPerfilActivity.class);
            startActivity(intent);
        });

        tvNombreSecundario = findViewById(R.id.textView5);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Cargar y mostrar el nombre
        cargarNombre();

        btnViajesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelesActivity.this, ViajesActivity.class);
                startActivity(intent);
            }
        });

        btnHotelesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelesActivity.this, HotelesActivity.class);
                startActivity(intent);
            }
        });

        btnRestauranteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelesActivity.this, RestauranteActivity.class);
                startActivity(intent);
            }
        });

        btnFacturacionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelesActivity.this, FacturacionActivity.class);
                startActivity(intent);
            }
        });

    }


    private void cargarNombre() {
        String nombre = sharedPreferences.getString("nombre", "Invitado");

        // Establecer el nombre en textView5
        tvNombreSecundario.setText(nombre);
    }

    private void initRecyclerView() {
        items.add( new PopularDomain("Grand Hotel, Huanuco","Huánuco", "Disfruta tu estadía\n" +
                "Grand Hotel Huánuco, formamos parte del rubro hotelero Inka Comfort Hoteles del Perú en la ciudad del mejor clima del mundo Huánuco.\n" +
                "\n" +
                "Durante tus viajes o vacaciones Grand Hotel Huánuco brinda un servicio Business Class para clientes exigentes.", 2,true,4.7,"pic6",true,80));
        items.add( new PopularDomain("Monte prado, Tingo Mara","Huánuco", "Hotel - Monte Prado Club Campestre espera a sus huéspedes por la dirección de Tingo María S/N Maria Parado de Bellido - Castillo Grande de Tingo María a la distancia de 18 minutos del centro. Este es apropiado por completo para vacaciones de lujo, descanso fuera de la ciudad y vacaciones.", 3,false,3.8,"pic7",false,70));
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