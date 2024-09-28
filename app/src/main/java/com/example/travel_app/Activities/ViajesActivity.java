package com.example.travel_app.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_app.Adapters.PopularAdapter;
import com.example.travel_app.Domains.PopularDomain;
import com.example.travel_app.R;

import java.util.ArrayList;

public class ViajesActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterPopular, adapterCat;
    private RecyclerView recyclerViewPopular, recyclerViewCategory;
    private ArrayList<PopularDomain> items; // Lista original
    private ArrayList<PopularDomain> filteredItems;  // Lista filtrada
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viajes);

        searchEditText = findViewById(R.id.editTextText);

        // Inicializa las listas antes de usarlas
        items = new ArrayList<>();
        filteredItems = new ArrayList<>();  // Inicialización correcta de filteredItems

        initRecyclerView();
        initSearch();

    }

    private void initRecyclerView() {

        items.add( new PopularDomain("Manos Cruzadas, Kotosh","Huánuco", "Kotosh es un sitio arqueológico ubicado en el distrito, provincia"+" y departamento de Huánuco, en el Perú. Se compone de una"+" serie de edificios superpuestos con 6 periodos de ocupación"+" continua que datan desde el Arcaico Tardío hasta el Intermedio Temprano."+" El más famoso de sus recintos, que se encuentra expuesto actualmente al público.", 2,true,4.3,"pic5",true,120));
        items.add( new PopularDomain("Huanucopampa","Huánuco", "Conocido también como Huánuco Viejo. Importantísimo centro de más de 2 km²"+" ubicado sobre una meseta o pampa de 200 ha de extensión a 3,700 metros de"+" altura que se emplaza sobre el valle del Vizcarra. Fue establecido"+" allí porque marcaba el punto medio del camino entre el Cuzco y Tomebamba,"+" hoy en el sur de Ecuador. Desde entonces la ciudadela ha sido reconocida"+" como La Capital del Chinchaysuyo, una de las cuatro regiones en que estaba"+" dividido políticamente el llamado Imperio de los Incas.Por esta población"+" pasaba el Camino del Inca, en uno de sus ramales", 1,false,4.1,"pic4",false,150));
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
