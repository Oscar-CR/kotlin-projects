package org.bedu.appcomponents

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReceiverOne: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        /*
        val bundle = intent.extras
        val name = bundle?.getString(MainActivity.NAME)
        val email = bundle?.getString(MainActivity.EMAIL)
        Toast.makeText(context,"$name $email",Toast.LENGTH_SHORT).show()
         */
        ToastCoroutine(intent.extras,context).run {
            execute()
        }
    }


    private inner class ToastCoroutine(
        private val bundle: Bundle?,
        private val context: Context
        ) : ViewModel() {

        fun execute()= viewModelScope.launch{
            doInBackground()
            onPostExecute()
        }

        private suspend fun doInBackground(): String = withContext(Dispatchers.IO) {
            val name = bundle?.getString(MainActivity.NAME)
            val email = bundle?.getString(MainActivity.EMAIL)

            return@withContext "Resultado"
        }

        private fun onPostExecute(){
            val name = bundle?.getString("NAME")
            val email = bundle?.getString("EMAIL")
            Toast.makeText(context,"$name $email",Toast.LENGTH_SHORT).show()

            Log.d("Broadcast", " Thread de post execute: ${Thread.currentThread()}")
        }
    }
}