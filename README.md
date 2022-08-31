
## Marvel Developer API Sample Project

Libraries Used
* Retrofit
* Kotlin Serialization
* AndroidX ViewModel
* AppCompat
* Jetpack Compose
* Glide


## Setting developer keys

generate a debug.keystore using `keytool`

in build.gradle configure the following values.

    signingConfigs {
        debug {
            storeFile file("debug.keystore")
            storePassword "android"
            keyAlias "androiddebugkey"
            keyPassword "android"
        }

    }


## Design Decisions

I decided to use Compose for a modernized framework.  
An alternate choice would have been standard XML layouts with ConstraintLayout.
Using Compose also worked as an opportunity to use LazyColumn over RecyclerView.
