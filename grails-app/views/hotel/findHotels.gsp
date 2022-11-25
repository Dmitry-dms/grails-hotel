<html>
<head>
    <meta name="layout" content="main"/>
    <title>Справочник отелей</title>
</head>
<body>
<div id="content" role="main" style="background-color: #F5F5F5;">
    <form action="/" method="post" style="margin:30px auto;width: auto">
        <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary fab"><g:message code="button.new_search" /></button>
    </form>
    <g:if test="${hotelCount == 0}">
        <div style="display: flex;align-items: center; margin-top:20px">
            <div class="h1-h"><g:message code="nothing" /></div>
        </div>
    </g:if>
    <g:else>
        <g:if test="${hotelCount != 0}">
            <div class="message" role="status"><g:message code="hotel.founded" args="${hotelCount}" /></div>
        </g:if>
        <table class="custom">
            <thead>
            <tr class="custom">
                <th class="custom" scope="col"><g:message code="hotel.name" /></th>
                <th class="custom" scope="col"><g:message code="hotel.stars" /></th>
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
                <g:paginate total="${hotelCount ?: 0}" next="${g.message(code: "pagination.next")}" prev="${g.message(code: "pagination.prev")}"/>
            </div>
        </g:if>
    </g:else>

</div>
</body>

</html>