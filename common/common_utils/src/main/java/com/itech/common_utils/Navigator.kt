package com.itech.common_utils

import android.app.Activity
import android.os.Bundle

interface Navigator {
    //a9dbf145baf64f0c82763cee9a049d05
    fun navigate(activity: Activity)


    interface Provider {
        fun getActivities(activities: Activities, bundle: Bundle?): Navigator
    }

}