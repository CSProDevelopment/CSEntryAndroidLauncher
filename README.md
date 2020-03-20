# CSEntryAndroidLauncher

This is an example of starting CSEntry from another Android application. To do this use, an Intent with the class *gov.census.cspro.csentry.ui.EntryActivity*:

```
Intent intent = new Intent();
intent.setComponent(new ComponentName("gov.census.cspro.csentry", "gov.census.cspro.csentry.ui.EntryActivity"));
```

You must specify the name of the PFF file to launch as an extra in the Intent:

```
intent.putExtra("PffFilename", "CSEntry Application.pff");
```

If the PFF is a fully evaluated path, that file will be opened. If not, CSEntry will look for a file with that name in the *csentry* directory.

To pass additional parameters that will get added to the PFF, add them as extras. For example, to set the operator ID and the case key:

```
intent.putExtra("OperatorID", "John Doe");
intent.putExtra("Key", "01050669557");
```

Finally launch the activity using the Intent:

```
startActivity(intent);
```
