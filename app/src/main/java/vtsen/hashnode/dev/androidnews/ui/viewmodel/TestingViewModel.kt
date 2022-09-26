package vtsen.hashnode.dev.androidnews.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class TestingViewModel: ViewModel() {
    init {
        Log.d("test", "TestingViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()

        Log.d("test", "TestingViewModel destroyed!")
    }
}