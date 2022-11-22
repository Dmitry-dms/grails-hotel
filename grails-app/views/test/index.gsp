<html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home Page</title>
    <a class="home" href="${createLink(uri: '/')}">Главная</a>
</head>
<body>

<div id="content" role="main">
    <section class="row colset-2-its">
        <form action="/test/findHotels" method="post" style="margin: 0 auto; width:320px">
<!--            <input type="text" name="hotel" value="" id="hotel">-->
            <g:textField name="hotel" value="${hotelName}" />
            <input type="submit" name="Update name" value="Найти" id="Update name">
            <g:select name="selection"
                      from="${countries}"
                      value="${params.selection}"
                      optionValue="name"
                      optionKey="name"
                      noSelection="['':'Любая']"
                      />
        </form>

    </section>
    <div class="message" role="status">${hotels.size()}</div>
    <f:table collection="${hotels}" />
<!--    <ul>-->
<!--        <g:each var="c" in="${hotels}">-->
<!--            <li class="controller">-->
<!--                <g:link resource="${c}">${c.name}</g:link>-->
<!--            </li>-->
<!--        </g:each>-->
<!--    </ul>-->
    <div class="pagination">
        <g:paginate total="${hotelCount ?: 0}" />
    </div>
</div>

</body>
</html>