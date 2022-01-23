## [https://🚀🔗.ml](https://xn--qv8hrw.ml/): Rocket Link
[![Test and Build](https://github.com/Seryiza/rocket-link/actions/workflows/test-and-build.yml/badge.svg)](https://github.com/Seryiza/rocket-link/actions/workflows/test-and-build.yml)

![Main page screenshot](./.github/main_page_screenshot.png)

Emoji link for your link

### Идеи возможностей
- ~~Главная страница для создания эмодзи-ссылки~~
- ~~Переход по ссылкам~~
- Регистрация пользователя и список своих ссылок
- Статистика переходов по своим ссылкам
- API endpoint для создания ссылки
- Предложение нескольких вариантов эмодзи при создании ссылки
- Возможность самому выбрать эмодзи (частично или полностью)

### Идеи технические
- Отрефакторить конфиг на использование env + config.edn
- Добавить уникальный индекс на кодовое имя в таблицу (на всякий случай)
- Переименовать code-name на... Что-нибудь более конкретное. Это вообще последовательность эмодзи для ссылки
- Добавить CSRF
- Добавить кэширование
- Добавить защиту от ботов
- Проверить отображение эмодзи и убрать из списка эмодзи без картинок
- Отрефакторить согласно [код-стайлу](https://github.com/bbatsov/clojure-style-guide)
- Добавить проверку корректности URL (+ частичное исправление вида "добавить https://")
- Добавить обработку ошибок 5xx (Sentry?)
