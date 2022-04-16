package org.bedu.repasorecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.bedu.repasorecycler.data.Contact

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler)

        val adapter = RecyclerAdapter(
            listOf(
                Contact("Javier", "5511223344"),
                Contact("Juan", "5522334455"),
                Contact("Luis", "5533445566"),
                Contact("Fernanda", "5544556677"),
                Contact("Luisa", "5555667788"),
                Contact("Manuel", "5566778899"),
                Contact("Jose", "5577889900"),
                Contact("Maria", "5588990011"),
                Contact("Ana", "5599001122")
            )
        ) {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            //Aquí se acciona el click
        }

        recycler.adapter = adapter


    }


}