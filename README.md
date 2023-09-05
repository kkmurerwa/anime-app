# Anime App

## Description

Anime App is a simple app that uses the Jikan-Moe [Anime/Manga Api](https://docs.api.jikan.moe/#tag/top).

## Design Principles

The app was built following Material 3 UI guidelines. It also follows the MVVM design pattern, Clean Architecture and SOLID Principles.

## Technologies

The Anime app is written in Kotlin and uses the following technologies;

- **Room** - Offline Caching
- **Retrofit** - Network
- **Glide** - Image loading
- **Dagger Hilt** - Dependency Injection
- **Timber** - Logging

## Project Structure

The base of the app is dividided into two distinct sections;

### a. Core

The core section contains shared code between the various features. This includes;

1. Database
2. Network Wrappers
3. State Wrappers (UIState and DataSourceState)
4. Dependency Injection Modules
5. Utilities such as extension functions, app constants, etc.

### b. Features

The features package contains subdivisions of each feature. Each feature is further sub-divided into three parts;

1. **Data** - Contains data-related logic
2. **Domain** - Contains the business logic
3. **Presentation** - Contains the UI and UI helpers.

## Future of Work

The app could benefit from the following and will probably be implemented in the future;

- Better test coverage including unit, integration and end-to-end testing.
- Pagination using the paging 3 library or a custom approach.
- Better KDocs on interfaces and their implementations.
