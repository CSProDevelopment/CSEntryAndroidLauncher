# CSEntryAndroidLauncher
Example of starting the CSEntry application from another Android application

You can launch the CSEntry Android application from another Android application and have it start directly in either add or modify mode. To do this use an Intent with class *gov.census.cspro.csentry.ui.EntryActivity*. 

```
Intent intent = new Intent();
intent.setComponent(new ComponentName("gov.census.cspro.csentry", "gov.census.cspro.csentry.ui.EntryActivity"));
```

You must specify the path to the pff file to launch as an extra in the Intent:

```
intent.putExtra("PFF_FILENAME", ""/mnt/sdcard/csentry/Simple CAPI/Simple CAPI.pff");
```

Finally launch the activity using the Intent:

```
startActivity(intent);
```

You can use the following optional extras on the intent:

| NAME | VALUE |
| --- | --- |
| NEWCASE | set to "REQUEST_ADD" to add a new case or "REQUEST_MODIFY" to modify an existing case |
| CASEID | set to the key of the case to launch when NEWCASE is set to "REQUEST_MODIFY" |
| OPERATOR_ID | set to the operator id to use for pardata logs and the getoperatorid() function in CSPro logic |

Note that the above extras ca also be specified in the pff file itself using the StartMode, Key and OperatorID parameters.
