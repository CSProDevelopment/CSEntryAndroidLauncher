# CSEntryAndroidLauncher

This is an example of starting CSEntry from another Android application. To do this, use an Intent with the class *gov.census.cspro.csentry.ui.EntryActivity*:

```kotlin
val intent = Intent()
intent.component = ComponentName("gov.census.cspro.csentry", "gov.census.cspro.csentry.ui.EntryActivity")
```

You must specify the PFF's filename or path as an extra in the Intent:

```kotlin
intent.putExtra("PffFilename", "CSEntry Application.pff")
```

If *PffFilename* is a fully evaluated file path, that file will be opened. If not, CSEntry will recursively look at files in the *csentry* directory until finding a file with the filename.

To pass additional parameters that will get added to the PFF, add them as extras. For example, to set the operator ID and the case key:

```kotlin
intent.putExtra("OperatorID", "John Doe")
intent.putExtra("Key", "01050669557")
```

Finally launch the activity using the Intent:

```kotlin
startActivity(intent)
```
