package org.bedu.options

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.item1 -> {
                isChecked(item)
            }
            R.id.item2 -> {
                isChecked(item)
            }
            R.id.item3 -> {
                isChecked(item)
            }
            R.id.subitem1 ->{
                isChecked(item)
            }
            R.id.subitem2 ->{
                isChecked(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isChecked(item: MenuItem){
        if (item.isChecked) {
            Toast.makeText(this, "Item1 Not Checked", Toast.LENGTH_LONG).show()
            item.setChecked(false)
        } else {
            Toast.makeText(this, "Item1 Checked", Toast.LENGTH_LONG).show()
            item.setChecked(true)
        }
    }
}