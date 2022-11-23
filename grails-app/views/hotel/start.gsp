<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'hotel.label', default: 'Hotel')}"/>
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div style="display: flex;align-items: center">
    <div class="index-h">Начните поиск</div>
</div>
<section class="row colset-2-its">
    <form action="/hotel/findHotels" method="post" style="margin:30px auto;width: auto">
        <form class="card p-2" style="margin: 50px">
            <div class="input-group" style="width: 600px">
                <g:textField class="form-control" style="width: 200px" name="search_subsctr" value="${searchString}"/>
                <g:select class="form-control" name="selectedCountry"
                          from="${countries}"
                          optionValue="name"
                          optionKey="name"
                          noSelection="['':'Любая']"
                />
                <button type="submit" class="btn btn-secondary">Найти</button>
            </div>
        </form>
    </form>

</section>
</body>

</html>