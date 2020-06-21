# Build a To Do App

A simple introduction to building a To do list app in Android studio.

## Demo

![Image](https://github.com/ayeshaf9/to-do-app-android/blob/master/Images/toDoAppDemo.gif)

## Prerequistes

1. Install [Android Studio](https://developer.android.com/studio) 4.0
2. Make an account on [MarvelApps](https://marvelapp.com/) for a design prototype (optional)
3. Download or clone this repo to view full code

## Steps

1. Wireframes and prototypes provide a general visual idea of what an app might look and behave like. One way to protoype an app is shown here using MarvelApps, where many pages can be created under a project to form a playable protoype. 

![Image](https://github.com/ayeshaf9/to-do-app-android/blob/master/Images/marvelAppWalkthrough.gif)

2. Launch **Android Studio** and create a new project 
   - Choose an **Empty Activity** 
   - Name your first activity
   - Select **Java** for language 
   - Choose a **minimum SDK** that is able to run on the most number of devices

![Image](https://github.com/ayeshaf9/to-do-app-android/blob/master/Images/createProject.gif)

3. Every activity is created with a **java** and **xml** file. Open `activity_main.xml` in the layout folder to design the layout of the main acitivty. Images can be copied to the drawable folder so that they can be added in the layout through `@drawable/` reference.

![Image](https://github.com/ayeshaf9/to-do-app-android/blob/master/Images/activity_main_xml.PNG)

4. Make a **splash screen** that displays for 2.5 seconds when the app is launched using the following java code in the `MainAcitivity.java` file. The code demonstrates calling one activity from another.

![Image](https://github.com/ayeshaf9/to-do-app-android/blob/master/Images/MainActivity.PNG)

5. Make a new activity for the to do list home page

![Image](https://github.com/ayeshaf9/to-do-app-android/blob/master/Images/SecondActivity.gif)

6. Build a to do list layout in the `acitvity_second.xml` file using buttons and list view

![Image](https://github.com/ayeshaf9/to-do-app-android/blob/master/Images/activity_second_xml.PNG)

7. Create another acitivty for entering the to do list items called `ItemActivity`.

8. To edit the to do list items, create another activity called `EditActivity`.

9. Build the layouts of the `ItemActivity` and `EditActivity` in the `acitivity_item.xml` and `activity_edit.xml` respectively.

10. In the Java files, add functions that do the following:
    1. Display the to do list
    2. Make calls to other acitivites
    3. Show alert dialogues
    4. Save the to do list in storage to reload on app restart
    5. Button click listeners to invoke a certain action

11. The App can be run on an emulator in the **AVD Manager** or install on a device. 

![Image](https://github.com/ayeshaf9/to-do-app-android/blob/master/Images/AVDmanager.gif)
