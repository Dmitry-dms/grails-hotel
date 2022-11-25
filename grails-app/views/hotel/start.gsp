<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Главная</title>
</head>
<body>
<div style="display: flex;align-items: center">
    <div class="index-h"><g:message code="main.start_search" /></div>
</div>
<section class="row colset-2-its">
    <form action="/hotel/findHotels" method="post" style="margin:30px auto;width: auto">
        <form class="card p-2" style="margin: 50px">
            <div class="input-group" style="width: 600px">
                <g:textField class="form-control" style="width: 200px" name="hotelTextInput" value="${searchString}"/>
                <g:select class="form-control" name="selectedCountry"
                          from="${countries}"
                          optionValue="name"
                          optionKey="name"
                          noSelection="['':'Любая']"
                />
                <button type="submit" class="btn btn-secondary"><g:message code="button.search" /></button>
            </div>
        </form>
    </form>

</section>
</body>

</html>