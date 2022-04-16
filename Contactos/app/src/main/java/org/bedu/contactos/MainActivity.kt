package org.bedu.contactos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentList) as ContactsFragment
        listFragment.setListener {
            val detailFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentDetail) as? DetailFragment

            // Pantalla grande, mostrar detalle en el fragment
            if (detailFragment != null) {
                detailFragment.showContact(it)
            } else { //pantalla pequeña, navegar a un nuevo Activity
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.CONTACT, it)
                startActivity(intent)
            }
        }
    }
}