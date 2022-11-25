<!doctype html>
<html>
    <head>
        <title>Страница не найдена</title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <ul class="errors">
            <li><g:message code="main.not_found" /></li>
            <li><g:message code="main.not_found.path" />${request.forwardURI}</li>
        </ul>
    </body>
</html>
