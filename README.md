## [https://🚀🔗.ml](https://xn--qv8hrw.ml/): Rocket Link
[![Test and Build](https://github.com/Seryiza/rocket-link/actions/workflows/test-and-build.yml/badge.svg)](https://github.com/Seryiza/rocket-link/actions/workflows/test-and-build.yml)

Service to create short emoji link for your long-long link.

https://🚀🔗.ml/to/😆 ➔ https://github.com/Seryiza/rocket-link/stargazers

![Main page screenshot](./.github/main_page_screenshot.png)

### How to develop it
Project requires [Leiningen](https://leiningen.org/) (2.0.0+).

```
export PORT=3000
export BASE_DOMAIN=localhost:3000
export DATABASE_URL=postgresql://postgres:pass@localhost:54320/rocket_link

$ lein deps
$ lein migratus migrate
$ lein repl
user=> (dev)            ; Enter to development namespace
rocket-repl=> (start)   ; Start systems (db connection, http server...)
rocket-repl=> (restart) ; Reload changed namespaces and restart systems
```

See also: [Clojure Workflow Reloaded](https://cognitect.com/blog/2013/06/04/clojure-workflow-reloaded), [Mount REPL](https://github.com/tolitius/mount#running-new-york-stock-exchange)

### Идеи возможностей
- ~~Главная страница для создания эмодзи-ссылки~~
- ~~Переход по ссылкам~~
- Возможность быстро скопировать / поделиться ссылокой после её создания
- Регистрация пользователя и список своих ссылок
- Статистика переходов по своим ссылкам
- API endpoint для создания ссылки
- Страница администратора со списком всех ссылок и возможностью их отключения
- Предложение нескольких вариантов эмодзи при создании ссылки
- Возможность самому выбрать эмодзи (частично или полностью)

### Идеи технические
- Отрефакторить конфиг на использование env + config.edn
- Добавить CSRF
- Добавить кэширование
- Добавить защиту от ботов
- Проверить отображение эмодзи и убрать из списка эмодзи без картинок
- Добавить проверку корректности URL (+ частичное исправление вида "добавить https://")
- Добавить обработку ошибок 5xx (Sentry?)
- Добавить случайное перемешивание, чтобы не быть в порядке возрастания Unicode Emoji
- Сделать punycode/redirect как middleware (и использовать ring redirect)
- Сохранить Pico.css как локальную зависимость
- Добавить информацию для SEO
- Добавить favicon
- Подумать, чтобы сделать BASE_DOMAIN опциональным
- [Рефакторинг тестов](https://guide.clojure.style/#testing)
- Добавить комментарии, где требуется (особенно в код эмодзи)
- Добавить трекинг посещаемости
- Добавить приёмочные тесты
