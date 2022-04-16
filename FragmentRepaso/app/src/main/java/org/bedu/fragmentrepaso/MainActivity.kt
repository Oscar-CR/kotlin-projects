package org.bedu.fragmentrepaso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.bedu.fragmentrepaso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragment= BeduFragment()
        val fragment2= BetoFragment()

        val manager = supportFragmentManager

        binding.addButton.setOnClickListener {
            manager.beginTransaction().apply {
                add(R.id.container, fragment,"fragbedu")
                commit()
            }

        }

        binding.removeButton.setOnClickListener {
            manager.beginTransaction().apply {
                add(R.id.container, fragment2,"fragbeto")
                commit()
            }
        }

    }
}