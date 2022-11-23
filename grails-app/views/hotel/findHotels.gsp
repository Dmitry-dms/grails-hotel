<html>
<head>
    <meta name="layout" content="main"/>
    <title>Справочник отелей</title>
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
        <g:if test="${hotelCount != 0}">
            <div class="message" role="status">Найдено ${hotelCount} отелей</div>
        </g:if>
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
                <g:paginate total="${hotelCount ?: 0}" next="Вперед" prev="Назад"/>
            </div>
        </g:if>
    </g:else>

</div>
</body>

</html>