package org.bedu.repasorecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.bedu.repasorecycler.data.Contact

class RecyclerAdapter(val contacts : List<Contact>, private val mListener: (Contact) -> Unit) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val contact = contacts[position]
        //holder.bind(contact)
        holder.itemView.setOnClickListener { contact.let { it1 -> mListener.invoke(it1) } }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val name = view.findViewById<TextView>(R.id.tvNombre)
        private val phone = view.findViewById<TextView>(R.id.tvPhone)

        fun bind(contact: Contact){
            name.text = contact.name
            phone.text = contact.phone
        }
    }

}
