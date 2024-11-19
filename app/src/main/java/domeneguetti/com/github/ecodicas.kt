package domeneguetti.com.github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

data class Dica(
    val titulo: String,
    val descricao: String
)

class DicaAdapter(
    private val dicas: List<Dica>
) : RecyclerView.Adapter<DicaAdapter.DicaViewHolder>() {

    class DicaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DicaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return DicaViewHolder(view)
    }

    override fun onBindViewHolder(holder: DicaViewHolder, position: Int) {
        val dica = dicas[position]
        holder.textTitle.text = dica.titulo
        holder.textDescription.text = dica.descricao

        holder.itemView.setOnClickListener {
            Toast.makeText(
                it.context,
                "${dica.titulo}: ${dica.descricao}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = dicas.size
}
