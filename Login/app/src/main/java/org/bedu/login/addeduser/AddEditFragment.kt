package org.bedu.login.addeduser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import org.bedu.login.R
import org.bedu.login.UserApplication
import org.bedu.login.databinding.FragmentAddEditBinding

class AddEditFragment : Fragment() {

    private lateinit var viewModel: AddEditViewModel
    private lateinit var binding: FragmentAddEditBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_edit,
            container,
            false
        )

        viewModel = AddEditViewModel(
            (requireContext().applicationContext as UserApplication).userRepository
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.buttonAddCar.setOnClickListener{
            viewModel.newUser()
        }

        //setupNavigation()

        return binding.root
    }

    /*


    fun setupNavigation(){
        viewModel.userDone.observe(viewLifecycleOwner, Observer {
            if(it){
                findNavController().navigate(
                    R.id.action_addEditFragment_to_vehicleListFragment
                )
            }
        })
    }

    */

}