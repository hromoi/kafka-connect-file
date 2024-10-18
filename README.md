Описание проекта
Этап 1: Валидация
Описание:
На первом этапе происходит валидация входящих файлов. Проверяются корректность имени файла, длина строк, наличие разделителей и уникальность записей.
Реализация основана на паттерне "цепочка ответственности", позволяющем гибко изменять порядок проверок, а также добавлять или удалять этапы валидации.
Если файл не проходит проверку, он помечается как невалидный и перемещается в соответствующую директорию.
Настройки валидации загружаются из внешнего файла config.properties, что позволяет изменять правила без перекомпиляции кода, просто перезапустив Kafka Connect.
Этап 2: Парсинг
Описание:
На втором этапе производится парсинг строк валидных файлов в объекты, включая извлечение идентификатора банка из имени файла.
Обработанные файлы перемещаются в директорию для обработанных файлов.
Настройки парсинга загружаются из config.properties.

0) Убедитесь что у вас установлен Docker
1) Создайте следующую структуру файлов и директорий:
project/
├── docker-compose.yml
├── config.properties
├── plugins/
│   └── kafka-connect-filepulse/ (папка с распакованным FilePulse плагином)
│   └── custom-processor/ (папка с вашим кастомным процессором в виде JAR файла)
├── source-directory/ (для входящих файлов)
├── valid-directory/ (для валидных файлов)
├── invalid-directory/ (для невалидных файлов)
└── processed-directory/ (для обработанных файлов)

2) Настройте FilePulse плагин:

 Загрузите плагин с официального репозитория.
 Распакуйте его в project/plugins/kafka-connect-filepulse/.
 
3) **Создайте JAR файл кастомного процессора и положите в project/plugins/custom-processor/.

4) **Обновите docker-compose.yml и конфигурационные файлы, указав нужные параметры.

5) **Перемонтируйте тома и запустите проект с помощью docker-compose up -d.

