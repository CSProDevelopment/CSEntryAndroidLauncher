package gov.census.cspro.csentrylauncher

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mEditTextPffFile: EditText
    private lateinit var mEditTextStaffName: EditText
    private val casesViewModel: CSProCasesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEditTextPffFile = findViewById(R.id.editTextPff)
        mEditTextStaffName = findViewById(R.id.editTextStaffName)

        findViewById<Button>(R.id.buttonLaunchViaIntent).setOnClickListener {
            launchCSEntryViaIntent(mEditTextPffFile.text.toString(), mEditTextStaffName.text.toString())
        }

        findViewById<Button>(R.id.buttonLaunchViaDeepLink).setOnClickListener {
            launchCSEntryViaDeepLink(mEditTextPffFile.text.toString(), mEditTextStaffName.text.toString())
        }

        // RecyclerView that displays list of cases from CSPro
        // Cases are added when CSPro calls the GetCSProResultActivity
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CSProCaseListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        casesViewModel.allCases.observe(this) { cases ->
            // Update the cached copy of the words in the adapter.
            cases?.let { adapter.setCases(it) }
        }
    }

    private fun launchCSEntryViaIntent(pffFile: String, staffName: String) {
        try {
            val intent = Intent()
            intent.component = ComponentName("gov.census.cspro.csentry", "gov.census.cspro.csentry.ui.EntryActivity")
            intent.putExtra("PffFilename", pffFile)
            intent.putExtra("STAFF_NAME", staffName)
            intent.putExtra("Key", UUID.randomUUID())
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage(R.string.activity_not_found).show()
        }
    }

    private fun launchCSEntryViaDeepLink(pffFile: String, staffName: String) {
        val uri = Uri.parse("https://csprousers.org/pff")
            .buildUpon()
            .appendPath(pffFile)
            .appendQueryParameter("STAFF_NAME", staffName)
            .appendQueryParameter("Key", UUID.randomUUID().toString())
            .build()

        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
