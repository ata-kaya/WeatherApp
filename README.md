# WeatherApp

WeatherApp is an Android application built using Kotlin for a smooth and efficient development experience. The app provides weather information and includes a counter that tracks how many times the app goes to the background and comes back to the foreground.

## Architecture

This application follows the MVVM (Model-View-ViewModel) architecture pattern. This design pattern separates the app into three main components:

- **Model**: Represents the data layer, including data sources and business logic.
- **View**: Handles the UI elements and displays data to the user using traditional Android Views.
- **ViewModel**: Manages the UI-related data in a lifecycle-conscious way, providing data to the View and handling user interactions.

## Libraries Used

- **Kotlin**: The programming language used for writing the app.
- **Room**: A database library that provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
- **Android Navigation**: A framework that simplifies implementing navigation within an Android app, providing a consistent and predictable way to navigate between different screens.
- **Dagger Hilt**: A dependency injection library that simplifies the process of injecting dependencies in Android applications, built on top of Dagger.
- **Retrofit**: A type-safe HTTP client for Android and Java, used for making network requests and handling responses.
- **Gson**: A library for converting Java objects to JSON and vice versa, used for JSON serialization and deserialization.
- **Glide**: An image loading and caching library for Android that helps with efficient image loading.
- **LiveData**: A lifecycle-aware data holder class used to observe data changes and update the UI accordingly.

## Setup and Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/ata-kaya/WeatherApp.git
    ```

2. Navigate to the project directory:
    ```bash
    cd WeatherApp
    ```

3. Open the project in Android Studio.

4. Build and run the app using Android Studio or Gradle commands.

## Features

- **Weather Information**: Displays weather data using traditional Android UI components.
- **Background Foreground Counter**: Tracks how many times the app goes to the background and comes back to the foreground.

## Contributing

Feel free to submit issues or pull requests if you have suggestions or improvements.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

For more information, check out the [Room](https://developer.android.com/topic/libraries/architecture/room), [Android Navigation](https://developer.android.com/guide/navigation), [Dagger Hilt](https://dagger.dev/hilt/), [Retrofit](https://square.github.io/retrofit/), [Gson](https://github.com/google/gson), and [Glide](https://bumptech.github.io/glide/) documentation.
