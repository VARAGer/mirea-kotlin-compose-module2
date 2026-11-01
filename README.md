# Модуль 2 — Jetpack Compose

Учебный Android-проект по заданиям модуля 2. Каждый пример находится в отдельном файле,
главный экран позволяет выбрать задание.

## Запуск

Открыть эту папку в Android Studio, дождаться синхронизации Gradle и запустить модуль `app`.
Нужны JDK 17, Android SDK 35 и устройство с Android 8.0 или новее.

## Задания 1–5

- `HelloTask.kt`: приветствие с nullable-параметром и три `@Preview`.
- `TextTask.kt`: строковый ресурс и три варианта оформления текста.
- `ButtonTask.kt`: кнопка с рамкой, скруглением и тенью 4 dp.
- `InitialsTask.kt`: серый контейнер 100 dp и круг с инициалами.

Задание 1 собирается в двух вариантах:

```sh
./gradlew assembleDebug -PgreetingName=Android
./gradlew assembleDebug
```

Во втором варианте в `Hello` передаётся `null`.
Для задания 2 открыть `HelloTask.kt` и панель Preview: portrait, landscape и round.

## Стиль кода

Используются осмысленные имена, отступы в четыре пробела, строковые ресурсы,
отдельные компоненты интерфейса и форматирование ktlint.

```sh
./gradlew ktlintFormat
./gradlew ktlintCheck lintDebug assembleDebug
```
