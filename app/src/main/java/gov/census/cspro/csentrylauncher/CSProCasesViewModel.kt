package gov.census.cspro.csentrylauncher

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CSProCasesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CSProCaseRepository

    val allCases: LiveData<List<CSProCase>>

    init {

        val dao = CSProCaseRoomDatabase.getDatabase(application).dao()
        repository = CSProCaseRepository(dao)
        allCases = repository.allCases
    }

    fun insert(c: CSProCase) = viewModelScope.launch {
        repository.insert(c)
    }
}