package com.itech.multimoduleapp.navigation

import android.os.Bundle
import com.itech.common_utils.Activities
import com.itech.common_utils.Navigator
import com.itech.news_presentation.gotoNewsActivity
import com.itech.search_presentation.gotoSearchActivity

class DefaultNavigator : Navigator.Provider {

    override fun getActivities(activities: Activities, bundle: Bundle?): Navigator {
        return when (activities) {
            Activities.NewsActivity -> {
                gotoNewsActivity(bundle)
            }
            Activities.SearchActivity -> {
                gotoSearchActivity(bundle)
            }
        }
    }


}
