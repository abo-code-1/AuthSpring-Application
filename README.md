# AuthSpring-Application Безопасность с JWT

Этот проект демонстрирует реализацию безопасности с использованием Spring Boot 3.0 и JSON Web Tokens (JWT).

## Архитектура приложения

![Spring Security JWT][(https://raw.githubusercontent.com/USERNAME/REPO_NAME/BRANCH/images/security-jwt.png](https://github.com/abo-code-1/AuthSpring-Application/blob/main/AuthSpring-Architecture.png))

## Возможности
- Регистрация и вход пользователей с аутентификацией через JWT
- Шифрование паролей с использованием BCrypt
- Авторизация на основе ролей с Spring Security
- Настроенная обработка ошибок доступа
- Механизм выхода из системы
- Поддержка обновления токена

## Используемые технологии
- **Spring Boot 3.0** – основной фреймворк
- **Spring Security** – аутентификация и авторизация
- **JSON Web Tokens (JWT)** – аутентификация на основе токенов
- **BCrypt** – безопасное хеширование паролей
- **Maven** – управление зависимостями

## Предварительные требования
Перед началом работы убедитесь, что у вас установлены:
- **JDK 17+**
- **Maven 3+**
- **PostgreSQL** (для настройки базы данных)

## Начало работы
Следуйте этим шагам, чтобы настроить и запустить проект:

1. **Клонируйте репозиторий:**
   ```sh
   git clone https://github.com/ali-bouali/spring-boot-3-jwt-security.git
   ```
2. **Перейдите в директорию проекта:**
   ```sh
   cd spring-boot-security-jwt
   ```
3. **Настройте базу данных:**
   - Создайте базу данных PostgreSQL с именем `jwt_security`.
4. **Соберите проект:**
   ```sh
   mvn clean install
   ```
5. **Запустите проект:**
   ```sh
   mvn spring-boot:run
   ```

