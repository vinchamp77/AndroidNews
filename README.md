# Android Kotlin Weekly - Simple RSS Feed Reader

This is a simple RSS feed reader app that currently reads my blog (Android Kotlin Weekly)'s feed - [rss.xml](https://vtsen.hashnode.dev/rss.xml). It is written in Kotlin and Jetpack Compose.

![](screenshots/Android_News_Overview.gif)

## Features
- Bookmark article
- Share article
- Mark article as read / unread
- Search articles

## Requirements
- Android Studio Dolphin or later

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
- Work Manager
- Notification

## Articles
- [Simple RSS Feed Reader - Jetpack Compose](https://vtsen.hashnode.dev/simple-rss-feed-reader-jetpack-compose)
- [Convert View Model to Use Hilt Dependency Injection](https://vtsen.hashnode.dev/convert-view-model-to-use-hilt-dependency-injection)

## Branches
- [master](https://github.com/vinchamp77/AndroidNews) - multiple view models (use recommended way to collect flow)
- [master_org](https://github.com/vinchamp77/AndroidNews/tree/master_org) - single view model (previous implementation)
- [master_org_hilt](https://github.com/vinchamp77/AndroidNews/tree/master_org_hilt) - convert view model to use hilt ([diff](https://github.com/vinchamp77/AndroidNews/compare/129e75036178fa2427e7283a605ada6e7fa27325..a23b2dfc36447be82339fb26d9a3e1a36108fb4a)) 
