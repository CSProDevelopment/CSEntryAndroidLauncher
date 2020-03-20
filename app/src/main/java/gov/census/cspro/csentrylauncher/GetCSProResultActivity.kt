package gov.census.cspro.csentrylauncher

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity that handles callback from CSPro
 *
 * CSPro calls this activity and passes it an intent with
 * extras that are filled in CSPro logic.
 *
 * This is the CSPro logic that calls this activity:
 * 	SystemApp android_launcher_callback;
 *      android_launcher_callback.setArgument("KEY", ID);
 *      android_launcher_callback.setArgument("NUMERIC_VALUE", NUMERIC_VALUE);
 *      android_launcher_callback.setArgument("STRING_VALUE", strip(STRING_VALUE));
 *      android_launcher_callback.exec("gov.census.cspro.csentrylauncher", "gov.census.cspro.csentrylauncher.GetCSProResultActivity");
 *
 *  The activity simply stores the data received in the intent in the database and finishes to
 *  return to CSPro.
 */
class GetCSProResultActivity : AppCompatActivity() {
    private val casesViewModel: CSProCasesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retreive data passed from CSPro
        val key = intent.getStringExtra("KEY")
        val sv = intent.getStringExtra("STRING_VALUE")
        val nv = intent.getDoubleExtra("NUMERIC_VALUE", 0.0)

        // Save in database
        casesViewModel.insert(CSProCase(key, nv, sv))

        // End activity to go back to CSPro
        finish()
    }
}
