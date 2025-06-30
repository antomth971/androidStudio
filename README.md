# Android Studio Learning Projects

## Overview

This repository contains a collection of small Android Studio projects created by the author to learn and experiment with various development concepts. Each subfolder is an independent project focusing on different features of the Android ecosystem. Below is a summary of each project, highlighting its purpose, key technologies, and notable implementation details.

## Table of Contents

- [Android Studio Learning Projects](#android-studio-learning-projects)
  - [Overview](#overview)
  - [Table of Contents](#table-of-contents)
  - [Project Arrondissement](#project-arrondissement)
  - [Project ArrondissementPhoto](#project-arrondissementphoto)
  - [Project BeeWiReader](#project-beewireader)
  - [Project ClassroomActivity](#project-classroomactivity)
  - [Project ComplexeActivity](#project-complexeactivity)
  - [Project Interface Camera](#project-interface-camera)
  - [Project IoT](#project-iot)
  - [Project QRCode](#project-qrcode)
  - [Project QR Code Connect](#project-qr-code-connect)
  - [Project TP3](#project-tp3)

## Project Arrondissement

**Purpose:** A quiz app themed around the arrondissements of Paris, used to practice multi-screen navigation, network communication, and dynamic UI updates.

**Key Components & Technologies:**

* **Volley HTTP library:** Handles user authentication and quiz question requests to a server.
* **JSON (Gson):** Parses server responses (quiz questions and answers) into Java objects.
* **Multiple screens & navigation:** A login Activity leads to a main menu Activity; the main menu uses a `BottomNavigationView` with fragments (Home and Score).
* **Dynamic ListView:** Displays quiz questions with multiple-choice answers using a custom adapter; answer buttons are disabled after selection or when time runs out.
* **Timer and feedback:** A 10-second countdown (`ProgressBar` + handler) is shown for each question; provides feedback (e.g., vibration) when time is up or an answer is submitted.

**Notable Implementation Details:** For each quiz attempt, the app sends the selected arrondissement ID to a server to retrieve a question and answer options. A handler updates a circular ProgressBar to show the 10-second countdown. When time expires or once an answer is chosen, the app disables the answer buttons and provides feedback (such as triggering the device vibrator). The `BottomNavigationView` allows the user to switch between the quiz and score fragments within the main menu. Overall, this project demonstrates user login, remote data fetching, JSON parsing, and UI updates based on a timer.

## Project ArrondissementPhoto

**Purpose:** Demonstrates displaying images in a grid and allowing the user to view a selected image at full size, practicing grid layouts and passing data between activities.

**Key Components & Technologies:**

* **GridView with custom adapter:** Shows a grid of Paris arrondissement photos (thumbnail images) using an array of drawable resources.
* **Multiple Activities & Intents:** Tapping an image opens a second Activity to display it; passes the selected image’s resource ID via `Intent` extras.
* **Layout and UI:** Uses a grid layout in the main Activity and an ImageView in the detail Activity, with a consistent theme applied.

**Notable Implementation Details:** Image resources for the 20 Paris arrondissements are stored in the drawable folder. `MainActivity` populates the GridView using an array of these image resource IDs and a custom adapter. When the user clicks on an image, an `AdapterView.OnItemClickListener` sends the selected image ID through an `Intent` to `SecondActivity`. The `SecondActivity` retrieves this ID from the extras and displays the corresponding image in an ImageView. This project showcases how to use a GridView for an image gallery and how to navigate to a detail screen with data passed via Intent.

## Project BeeWiReader

**Purpose:** Connects to a **BeeWi SmartClim** Bluetooth Low Energy sensor to read its data (e.g., temperature and humidity), learning BLE GATT communication, threading, and UI updates with sensor data.

**Key Components & Technologies:**

* **Bluetooth Low Energy (BLE):** Scans for the BeeWi SmartClim sensor and connects via BLE GATT.
* **GATT callback:** Implements a custom `BeeWiSmartBtCallback` to handle connection events and read characteristics (temperature, humidity, battery level).
* **Background thread:** Performs BLE operations on a worker thread (with wait/notify synchronization) to avoid blocking the UI thread.
* **UI updates:** After reading sensor data, posts updates to the main thread to display temperature, humidity, and battery level; shows a CircularProgressView spinner while waiting for data.
* **Permissions:** Requests to enable Bluetooth and for necessary BLE permissions at runtime (prompting the user via the Activity Result API if Bluetooth is off).

**Notable Implementation Details:** On launch, the app prompts the user to enable Bluetooth if it’s not already on. It then connects to the SmartClim device via GATT and waits (with a timeout) for the `BeeWiSmartBtCallback` to return the sensor data. The raw data bytes are parsed into a `SensorData` object containing structured values (temperature, humidity, battery). Finally, the app updates the UI with these readings and hides the loading spinner. If the sensor isn’t reachable or fails to respond, the app shows an error message. This project demonstrates the full BLE workflow on Android: from permission handling and device connection to data retrieval and UI update.

## Project ClassroomActivity

**Purpose:** A simple two-screen app (login and welcome) to practice passing data between activities using Intent extras, including sending a custom Serializable object.

**Key Components & Technologies:**

* **Basic UI inputs:** A login screen with two `EditText` fields (username and password) and a `Button`.
* **Intents and extras:** On click of the login button, if inputs are valid, an `Intent` launches `SecondActivity` carrying the username (as a string) and a custom `User` object (as a Serializable extra).
* **Toast feedback:** Uses `Toast` messages for input validation (e.g., prompting if fields are empty) and for greeting the user on the second screen.
* **No external storage:** No actual authentication backend or persistent storage is used; this is purely an in-memory data transfer exercise between activities.

**Notable Implementation Details:** In `MainActivity`, the login button’s `onClickListener` validates that both fields are filled. It then creates a `User` object and an `Intent` for `SecondActivity`, putting the username under a key (e.g., "login") and the `User` object in the extras. When `SecondActivity` starts, it retrieves the `User` via `getSerializableExtra` and the username via `getStringExtra`. The second screen then displays a greeting (for example, using a Toast: "Bonjour <username>"). This project illustrates how to pass both simple and complex data to a new Activity using Intents.

## Project ComplexeActivity

**Purpose:** A project focusing on designing a complex UI layout within a single activity, as a playground for advanced view arrangements and layout techniques.

**Key Components & Technologies:**

* **Custom layout XML:** A complicated interface defined in `res/layout/test.xml` with multiple nested view groups or a `ConstraintLayout` to practice intricate positioning of elements.
* **Single Activity:** An AppCompatActivity that simply sets its content view to the complex layout (no additional runtime logic), keeping the focus on the UI design itself.
* **Multiple UI elements:** Likely includes a variety of widgets (`TextView`, `Button`, `ImageView`, etc.) arranged in a non-trivial configuration to achieve the desired layout.
* **Layout practice:** Serves as a sandbox to experiment with UI design – tweaking constraints, nesting layouts, and ensuring the interface looks correct – without any distraction from app logic.

**Notable Implementation Details:** Since the Java code only loads the layout, all the complexity resides in the XML. The developer likely used `ConstraintLayout` and possibly nested `LinearLayout` or `RelativeLayout` containers to achieve a sophisticated UI. By isolating this UI in its own module, it was easy to iteratively adjust the design and immediately view the results. In summary, *ComplexeActivity* is essentially a template for practicing advanced layout construction in Android.

## Project Interface Camera

**Purpose:** A base app structure for a camera interface using modern Android architecture components (fragments and Navigation). Created as a template to learn fragment navigation and UI scaffolding, with the intention of adding camera functionality.

**Key Components & Technologies:**

* **Jetpack Navigation:** Utilizes a navigation graph with two placeholder fragments (`FirstFragment` and `SecondFragment`); `MainActivity` uses a `NavController` (with an `AppBarConfiguration`) to manage fragment transactions and the app bar.
* **ViewBinding:** Employs View Binding (e.g., `ActivityMainBinding`, `FragmentFirstBinding`) to simplify UI initialization and avoid manual `findViewById` calls.
* **Material Design UI:** Includes a Toolbar (tied to the NavController for automatic title and up-button handling) and a FloatingActionButton (which shows a `Snackbar` as a sample action).
* **Fragment transitions:** `FirstFragment` contains a button that navigates to `SecondFragment` using Navigation component actions, demonstrating basic fragment-to-fragment navigation.
* **Camera-ready structure:** No camera logic is implemented yet, but this template sets up the necessary structure to add a camera feature (e.g., a CameraX preview in one of the fragments) within the navigation framework.

**Notable Implementation Details:** This project was generated from an Android Studio template (Navigation Drawer or Basic Navigation). In `MainActivity`, the NavController is set up with the Toolbar via `setupActionBarWithNavController`, and the FloatingActionButton’s onClick is wired to show a simple Snackbar. The placeholder fragments use binding to inflate their layouts and provide a basic interaction (navigating from the first to the second fragment). While currently the app just navigates between two static screens, it provides a foundation to integrate a live camera preview or camera controls into the existing fragment structure.

## Project IoT

**Purpose:** An app intended to interface with an IoT device (specifically an IP camera), combining a live video stream viewer with a Navigation Drawer UI.

**Key Components & Technologies:**

* **Navigation Drawer:** Built using the Navigation Drawer template, it provides a DrawerLayout with a side menu for multiple sections (e.g., Home, Gallery, Slideshow as placeholders in the template).
* **RTSP video stream:** Defines an RTSP URL (including credentials) for a network camera’s video feed, indicating the app is meant to connect to and display a live stream from an IP camera.
* **ViewBinding:** Simplifies access to UI components like the toolbar and floating action button in `MainActivity` and the navigation header.
* **IoT integration:** Intended to embed a video player view in one of the fragments to play the camera’s RTSP stream (which may require a third-party library or Android’s `VideoView` with the appropriate media support).
* **Template fragments:** Uses the default fragments from the drawer template as placeholders; these can be repurposed to show the camera stream or other IoT data once implemented.

**Notable Implementation Details:** Upon startup, `MainActivity` sets up the navigation drawer (syncing it with the toolbar) and includes a hard-coded RTSP link (`mrl`) for the IP camera. The current UI still has template features (like a FAB that shows a Snackbar and dummy menu items). To actually display the video, the developer would integrate a streaming player into a fragment (for example, using the open-source VLC library or an Android `MediaPlayer` that supports RTSP). This project was a learning step towards handling real-time video streams in an app while managing a multi-section UI with a navigation drawer.

## Project QRCode

**Purpose:** An application combining QR code scanning with basic client-server communication. It was built to practice using a camera scanner library for QR codes and handling user login via a web API.

**Key Components & Technologies:**

* **QR code scanner (ZBar):** Integrates the ZBar library to scan QR codes through the device camera (`MainActivity` implements `ZBarScannerView.ResultHandler`).
* **Camera handling:** The app embeds a `ZBarScannerView` in a layout and manages it in the activity lifecycle, starting the camera in `onResume()` and stopping it in `onPause()`. The scanner is configured to detect only QR codes, and results are delivered to `handleResult()`.
* **Login via Volley:** A `Login` Activity collects username and password, then uses a Volley POST request to authenticate with a local server (calling `login.php` with the credentials and an API key).
* **JSON parsing:** Parses the server’s JSON response (e.g., user data or a list of activities) using `JSONArray` and cleans any unwanted characters via a custom `deleteChar()` function.
* **App workflow:** The app typically launches to the login screen; after a successful login (and data retrieval), it proceeds to the QR scanning screen. The scanned QR code content is captured in `MainActivity` (currently logged, but could be used to trigger another activity or a server request).

**Notable Implementation Details:** `MainActivity` sets up the `ZBarScannerView` programmatically within a `FrameLayout`, restricting it to read QR codes. When a code is scanned, the content is logged via `Log.d` (this could be extended to perform an action like looking up information or confirming attendance). The `Login` activity ensures neither field is empty before building the request URL and making the Volley call. The app’s network security configuration permits cleartext HTTP traffic for the local API. In summary, *QRCode* demonstrates integrating device hardware (camera scanning) with backend API calls, including handling runtime permissions and JSON data processing.

## Project QR Code Connect

**Purpose:** An extension of the QR code project that integrates network connectivity and structured navigation. This app aims to use QR code scanning to connect with a remote service (e.g., for login or data retrieval) and employs the Jetpack Navigation component for its UI flow.

**Key Components & Technologies:**

* **Volley & Gson:** Uses Volley for HTTP requests and Gson to parse JSON responses into Java objects for easier handling of data.
* **Navigation Component:** Implements a navigation graph with fragments, creating a guided multi-screen flow (instead of standalone activities for each screen).
* **Combined UI elements:** Reuses or adapts parts of earlier projects – for example, it has a `Connexion` Activity (login screen) labeled "Arrondissement" (suggesting code borrowed from the Arrondissement project) and likely a scanning interface from the QRCode project.
* **Grid/List display:** The package name (e.g., `com.example.gourav.GridViewExample`) hints that a grid view or similar UI is part of the design, possibly to display information fetched after scanning a QR code.
* **QR scanning:** Intended to incorporate a QR scanner (like ZBar) so that after login (or in place of manual login), scanning a code will send data to the server and navigate to a results or confirmation screen based on the response.

**Notable Implementation Details:** This project appears to merge functionalities from multiple sources. For instance, it likely starts with a login or connection step (borrowing the *Arrondissement* app’s login logic) and then transitions into a fragment-based interface (to show scanned results or additional options). The presence of Gson means that any data returned from scanning (e.g., user info or event details encoded in a QR) can be directly converted into objects. The use of Navigation ensures that transitions (say, from a login fragment to a scanner fragment to a results fragment) are handled seamlessly. In essence, *QR Code Connect* was an attempt to combine QR scanning, user authentication, and a modern UI architecture into one application, demonstrating how these pieces can work together.

## Project TP3

**Purpose:** A small educational project (TP3 lab exercise) focusing on using a `Spinner` (dropdown list) and populating it with dynamic data.

**Key Components & Technologies:**

* **Spinner widget:** A dropdown list UI element centered on the screen.
* **ArrayAdapter:** Populates the Spinner with a simple array of strings (e.g., planet names and a placeholder phrase) provided in the code.
* **ConstraintLayout:** Uses a ConstraintLayout to position the Spinner (e.g., centering it by constraining to all sides of the parent).
* **No event handling:** Does not implement any selection listener for the Spinner; the goal was just to display a list of items in the dropdown.

**Notable Implementation Details:** When the app launches, the Spinner is filled with the specified items (for example, "mercury", "saturne", and a humorous phrase). The `ArrayAdapter` uses a built-in Android layout (like `android.R.layout.simple_list_item_1`) to display the Spinner choices. Since no action is taken on item selection, this project’s sole focus was understanding how to populate and use a Spinner. It successfully demonstrates setting up a Spinner and adapter, which was the learning objective for this exercise.
