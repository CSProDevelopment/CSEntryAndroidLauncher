# CSEntry Launcher (Android)

This application, CSEntry Launcher, demonstrates launching CSEntry from another Android application. This is shown using two approaches: via an [Intent](https://developer.android.com/reference/android/content/Intent) or a [deep link](https://developer.android.com/training/app-links/deep-linking).

The deep link approach differs from the Intent approach in two ways:

- Deep links can open non-entry applications.
- Deep links for entry applications open the Case Listing activity, whereas the Intent approach skips the Case Listing and opens the Entry activity.


## Using an Intent

Create an Intent with the class *gov.census.cspro.csentry.ui.EntryActivity*:

```kotlin
val intent = Intent()
intent.component = ComponentName("gov.census.cspro.csentry", "gov.census.cspro.csentry.ui.EntryActivity")
```

Specify the PFF's filename or path as an extra:

```kotlin
intent.putExtra("PffFilename", "CSEntry Application.pff")
```

If *PffFilename* is a fully evaluated file path, that file will be opened. If not, CSEntry will recursively look at files in the *csentry* directory until finding a file with the specified filename.

To pass additional parameters that will get added to the PFF, add them as extras. For example, to set the operator ID and the case key:

```kotlin
intent.putExtra("OperatorID", "John Doe")
intent.putExtra("Key", "01050669557")
```

Finally, launch the activity using the Intent:

```kotlin
startActivity(intent)
```


## Using a Deep Link

Deep links that run applications using CSEntry begin with https://csprousers.org/pff. The PFF's filename is specified, following /pff, as part of the URI's path. If multiple path segments are provided, for example https://csprousers.org/pff/my-directory/my-survey.pff, the path is treated as a full file path to be evaluated from within the *csentry* directory. If only a single path segment is provided, for example https://csprousers.org/pff/my-survey.pff, then CSEntry will recursively look at files in the *csentry* directory until finding a file with the specified filename.

To pass additional parameters that will get added to the PFF, add them as query parameters.

For example, code to launch CSEntry using a deep link might look like:

```kotlin
val uri = Uri.parse("https://csprousers.org/pff")
    .buildUpon()
    .appendPath("CSEntry Application.pff")
    .appendQueryParameter("OperatorID", "John Doe")
    .appendQueryParameter("Key", "01050669557")
    .build()

val intent = Intent(Intent.ACTION_VIEW, uri)
startActivity(intent)
```
