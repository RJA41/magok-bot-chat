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

    state: CreateOrderStep1
        a: Для оформления заказа необходимо зарегистрироваться на сайте
        buttons:
            "Как зарегистрироваться?" -> /createUser
            "Уже зарегистрирован(а)"
            "Вернуться" -> /Start
        intent: /Я зарегистрирован || toState = "./"
        event: noMatch || toState = "./"