<html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home Page</title>
</head>
<body>

<div id="content" role="main">
    <section class="row colset-2-its">
        <form action="/hotel/findHotels" method="post" style="margin:30px auto;width: auto">
            <form class="card p-2" style="margin: 50px">
                <div class="input-group" style="width: 600px">
                    <g:textField class="form-control" style="width: 200px" name="hotel" value="${hotelName}"/>
                    <g:select class="form-control" name="selection"
                              from="${countries}"
                              value="${params.selection}"
                              optionValue="name"
                              optionKey="name"
                              noSelection="['':'Любая']"
                    />
                    <button type="submit" class="btn btn-secondary">Найти</button>
                </div>
            </form>
        </form>

    </section>

    <!--    <div class="message" role="status">${hotels.size()}</div>-->
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
                    <g:render template="hotelLineTemplate" model="[hotel: c]"/>
                </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination pag">
        <g:paginate total="${hotelCount ?: 0}"/>
    </div>
</div>

</body>
</html>