package org.bedu.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.bedu.login.addeduser.AddEditFragment
import org.bedu.login.userlist.UserListFragment

class AddEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        val fragment = AddEditFragment()
        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }
}