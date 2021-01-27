# movie_plot

# ***Notice Before Build***

For obvious security reason, I won't include TMDB's Api Key inside this project. Api Key is automatically injected into BuildConfig via ```local.properties``` which you need to provide your own Api Key.

There are 2 methods to achieve this :

- ***Via ```local.properties``` (Recommended)***

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Once you clone this project and opening it in Android Studio, Gradle will generate ```local.properties``` file which would be automatically excluded from Git. open this file and add code below:

```
tmdbApiKey="<Your Api Key V3 Here>"
```

- ***Via ```gradle.properties```***

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If local.properties were not generated, you can add the Api Key via ```gradle.properties``` file. 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;First open the ```gradle.properties``` file and add code below:

```
tmdbApiKey="<Your Api Key V3 Here>"
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Then open ```\app\build.gradle``` and replace this line :
```
buildConfigField("String", "TMDB_API_KEY", properties.getProperty("tmdbApiKey", ""))
```
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;with this :
```
buildConfigField("String", "TMDB_API_KEY", tmdbApiKey)
```

# About App

App for consuming The Movie DB's API (TMDB). Using several technology stacks for Android Development such as:

- MVVM
- Room
- Koin
- Retrofit
- Multi Module
- Single Activity with Jetpack Navigation Component
- Kotlin DSL for Gradle Dependency

