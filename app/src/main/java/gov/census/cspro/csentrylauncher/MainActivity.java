package gov.census.cspro.csentrylauncher;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextPffFile;
    private EditText mEditTextCaseId;
    private Spinner mSpinnerMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextPffFile = findViewById(R.id.editTextPff);
        mEditTextCaseId = findViewById(R.id.editTextCaseId);
        mSpinnerMode = findViewById(R.id.spinnerMode);

        // Only enable for modify mode
        mEditTextCaseId.setEnabled(mSpinnerMode.getSelectedItemPosition() == 1);

        mSpinnerMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // Only enable for modify mode
                mEditTextCaseId.setEnabled(pos == 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button buttonLaunch = findViewById(R.id.buttonLaunch);
        buttonLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String pffFile = mEditTextPffFile.getText().toString();
                final boolean addMode = mSpinnerMode.getSelectedItemPosition() == 0;
                final String startCaseId = addMode ? null : mEditTextCaseId.getText().toString();
                launchCSEntry(pffFile, addMode, startCaseId);
            }
        });
    }

    private void launchCSEntry(String pffFile, boolean addMode, String startCaseId) {

        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("gov.census.cspro.csentry", "gov.census.cspro.csentry.ui.EntryActivity"));
            if (addMode) {
                intent.putExtra("StartMode", "add");
            } else {
                intent.putExtra("Key", startCaseId);
            }
            intent.putExtra("PffFilename", pffFile);
            intent.putExtra("OperatorID", "josh");
            startActivity(intent);
        } catch (ActivityNotFoundException e)
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(R.string.activity_not_found).show();
        }
    }
}
