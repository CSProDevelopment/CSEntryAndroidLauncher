# CSEntryAndroidLauncher
Example of starting the CSEntry application from another Android application

You can launch the CSEntry Android application from another Android application and have it start directly in either add or modify mode. To do this use an Intent with class *gov.census.cspro.csentry.ui.EntryActivity*. 

```
Intent intent = new Intent();
intent.setComponent(new ComponentName("gov.census.cspro.csentry", "gov.census.cspro.csentry.ui.EntryActivity"));
```

You must specify the path to the pff file to launch as an extra in the Intent:

```
intent.putExtra("PffFilename", ""/mnt/sdcard/csentry/Simple CAPI/Simple CAPI.pff");
```

Finally launch the activity using the Intent:

```
startActivity(intent);
```

You can add optional extras on the intent which will overwrite the corresponding values in the pff file. This allows you to launch a particular case, set the operator ID, etc... without having to write out a new pff file each time you launch the program. The most commonly used parameters are:

| NAME | VALUE |
| --- | --- |
| StartMode | set to "add" to skip the case list screen and immediately start adding a new case |
| Key | set to the case ID of the case to launch if you want to launch immediately into a particular case |
| OperatorID | set to the operator id to use for paradata logs and the getoperatorid() function in CSPro logic |

Note that the above extras can also be specified in the pff file itself using the StartMode, Key and OperatorID parameters.

The full list of pff file parameters for data entry applications can be found in the [CSPro help](https://www.csprousers.org/help/CSPro/run_production_data_entry.html):