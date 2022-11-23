<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Справочник стран</title>
</head>
<body>
<section class="row colset-2-its">
    <form action="/country/findCountries" method="post" style="margin:30px auto;width: auto">
        <form class="card p-2" style="margin: 50px">
            <div class="input-group" style="width: 600px">
                <g:textField class="form-control" style="width: 200px" name="search_subsctr" value="${searchString}"/>
                <button type="submit" class="btn btn-secondary">Найти</button>
            </div>
        </form>
    </form>
</section>
<div id="content" role="main" style="background-color: #F5F5F5;">
    <div style="display: flex;align-items: end">
        <form action="/country/create" method="post" class="create">
            <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary">Создать</button>
        </form>
    </div>
    <g:if test="${countriesCount == 0}">
        <div style="display: flex;align-items: center; margin-top:20px">
            <div class="h1-h">Список стран пуст</div>
        </div>
    </g:if>
    <g:else>
        <table class="custom" style="margin-top:20px">
            <thead>
            <tr class="custom">
                <th class="custom" scope="col">Страна</th>
                <th class="custom" scope="col">Столица</th>
            </tr>
            </thead>
            <tbody>
            <g:each var="c" in="${countries}">
                <tr class="custom">
                    <g:render template="countryTableRowTemplate" model="[country: c]"/>
                </tr>
            </g:each>
            </tbody>
        </table>
        <g:if test="${countriesCount > 10}">
            <div class="pagination pag">
                <g:paginate total="${countriesCount ?: 0}" next="Вперед" prev="Назад"/>
            </div>
        </g:if>
    </g:else>


</div>
</body>
</html>