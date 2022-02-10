# Android-12-SplashScreen-Migration
1. Add the Android 12 Splash screen and ViewModel dependency
```kotlin

//Splash screen migration in lower than android 12
implementation 'androidx.core:core-splashscreen:1.0.0-beta01'
//Dependency for extending ViewModel class
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
``` 
2. Create res file named 'splash' add the Splashscreen Style
```kotlin

//value/splash
<style name="Theme.App.Starting" parent="Theme.SplashScreen">
        // Set the splash screen background, animated icon, and animation duration.
        <item name="windowSplashScreenBackground">@color/white</item>

        // Use windowSplashScreenAnimatedIcon to add either a drawable or an
        // animated drawable. One of these is required.
        <item name="windowSplashScreenAnimatedIcon">@drawable/android_logo</item>
        <item name="android:windowSplashScreenBrandingImage" tools:targetApi="s">@drawable/android_branding</item>
        <item name="windowSplashScreenAnimationDuration">1000</item>  # Required for
        # animated icons

        // Set the theme of the Activity that directly follows your splash screen.
        <item name="postSplashScreenTheme">@style/Theme.MigratingAndroid_12_SplashScreenAPI</item>  # Required.
    </style>
   ```
   Make new file v31/splash
  ```kotlin
    <style name="Theme.App.Starting" parent="Theme.SplashScreen">
        // Set the splash screen background, animated icon, and animation duration.
        <item name="windowSplashScreenBackground">@color/white</item>

        // Use windowSplashScreenAnimatedIcon to add either a drawable or an
        // animated drawable. One of these is required.
        <item name="windowSplashScreenAnimatedIcon">@drawable/android_logo</item>
        <item name="android:windowSplashScreenBrandingImage">@drawable/android_branding</item>
        <item name="windowSplashScreenAnimationDuration">200</item>
        # Required for
        # animated icons

        // Set the theme of the Activity that directly follows your splash screen.
        <item name="postSplashScreenTheme">@style/Theme.MigratingAndroid_12_SplashScreenAPI</item>
        # Required.
    </style>
   ```
3. Replace the main theme in manifiest > application > theme and manifest > activity > theme with new splash screen theme
```kotlin

android:theme="@style/Theme.App.Starting"
```
4. Add the logo and branding image in drawable

5. Create the new Viewmodel class and add the code
```kotlin

class SplashViewModel: ViewModel() {
    private val _isLoaded = MutableStateFlow(true)
    val isLoading = _isLoaded.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            _isLoaded.value = false
        }
    }
}
```
6. Then access viewModel class in main activity
```kotlin

private val splashViewModel = SplashViewModel()
//installSplashScreen function must be before the 'super' method
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                splashViewModel.isLoading.value
            }
        }
 ```
