## [https://🚀🔗.ml](https://xn--qv8hrw.ml/): Rocket Link
[![Test and Build](https://github.com/Seryiza/rocket-link/actions/workflows/test-and-build.yml/badge.svg)](https://github.com/Seryiza/rocket-link/actions/workflows/test-and-build.yml)

Service to create short emoji link for your long-long link.

https://🚀🔗.ml/to/😆 ➔ https://github.com/Seryiza/rocket-link/stargazers

<div>
  <img alt="main page screenshot" src="./.github/main-page-screenshot.png" width="30%">
  <img alt="created link page screenshot" src="./.github/created-link-page-screenshot.png" width="30%">
</div>

### How to develop it
Project requires [Leiningen](https://leiningen.org/) (2.0.0+).

```
export PORT=3000
export PROJECT_URL=http://localhost:3000
export DATABASE_URL=postgresql://postgres:pass@localhost:54320/rocket_link

$ lein deps
$ lein migratus migrate
$ lein repl
user=> (dev)            ; Enter to development namespace
rocket-repl=> (start)   ; Start systems (db connection, http server...)
rocket-repl=> (refresh) ; Reload changed namespaces and restart systems
```

See also: [Clojure Workflow Reloaded](https://cognitect.com/blog/2013/06/04/clojure-workflow-reloaded), [Mount REPL](https://github.com/tolitius/mount#running-new-york-stock-exchange)

#### Neovim
If you use [Neovim](https://neovim.io/) and [Conjure](https://github.com/Olical/conjure), you can configurate your init.lua like this:

```lua
vim.g['conjure#client#clojure#nrepl#refresh#after'] = 'rocket-repl/restart'
```

And restart changed namespaces by default mapping `<localleader>rr`. See [Conjure doc](https://github.com/Olical/conjure/blob/master/doc/conjure-client-clojure-nrepl.txt). It's very cool!

### Идеи возможностей
- ~~Главная страница для создания эмодзи-ссылки~~
- ~~Переход по ссылкам~~
- ~~Возможность быстро скопировать / поделиться ссылокой после её создания~~
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
- Добавить информацию для SEO
- Добавить favicon
- Подумать, чтобы сделать BASE_DOMAIN опциональным
- [Рефакторинг тестов](https://guide.clojure.style/#testing)
- Добавить комментарии, где требуется (особенно в код эмодзи)
- Добавить трекинг посещаемости
- Добавить приёмочные тесты
- Добавить поддержу нескольких языков
    - Возможно, потребуется отрефакторить message.clj (вынести сообщения в мультиметоды)
- Добавить красивую 404
- Заиспользовать [HugSQL](https://www.hugsql.org/) для более удобного взаимодействия с БД
