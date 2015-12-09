#####################################
######### 主程序不能混淆的代码 ##########
#####################################




######### 第三方不能混淆的代码 ##########

############ picasso ################
-dontwarn com.squareup.okhttp.**


############ litepal ################
-dontwarn org.litepal.*
-keep class org.litepal.** { *; }
-keep enum org.litepal.**
-keep interface org.litepal.** { *; }
-keep public class * extends org.litepal.**
-keepattributes *Annotation*
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers class * extends org.litepal.crud.DataSupport{*;}

