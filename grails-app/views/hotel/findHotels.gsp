<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'hotel.label', default: 'Hotel')}"/>
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div id="content" role="main">
    <g:if test="${hotelCount == 0}">
        <h1>По вашему запросу ничего не найдено</h1>
    </g:if>
    <g:else>
        <form action="/" method="post" style="margin:30px auto;width: auto">
            <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary fab">Новый поиск</button>
        </form>
        <table class="custom">
            <thead>
            <tr class="custom">
                <th class="custom" scope="col">Название</th>
                <th class="custom" scope="col">Звездность</th>
            </tr>
            </thead>
            <tbody>
            <g:each var="c" in="${hotels}">
                <tr class="custom">
                    <g:render template="hotelTableRowTemplate" model="[hotel: c]"/>
                </tr>
            </g:each>
            </tbody>
        </table>
        <g:if test="${hotelCount > 10}">
            <div class="pagination pag">
                <g:paginate total="${hotelCount ?: 0}"/>
            </div>
        </g:if>
    </g:else>

</div>
</body>

</html>