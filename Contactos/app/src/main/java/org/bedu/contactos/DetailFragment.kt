package org.bedu.contactos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment


class DetailFragment: Fragment() {
    private lateinit var imgPorfileDetail: ImageView
    private lateinit var etUserDetail: EditText
    private lateinit var etPhoneDetail: EditText


    // Traemos todas las views desde el layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        imgPorfileDetail = view.findViewById(R.id.imgPorfileDetail)
        etUserDetail = view.findViewById(R.id.etUserDetail)
        etPhoneDetail = view.findViewById(R.id.etPhoneDetail)

        return view
    }

    fun showContact(contact: Contacts) {
        etUserDetail.hint = contact.name
        etPhoneDetail.hint = contact.phone
        imgPorfileDetail.setImageResource(contact.photo)
    }

  
}
