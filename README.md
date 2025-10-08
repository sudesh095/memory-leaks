# Memory Leaks Study in Android

![Android CI](https://img.shields.io/badge/Android-Compose-blue) ![Kotlin](https://img.shields.io/badge/Kotlin-2.2-orange) ![License](https://img.shields.io/badge/License-MIT-green)

A study project for **Android memory leaks** and safe practices. This app demonstrates common memory leak scenarios in **Activities, Views, Handlers, Coroutines, Receivers, Adapters, Bitmaps, and Fragments**, along with **Jetpack Compose** examples.

---

## Features

- **Context Leaks**: Unsafe Activity/Context references and safe alternatives.  
- **View Leaks**: Views holding references unnecessarily.  
- **Handler Leaks**: Handlers referencing Activities.  
- **Coroutine Leaks**: Improper Coroutine scopes.  
- **Receiver Leaks**: BroadcastReceivers not unregistered.  
- **Adapters**: Memory leaks vs safe usage with `applicationContext`.  
- **Large Collections / Bitmaps**: Unbounded memory usage and bounded `LruCache`.  
- **Fragment Leaks**: Persistent binding objects.  
- **Jetpack Compose Leaks**: Observers, coroutines, and safe patterns.  
- Educational code showing **unsafe vs safe implementations**.

---


## Project Structure

```
com.codexorbit.oom_memoryleaks
│
├─ presentation.views
│   ├─ HomeScreen.kt
│   └─ MyButton.kt
├─ presentation.activities
├─ ui
└─ utils
```

---

## Usage

1. Clone the repository:

```bash
git clone https://github.com/sudesh95/memory-leaks.git
```

2. Open in **Android Studio** and build the project.  
3. Run on an **emulator or device**.  
4. Explore **leak scenarios** and **safe implementations** using the buttons in the Home Screen.  

---

## Learning Objectives

- Identify **common sources of memory leaks**:
  - Strong Activity/Context references
  - Views not released
  - Handlers & Runnables
  - Improper Coroutine scopes
  - Broadcast Receivers
  - Large collections & Bitmaps
  - Fragment bindings
  - Compose observers

- Learn **safe coding patterns**:
  - Use `applicationContext`
  - Nullify Fragment bindings
  - Unregister receivers and handlers
  - Bounded caching
  - Proper Coroutine scoping
  - Dispose Compose observers properly

- Debug memory leaks with **Android Studio Profiler** or **LeakCanary**.

---

## Key Takeaways

- Memory leaks can cause **OOM crashes** and app instability.  
- Proper **lifecycle management** and **context usage** are crucial.  
- **Jetpack Compose** still requires careful handling of observers, coroutines, and state.  

---

## References

- [Android Memory Management](https://developer.android.com/topic/performance/memory)  
- [LeakCanary](https://square.github.io/leakcanary/)  
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)

---

## License

MIT License © 2025 Sudesh Kumar

