package org.bedu.recyclercontacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_superhero.view.*


class HeroAdapter(
    val superhero: List<Superhero>
    ):
    RecyclerView.Adapter<HeroAdapter.HeroHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroHolder(layoutInflater.inflate(R.layout.item_superhero,parent,false))
    }

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        holder.reder(superhero[position])
    }

    override fun getItemCount(): Int {
        return superhero.size
    }

    class HeroHolder(val view: View):RecyclerView.ViewHolder(view) {
        fun reder(superhero: Superhero){
            view.tvRealName.text = superhero.realName
            view.tvSuperHeroName.text = superhero.superHeroName
            view.tvPublisher.text = superhero.publisher
            view.tvRealName.text = superhero.realName
            Picasso.get().load(superhero.image).into(view.ivHero)

            view.setOnClickListener {
                Toast.makeText(view.context,"Hola ${superhero.realName}",Toast.LENGTH_SHORT).show()
            }

            view.ivHero.setOnClickListener{
                Toast.makeText(view.context, "Foto de ${superhero.superHeroName}",Toast.LENGTH_SHORT).show()
            }
        }
    }
}