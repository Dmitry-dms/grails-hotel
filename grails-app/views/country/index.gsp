<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Справочник стран</title>
    <g:set var="maxPageElements" value="${params.max}" />
</head>
<body>
<section class="row colset-2-its">
    <form action="/country/index" method="post" style="margin:30px auto;width: auto">
        <form class="card p-2" style="margin: 50px">
            <div class="input-group" style="width: 600px">
                <g:textField class="form-control" style="width: 200px" name="countryTextInput" value="${inputSearchText}"/>
                <button type="submit" class="btn btn-secondary"><g:message code="button.search" /></button>
            </div>
        </form>
    </form>
</section>
<div id="content" role="main" style="background-color: #F5F5F5;">

    <g:render template="/crudTipsTemplate" model="[flash: flash]"/>

    <div style="display: flex;align-items: end">
        <form action="/country/create" method="post" class="create">
            <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary"><g:message code="button.create" /></button>
        </form>
    </div>
    <g:if test="${countriesCount == 0}">
        <div style="display: flex;align-items: center; margin-top:20px">
            <div class="h1-h"><g:message code="country.emptyList" /></div>
        </div>
    </g:if>
    <g:else>
        <table class="custom" style="margin-top:20px">
            <thead>
            <tr class="custom">
                <th class="custom" scope="col"><g:message code="country.name" /></th>
                <th class="custom" scope="col"><g:message code="country.capital" /></th>
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
        <g:if test="${countriesCount > maxPageElements}">
            <div class="pagination pag">
                <g:paginate total="${countriesCount ?: 0}" next="${g.message(code: "pagination.next")}" prev="${g.message(code: "pagination.prev")}"/>
            </div>
        </g:if>
    </g:else>


</div>
</body>
</html>