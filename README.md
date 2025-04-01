# 📦 Библеотека Needed Utils от Khurshed Usmonov.

Универсальный набор Kotlin-утилит для Android: работа с изображениями, JSON, SharedPreferences, Intent, WindowInsets и анимацией.

---

## 🚀 Подключение

### 1. Добавьте JitPack в `settings.gradle.kts`

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. Добавьте зависимость в `build.gradle.kts`

```kotlin
dependencies {
    implementation("com.github.uskhurshed:needed-utils:v1.0.0")
}
```

> Также можно использовать `master-SNAPSHOT` или commit hash.

---

## 📦 Содержимое

| Класс              | Назначение |
|--------------------|------------|
| `ImageUtils`       | Загрузка изображений через Glide, плейсхолдеры, screenshot, share |
| `JsonUtils`        | Работа с JSONObject/JSONArray: безопасное получение, конвертация |
| `PreferencesUtils` | Упрощённая работа с SharedPreferences |
| `DataTransferUtils`| Получение Parcelable и Serializable из Intent и Bundle |
| `WindowUtils`      | Работа с системными insets, анимацией кликов, светлыми панелями |

---

## 🔍 Примеры использования

### 🖼 ImageUtils

```kotlin
imageView.glideCenterCrop("https://link.com/image.jpg")
imageView.glideCenterInside(url)
bitmap.share(context, "Поделиться изображением")
view.screenShot()
```

---

### 📦 JsonUtils

```kotlin
val json = JSONObject(response)
val name = json.string("name", "No Name")
val arr = json.jsonArray("items")
val secondName = arr.stringOrNull(1)
```

---

### 💾 PreferencesUtils

```kotlin
PreferencesUtils.init(context)
PreferencesUtils.setPrefString("key", "value")
val name = PreferencesUtils.getPrefString("key", "default")
```

---

### 📤 DataTransferUtils

```kotlin
val data: MyData? = intent.parcelable("dataKey")
val id: String? = bundle.serializable("id")
```

---

### 🪟 WindowUtils

```kotlin
view.setupInsets()
activity.setLightSystemBars(true)
view.setInteractiveClick { toast("Clicked") }
val px = 16.dpToPx(context)
```

---

## 📜 License

MIT License — используй свободно в своих проектах.
