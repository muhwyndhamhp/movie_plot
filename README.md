# movie_plot

# ***Notice Before Build***

For obvious security reason, I won't include TMDB's Api Key inside this project. Api Key is automatically injected into BuildConfig via ```local.properties``` which you need to provide your own Api Key.

There are 2 methods to achieve this :

## ***Via ```local.properties``` (Recommended)***

Once you clone this project and opening it in Android Studio, Gradle will generate ```local.properties``` file which would be automatically excluded from Git. open this file and add code below:

```
tmdbApiKey="<Your Api Key V3 Here>"
```

## ***Via ```gradle.properties```***

If local.properties were not generated, you can add the Api Key via ```gradle.properties``` file. 

First open the ```gradle.properties``` file and add code below:

```
tmdbApiKey="<Your Api Key V3 Here>"
```

Then open ```\app\build.gradle``` and replace this line :
```
buildConfigField("String", "TMDB_API_KEY", properties.getProperty("tmdbApiKey", ""))
```
with this :
```
buildConfigField("String", "TMDB_API_KEY", tmdbApiKey)
```

***PLEASE DON'T COMMIT ```gradle.properties``` FILE INTO REPOSITORY OR YOUR API KEY WILL LEAK***

# About App

App for consuming The Movie DB's API (TMDB). Using several technology stacks for Android Development such as:

- MVVM
- Room
- Koin
- Retrofit
- Multi Module
- Single Activity with Jetpack Navigation Component
- Kotlin DSL for Gradle Dependency

