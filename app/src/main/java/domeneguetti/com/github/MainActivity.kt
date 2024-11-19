package domeneguetti.com.github

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import domeneguetti.com.github.adapter.DicaAdapter
import domeneguetti.com.github.database.DatabaseHelper
import domeneguetti.com.github.model.Dica

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dicaAdapter: DicaAdapter
    private lateinit var searchView: SearchView
    private lateinit var databaseHelper: DatabaseHelper
    private var dicasList = mutableListOf<Dica>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar componentes
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        databaseHelper = DatabaseHelper(this)

        // Carregar dados do banco SQLite
        dicasList = databaseHelper.getAllDicas()

        // Configurar RecyclerView
        dicaAdapter = DicaAdapter(dicasList) { dica ->
            Toast.makeText(this, "Detalhes: ${dica.descricao}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = dicaAdapter

        // Configurar filtro de pesquisa
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    dicaAdapter.filter(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    dicaAdapter.filter(it)
                }
                return false
            }
        })
    }
}
