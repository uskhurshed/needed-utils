# 🧰 Needed Utils

Универсальный набор Kotlin-утилит для Android: работа с изображениями, JSON, SharedPreferences, Intent, WindowInsets и анимацией.

---

## 📁 Путь в проекте

```
utils/src/main/java/com/easyapps/utils
```

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
