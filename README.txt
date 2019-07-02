1. App information
   Application also support both Portrait/Landscape orientation mode

2. How to running app
 Runing app by following ways

- Using app-debug.apk directly (Attached in root folder)

- Using Android Studio tool
  + Open project by Android Studio tool
  + Android Studio tool will take care the rest (download libraries, modify configuration,...)
  + Connect Real device to computer or using AVD (Android Virtual Devices)
  + Select "App" on "Run/Debug configurations" menu on toolbar and perform "Shift + F10" to execute application

- Using command line tool
  + Connect Real device to computer or using AVD (Android Virtual Devices)
  + Using command line tool (CMD) and navigate to root project folder
  + execute command 
    ./gradlew installDebug
  + APK file will be built and immediately install it on a running emulator or connected device

3. How to testing app
 - Using Android Studio tool
   + Open project by Android Studio tool (Same as 2)
   + In Project window, right click on test(test) folder and select        "Run Tests..." to execute Unit test
   + In Project window, right click on test(androidTest) folder and select "Run Tests..." to execute Instrumentation test

	
 - Using command line
   + Make sure Real device is connected to computer or using AVD (Android Virtual Devices)
   + Using command line tool (CMD) and navigate to root project folder
   + execute Unit test command
    ./gradlew test
   + execute Instrumentation test command
    ./gradlew connectedAndroidTest
   + Test result will be displayed on command line window
   