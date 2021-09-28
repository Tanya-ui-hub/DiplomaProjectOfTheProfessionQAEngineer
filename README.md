# DiplomaProjectOfTheProfessionQAEngineer [![Build status](https://ci.appveyor.com/api/projects/status/kdh5ky7tuqt4vm38?svg=true)](https://ci.appveyor.com/project/Tanya-ui-hub/diplomaprojectoftheprofessionqaengineer)

# Дипломный проект профессии «Тестировщик»

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API
Банка.

## Документация

[План автоматизации тестирования веб-формы сервиса покупки туров интернет-банка](documents/Plan.md)

[Отчёт о проведенном тестировании](documents/Report.md)

[Отчёт о проведённой автоматизации](documents/Summary.md)

## Запуск приложения

Перед запуском необходимо выполнить следующие предусловия. Если у вас уже есть необходимое ПО, то понадобится только п.1 и запуск Docker.

*Предусловия:*
1. Необходимо склонировать репозиторий или скачать архив по [ссылке](https://github.com/Tanya-ui-hub/DiplomaProjectOfTheProfessionQAEngineer.git). Или воспользоваться VCS Git, встроенной в
   IntelliJ IDEA.
2. Установить и запустить Docker Desktop. Это можно сделать [здесь](https://docs.docker.com/get-docker/) в зависимости от операционной системы. Дополнительные инструкции по установке Docker [ссылке](https://github.com/netology-code/aqa-homeworks/blob/master/docker/installation.md)
3. Открыть проект в IntelliJ IDEA

### Запуск

### Техническая часть

Само приложение расположено в файле [`aqa-shop.jar`](artifacts/aqa-shop.jar) и запускается стандартным способом `java -jar artifacts/aqa-shop.jar` на порту 8080.
