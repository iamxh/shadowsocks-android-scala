enablePlugins(AndroidApp)
android.useSupportVectors

name := "shadowsocks"
version := "4.1.6"
versionCode := Some(186)

proguardOptions ++=
  "-dontwarn com.evernote.android.job.gcm.**" ::
  "-dontwarn com.evernote.android.job.util.GcmAvailableHelper" ::
  "-dontwarn com.j256.ormlite.**" ::
  "-dontwarn okio.**" ::
  "-dontwarn org.xbill.**" ::
  "-keep public class com.evernote.android.job.v21.PlatformJobService" ::
  "-keep public class com.evernote.android.job.v14.PlatformAlarmService" ::
  "-keep public class com.evernote.android.job.v14.PlatformAlarmReceiver" ::
  "-keep public class com.evernote.android.job.JobBootReceiver" ::
  "-keep public class com.evernote.android.job.JobRescheduleService" ::
  "-keep class com.github.shadowsocks.JniHelper { *; }" ::
  Nil

val playServicesVersion = "10.2.1"
resolvers += Resolver.jcenterRepo
libraryDependencies ++=
  "com.futuremind.recyclerfastscroll" % "fastscroll" % "0.2.5" ::
  "com.evernote" % "android-job" % "1.1.9" ::
  "com.github.jorgecastilloprz" % "fabprogresscircle" % "1.01" ::
  "com.j256.ormlite" % "ormlite-android" % "5.0" ::
  "com.mikepenz" % "crossfader" % "1.5.0" ::
  "com.mikepenz" % "fastadapter" % "2.5.2" ::
  "com.mikepenz" % "iconics-core" % "2.8.3" ::
  "com.mikepenz" % "materialdrawer" % "5.9.0" ::
  "com.mikepenz" % "materialize" % "1.0.1" ::
  "com.squareup.okhttp3" % "okhttp" % "3.7.0" ::
  "com.twofortyfouram" % "android-plugin-api-for-locale" % "1.0.2" ::
  "dnsjava" % "dnsjava" % "2.1.8" ::
  "eu.chainfire" % "libsuperuser" % "1.0.0.201704021214" ::
  "net.glxn.qrgen" % "android" % "2.0" ::
  Nil

lazy val goBuild = TaskKey[Unit]("go-build", "Build go and overture")
goBuild := {
  Process(Seq("mobile/src/overture/make.bash", minSdkVersion.value)) ! streams.value.log match {
    case 0 => // Success!
    case n => sys.error(s"Native build script exit code: $n")
  }
}
