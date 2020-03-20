package gov.census.cspro.csentrylauncher

import androidx.lifecycle.LiveData

class CSProCaseRepository(private val dao: CSProCaseDao) {

    val allCases: LiveData<List<CSProCase>> = dao.getCases()

    suspend fun insert(c: CSProCase) {
        dao.insert(c)
    }
}

