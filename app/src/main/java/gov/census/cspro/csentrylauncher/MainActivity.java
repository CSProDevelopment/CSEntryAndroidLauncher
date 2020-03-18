package gov.census.cspro.csentrylauncher;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextPffFile;
    private EditText mEditTextStaffName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextPffFile = findViewById(R.id.editTextPff);
        mEditTextStaffName = findViewById(R.id.editTextStaffName);

        Button buttonLaunch = findViewById(R.id.buttonLaunch);
        buttonLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String pffFile = mEditTextPffFile.getText().toString();
                final String staffName = mEditTextStaffName.getText().toString();
                launchCSEntry(pffFile, staffName);
            }
        });
    }

    private void launchCSEntry(String pffFile, String staffName) {

        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("gov.census.cspro.csentry", "gov.census.cspro.csentry.ui.EntryActivity"));
            intent.putExtra("PFF_FILENAME", pffFile);
            intent.putExtra("STAFF_NAME", staffName);
            intent.putExtra("Key", UUID.randomUUID());
            startActivity(intent);
        }

        catch (ActivityNotFoundException e) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(R.string.activity_not_found).show();
        }
    }
}
