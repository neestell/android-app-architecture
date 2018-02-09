# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/evgeniynagibin/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses TermsAndConditionsView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn java.lang.invoke.**
# Global
-keepattributes Signature

# Models
-keepclasseswithmembers class com.pixelcraft.** { *; }
-keepclasseswithmembernames class com.pixelcraft.** { *; }

# Support
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#Gesture view
-keep public class * extends com.alexvasilkov.gestures.GestureController

-keepclassmembers class * extends com.alexvasilkov.gestures.GestureController{
 public *;
}

-keepclasseswithmembers class com.alexvasilkov.gestures.GestureController { *; }
-keepclasseswithmembernames class com.alexvasilkov.gestures.GestureController { *; }

# Retrofit2
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okio.**
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault

#GMS
-keep class com.google.android.gms.**{*;}
-dontwarn com.google.android.gms.**

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Picasso
-dontwarn com.squareup.okhttp.**

# Gson
-keep class com.google.gson.** { *; }
-keepattributes Signature

# Bottom navigation
-keep public class android.support.design.widget.BottomNavigationView { *; }
-keep public class android.support.design.internal.BottomNavigationMenuView { *; }
-keep public class android.support.design.internal.BottomNavigationPresenter { *; }
-keep public class android.support.design.internal.BottomNavigationItemView { *; }

# Chartboost
-keep class com.chartboost.** { *; }
