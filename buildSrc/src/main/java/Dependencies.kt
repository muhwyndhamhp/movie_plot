object Versions {

    const val versionCode = 1
    const val versionName = "0.0.1"

    const val gradle = "4.1.2"

    const val kotlin_version = "1.4.21"
    const val koin_version = "2.2.0"

    const val material = "1.2.1"
    const val constraint_layout = "2.0.4"
    const val androidx_core = "1.3.2"
    const val app_compat = "1.2.0"
    const val fragment = "1.2.5"
    const val live_data = "2.2.0"

    const val glide = "4.11.0"

    const val room = "2.2.6"

    const val retrofit = "2.9.0"
    const val gson = "2.8.6"
    const val gson_retrofit_converter = "2.7.0"

    const val jetpack_navigation = "2.3.2"

    const val j_unit = "4.+"
}

object Deps {

    //base Gradle Deps
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.jetpack_navigation}"

    //AndroidX
    const val stdLibs = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidx_core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.app_compat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"


    //Koin
    const val koin = "org.koin:koin-android:${Versions.koin_version}"
    const val koinLifecycle = "org.koin:koin-androidx-scope:${Versions.koin_version}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin_version}"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.gson_retrofit_converter}"

    //Navigation
    const val jetpackNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.jetpack_navigation}"
    const val jetpackNavigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.jetpack_navigation}"

    //JUnit
    const val jUnit = "junit:junit:${Versions.j_unit}"
    const val jUnitTest = "androidx.test.ext:junit:1.1.2"

    //Lifecycle
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.live_data}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKapt = "androidx.room:room-compiler:${Versions.room}"

    //Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glide}"

    //Espresso
    const val espresso = "androidx.test.espresso:espresso-core:3.3.0"

}