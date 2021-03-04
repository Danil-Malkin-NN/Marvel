# Как запустить этот проект в docker

1. создать параметры среды в .env 
    * SPRING_DATASOURCE_URL - url базы данных по которому подключится спринг 
      `jdbc:postgresql://db:5432/postgres`
    * POSTGRES_PASSWORD пароль для БД
      
    * POSTGRES_USER имя пользователя БД
    * POSTGRES_DB название БД
    
2. собрать jar файл.

3. `docker-compose up`


# Как запустить этот проект без docker
1. Для запуска проекта вне докера вам необходиммо создать файл `local.properties`
2. Переопределить в нём переменные для поключения к вашей БД.

