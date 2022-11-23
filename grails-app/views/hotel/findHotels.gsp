<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'hotel.label', default: 'Hotel')}"/>
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div id="content" role="main" style="background-color: #F5F5F5;">
    <form action="/" method="post" style="margin:30px auto;width: auto">
        <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary fab">Новый поиск</button>
    </form>
    <g:if test="${hotelCount == 0}">
        <div style="display: flex;align-items: center; margin-top:20px">
            <div class="h1-h">По вашему запросу ничего не найдено</div>
        </div>
    </g:if>
    <g:else>
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