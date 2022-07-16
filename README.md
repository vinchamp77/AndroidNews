# Simple RSS Feed Reader (Kotlin and Jetpack Compose)

This is a simple RSS feed reader app that currently reads my blog's feed - [rss.xml](https://vtsen.hashnode.dev/rss.xml). 

![](screenshots/Android_News_Overview.gif)

> Note: This is my first Jetpack Compose app. So there could have a lot improvements needed and may not follow 100% best practices. See "Future Refactor" section below.

## Features
- Bookmark article
- Share article
- Mark article as read / unread
- Search articles

## Requirements
- Android Studio Bumblebee or later

## Tech Stack
- [MVVM Architecture](https://vtsen.hashnode.dev/mvc-vs-mvp-vs-mvvm-design-patterns)
- Jetpack Compose
- Ktor Client
- Room Database
- Coil
- XmlPullParser
- [Compose Navigation](https://vtsen.hashnode.dev/simple-jetpack-compose-navigation-example)
- Scaffold (Top/Bottom Bar)
- Webview
- [Coroutines](https://vtsen.hashnode.dev/kotlin-coroutines-basics-simple-android-app-demo) & Flow
- Accompanist (System UI & Swipe Refresh)

## Articles
- [Convert View Model to Use Hilt Dependency Injection](https://vtsen.hashnode.dev/convert-view-model-to-use-hilt-dependency-injection)

## Future Refactor
- Decouple `ViewModel` from composable functions, state hoisting at higher composable function
- Rename composable functions not to start with "verb" because it implies action (which is not)

## Branches
- [master](https://github.com/vinchamp77/AndroidNews)
- [hilt](https://github.com/vinchamp77/AndroidNews/tree/hilt) - convert view model to use hilt
