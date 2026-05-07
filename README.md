# EcommerceApp - Android Developer Task

A modern, scalable Android application built with Jetpack Compose, following MVVM and Clean Architecture principles.

## 📱 Screenshots
| Home Screen | Product Detail |
| :---: | :---: |
| <img src="screenshots/home.png" width="300"> | <img src="screenshots/details.png" width="300"> |


## 🚀 Features
- **Product Catalog**: Browsable list of products fetched from FreeAPI.
- **Dynamic Filtering**: Categories are extracted dynamically from the API response for filtering.
- **Detailed Product View**: Deep dive into product specifications with an image carousel.
- **Pull-to-Refresh**: Swipe down on the home screen to refresh product data.
- **Stock Indicators**: Visual cues for product availability (In Stock, Low Stock, Out of Stock).
- **Responsive UI**: Built with Material 3 and Jetpack Compose for a premium look and feel.
- **Animations**: Subtle entrance animations for a smooth user experience.

## 🛠 Tech Stack
- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
- **Architecture**: MVVM (Model-View-ViewModel) + Clean Architecture
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
- **Asynchronous**: Kotlin Coroutines & Flow
- **Bonus Helpers**: Accompanist (Pager, SwipeRefresh)

## 🛠️ Setup Instructions
Follow these steps to get the project running on your local machine:

### 1. Prerequisites
*   **Android Studio**: Electric Eel | 2022.1.1 or newer.
*   **JDK**: Java 11 (ensure your JAVA_HOME and Project Structure are set to Java 11).
*   **Android SDK**: API Level 33 (Android 13.0).

### 2. Installation
1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/Ankit-Ankushe/EcommerceMobileApp.git
    ```
2.  **Open in Android Studio**:
    *   Go to `File -> Open` and select the project folder.
    *   Wait for the Gradle sync to complete.
3.  **Build & Run**:
    *   Select the `app` configuration.
    *   Choose an emulator or physical device (API 24+).
    *   Click the **Run** (green play) button.

---

## 🏗️ Technical Architecture
The project is built using **Clean Architecture** principles to ensure scalability, testability, and maintainability.

### Layers:
*   **Presentation (UI)**: Built with 100% **Jetpack Compose**. Uses **StateFlow** to observe UI state from ViewModels.
*   **Domain**: Contains the core Business Logic and Repository interfaces.
*   **Data**: Implementation of Repositories, Retrofit Service interfaces, and Data Models.

### Key Patterns Used:
*   **MVVM Pattern**: Decouples UI logic from business logic.
*   **Repository Pattern**: Abstracts the data source (Network) from the rest of the app.
*   **Dependency Injection (Hilt)**: Manages object lifecycles and simplifies testing.
*   **Coroutines & Flow**: Handles all asynchronous operations with safety and efficiency.

---

## 📝 Important Notes for Reviewers
*   **API Image URLs**: The `freeapi.app` source currently provides some image links that are 404 (deleted from host). I have implemented robust error handling to display a "Broken Image" icon in these cases, rather than a blank space, to maintain UI integrity.
*   **Pull-to-Refresh**: Swipe down on the Home Screen to trigger a fresh API call.
*   **Animations**: Check the list entrance animations for a smooth UX.
