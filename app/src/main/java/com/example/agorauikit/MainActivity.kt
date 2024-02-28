package com.example.agorauikit


import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import io.agora.agorauikit_android.*
import io.agora.rtc2.Constants.CLIENT_ROLE_BROADCASTER


class MainActivity : AppCompatActivity() {


    // Object of AgoraVideoVIewer class
    private var agView: AgoraVideoViewer? = null

    // Fill the App ID of your project generated on Agora Console.
    private val appId = "9a5979abb0444af8842a8d1c5eea8e2c"

    // Fill the channel name.
    private val channelName = "aryan"

    // Fill the temp token generated on Agora Console.
    private val token = "007eJxTYMiYzpvzWeFlX86Of+2Z4q+EL7YxiX6v6/l469xlnpnf/zxTYLBMNLU0t0xMSjIwMTFJTLOwMDFKtEgxTDZNTU20SDVKfs13P7UhkJFB980ZVkYGCATxWRkSiyoT8xgYAGTAI9o="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeAndJoinChannel();



    }

    fun joinChannel() {
        agView?.join(channelName, token, CLIENT_ROLE_BROADCASTER, 0)
    }

    private fun initializeAndJoinChannel() {
        // Create AgoraVideoViewer instance
        try {
            agView = AgoraVideoViewer(
                this,
                AgoraConnectionData(appId, token),
                AgoraVideoViewer.Style.FLOATING,
                AgoraSettings(),
                null
            )
        } catch (e: Exception) {
            Log.e(
                "AgoraVideoViewer",
                "Could not initialize AgoraVideoViewer. Check that your app Id is valid."
            )
            Log.e("Exception", e.toString())
            return
        }

        // Add the AgoraVideoViewer to the Activity layout
        addContentView(
            agView, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        // Check permission and join a channel
       // if (DevicePermissionsKt.requestPermissions(AgoraVideoViewer.Companion, this)) {
            joinChannel()
        //} else {
            val joinButton = Button(this)
            joinButton.text = "Allow camera and microphone access, then click here"
            joinButton.setOnClickListener {
               /* if (DevicePermissionsKt.requestPermissions(
                        AgoraVideoViewer.Companion,
                        applicationContext
                    )
                ) {*/
                    (joinButton.parent as ViewGroup).removeView(joinButton)
                    joinChannel()
 //               }
            }
            addContentView(
                joinButton,
                FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200)
            )
        }



    }


