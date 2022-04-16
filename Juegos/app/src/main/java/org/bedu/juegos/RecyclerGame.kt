package org.bedu.juegos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RecyclerGame(val game: List<Game>): RecyclerView.Adapter<RecyclerGame.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerGame.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerGame.ViewHolder, position: Int) {
        val game = game[position]
        holder.bind(game)
    }

    override fun getItemCount(): Int {
        return game.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val nombre: TextView = view.findViewById(R.id.tvTitulo)
        private val descripcion: TextView = view.findViewById(R.id.tvCategoria)
        private val clasificacion: TextView =  view.findViewById(R.id.tvClasificacion)
        private val rating: RatingBar =  view.findViewById(R.id.rbCalificacion)
        private val portada: ImageView = view.findViewById(R.id.imgPortada)

        fun bind(game: Game){
            nombre.text= game.name
            descripcion.text= game.descripcion
            clasificacion.text= game.genero
            rating.rating=game.rating
            portada.setImageResource()

        }
    }


}