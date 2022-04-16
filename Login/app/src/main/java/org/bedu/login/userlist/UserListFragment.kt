package org.bedu.login.userlist

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ListenerUtil.getListener
import androidx.lifecycle.Observer
import org.bedu.login.AddEditActivity
import org.bedu.login.R
import org.bedu.login.UserApplication
import org.bedu.login.adapter.UserAdapter
import org.bedu.login.databinding.FragmentUserListBinding
import org.bedu.login.models.ItemListener
import org.bedu.login.models.User
import org.bedu.login.models.UserDB
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserListFragment : Fragment() {

    private lateinit var adapter: UserAdapter
    private lateinit var binding: FragmentUserListBinding
    private lateinit var viewModel: UserListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_list,
            container,
            false
        )

        viewModel = UserListViewModel(
            (requireContext().applicationContext as UserApplication).userRepository
        )

        binding.lifecycleOwner = this
        binding.userListViewModel = viewModel

        //setupEditVehicle()

        setupVehicleList()


        binding.buttonAdd.setOnClickListener {
            val intent= Intent(context, AddEditActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    private fun getListener(): UserListFragment {
        return this

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    private fun setupVehicleList(){
        if(viewModel!=null){
            adapter = UserAdapter(viewModel)
            binding.list.adapter = adapter

            viewModel.userList.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.submitList(it)
                }
            })

        }
    }

    /*
    private fun setupEditVehicle(){
        if(viewModel!=null){

            with(viewModel){

                eventEditUser.observe(viewLifecycleOwner, Observer {
                    if(eventEditUser.value!=null){
                        findNavController().navigate(
                            R.id.action_vehicleListFragment_to_addEditFragment,
                            bundleOf("vehicle_id" to eventEditUser.value!!)
                        )
                        eventEditUser.value = null
                    }
                })
            }
        }

    }

     */


}