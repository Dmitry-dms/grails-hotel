<!doctype html>
<html>
    <head>
        <title>Page Not Found</title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <ul class="errors">
            <li>Ошибка: Страница не найдена (404)</li>
            <li>Путь: ${request.forwardURI}</li>
        </ul>
    </body>
</html>
