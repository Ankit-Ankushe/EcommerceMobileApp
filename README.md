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

## 🏗 Architecture Detail
The project follows **Clean Architecture** principles with a clear separation of concerns:
1.  **Data Layer**: Handles API requests via Retrofit and data modeling. Includes the `ProductRepository` implementation.
2.  **Domain Layer**: (Simplified for this task) Includes the Repository interfaces and data models.
3.  **UI/Presentation Layer**:
    *   **ViewModels**: Manage UI state using `StateFlow` and handle business logic.
    *   **Compose Screens**: Declarative UI components that react to StateFlow updates.
    *   **Navigation**: Single Activity architecture using Compose Navigation.

## ⚙️ Setup Instructions
1.  Clone the repository.
2.  Open in **Android Studio Electric Eel** (or newer).
3.  Ensure you are using **Java 11** for the Gradle build.
4.  Sync Gradle and run the `app` module on an emulator or physical device (API 24+).

## 📝 Notes on API Data
Please note that the current `freeapi.app` provides image URLs that may return 404 errors due to external changes at the source (DummyJSON). The app includes robust error handling for these cases and displays a "Broken Image" icon to signify missing remote data, as per the developer task requirements.
