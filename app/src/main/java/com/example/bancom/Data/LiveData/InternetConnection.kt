package com.example.bancom.Data.LiveData

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import androidx.lifecycle.LiveData

class InternetConnection(private val PContext: Context): LiveData<Boolean>()
{
    private var ConnectivityManagerObject: ConnectivityManager =
        PContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var NetworkCallbackObject: ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        UpdateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ->
                ConnectivityManagerObject.
                registerDefaultNetworkCallback(GetConnectivityManagerCallbackObject())
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ->
                LollipopNetworkRequest()
            else -> PContext.registerReceiver(NetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    override fun onInactive() {
        super.onInactive()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            ConnectivityManagerObject.unregisterNetworkCallback(NetworkCallbackObject)
        else
            PContext.unregisterReceiver(NetworkReceiver)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun LollipopNetworkRequest() {
        val RequestBuilderObject = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        ConnectivityManagerObject.registerNetworkCallback(RequestBuilderObject.build(),
            GetConnectivityManagerCallbackObject())
    }

    private fun GetConnectivityManagerCallbackObject(): ConnectivityManager.NetworkCallback {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkCallbackObject = object: ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true)
                }
            }
            return NetworkCallbackObject
        }
        else throw IllegalAccessError("Error")
    }

    private val NetworkReceiver = object: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            UpdateConnection()
        }
    }

    private fun UpdateConnection() {
        val ActiveNetwork: NetworkInfo? = ConnectivityManagerObject.activeNetworkInfo
        postValue(ActiveNetwork?.isConnected == true)
    }
}