require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Добрый день, чем могу Вам помочь? || tts = "Добрый день, чем могу Вам помочь?"
        buttons:
            "Адреса магазинов и ПВЗ" -> /adres market
            "Как оформить заказ?" -> /CreateOrderStep1
            "Режим работы"
            "Условия доставки"
            "Как можно оплатить заказ?"
            "Как оформить возврат?"
            "Связаться с менеджером"
        intent: /Адреса || toState = "/adres market"
        intent: /Оформить заказ || toState = "/CreateOrderStep1"
        event: noMatch || toState = "/NoMatch"

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}
        go!: /Start

    state: adres market
        a: Адреса магазинов и пунктов выдачи, схему проезда к ним Вы можете посмотреть здесь https://magok.ru/clients/contacts
            Наличие товаров в магазинах можно уточнить у менеджера
             Могу Вам помочь чем-то ещё? || htmlEnabled = true, html = "Адреса магазинов и пунктов выдачи, схему проезда к ним Вы можете посмотреть здесь <u>https://magok.ru/clients/contacts</u><br><br><i>Наличие товаров в магазинах можно уточнить у менеджера</i><br><br>&nbsp;Могу Вам помочь чем-то ещё?"
        buttons:
            "Связаться с менеджером"
            "Вернуться" -> /Start
        intent: /Звонок || toState = "./"
        event: noMatch || toState = "/Start"

    state: createUser
        a: Чтобы оформить заказ Вам нужно зарегистрироваться на сайте фирмы МАГ.
            Шаг 1 || htmlEnabled = true, html = "Чтобы оформить заказ Вам нужно зарегистрироваться на сайте фирмы МАГ.<br><b>Шаг 1</b>"
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/oGVRHSMwwQr6dXBm.png
        a: Шаг 2
            Авторизоваться с помощью Яндекс ID или провести обычную регистрацию || htmlEnabled = true, html = "<b>Шаг 2</b><br>Авторизоваться с помощью Яндекс ID или провести обычную регистрацию"
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/qcjVvD0Ppq4oVuX7.png
        a: Шаг 3
            Выберите подходящий тип покупателя, заполните контактные данные (Они нужны нам для выставления счета и оформления доставки для Вас) || htmlEnabled = true, html = "<b>Шаг 3</b><br>Выберите подходящий тип покупателя, заполните контактные данные (Они нужны нам для выставления счета и оформления доставки для Вас)"
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/oSiygh9HGwX9dgAu.png
        a: Шаг 4
            Перейдите в свою электронную почту и подтвердите регистрацию || htmlEnabled = true, html = "<b>Шаг 4<br></b>Перейдите в свою электронную почту и подтвердите регистрацию"
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/KR4ZRtqlJ13tZA0S.PNG
        buttons:
            "Назад" -> /Start
            "Звонок менеджеру"
        event: noMatch || toState = "./"

    state: CreateOrderStep1
        a: Для оформления заказа необходимо зарегистрироваться на сайте
        buttons:
            "Как зарегистрироваться?" -> /createUser
            "Уже зарегистрирован(а)" -> /createOrder
            "Вернуться" -> /Start
        intent: /Я зарегистрирован || toState = "/createOrder"
        intent: /зарегистрироваться || toState = "/createUser"
        intent: /Вернуться || toState = "/Start"
        event: noMatch || toState = "./"

    state: createOrder
        a: Перед оформлением заказа Вам необходимо войти в учетную запись
                Шаг 1 || htmlEnabled = true, html = "Перед оформлением заказа Вам необходимо войти в учетную запись<br><b>Шаг 1</b>"
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/lO2tcpNquTxEWfor.png
        a: Шаг 2
                Введите логин (электронная почта), пароль или авторизуйтесь по номеру телефона (должен быть предварительно подтвержден) || htmlEnabled = true, html = "<b>Шаг 2</b><br>Введите логин (электронная почта), пароль или авторизуйтесь по номеру телефона (должен быть предварительно подтвержден)"
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/H7WCPHvWNwV24p8a.png
        a: После авторизации все выбранные ранее товары перенесутся в Вашу корзину
                Шаг 3
                Перейдите в корзину и нажмите на "Оформить заказ" || htmlEnabled = true, html = "<i>После авторизации все выбранные ранее товары перенесутся в Вашу корзину</i><br><br><b>Шаг 3</b><br>Перейдите в корзину и нажмите на "Оформить заказ""
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/BbI1JOyB5Hgo1Fbs.png
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/YF1giFSSySBEVGwJ.png
        a: Шаг 4
                Проверьте Ваш населенный пункт (от выбора населенного пункта зависит список доступных доставок)
                Выберите, каким способом хотите получить заказ || htmlEnabled = true, html = "<span style="letter-spacing: 0.105px;"><b>Шаг 4</b></span><br>Проверьте Ваш населенный пункт (<i>от выбора населенного пункта зависит список доступных доставок</i>)<br>Выберите, каким способом хотите получить заказ"
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/lKw5lohhVu8Etrzd.png
        a: Шаг 5
                Выберите терминал или введите адрес для доставки. Нажмите на кнопку "Оформить заказ" || htmlEnabled = true, html = "<b>Шаг 5</b><br>Выберите терминал или введите адрес для доставки. Нажмите на кнопку "Оформить заказ""
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/oemzEJYCEpj5ub26.PNG
        image: https://248305.selcdn.ru/zfl_prod/1000115138/279463839/AWgKk69zN4UYyodN.PNG
        buttons:
            "Назад" -> /Start
            "Звонок менеджера"
        intent: /Звонок || toState = "./"
        intent: /Вернуться || toState = "/Start"
        event: noMatch || toState = "./"