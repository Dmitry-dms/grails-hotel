<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="card mx-auto" style="width:400px">
    <div id="create-country" class="content scaffold-create" role="main">
        <g:if test="${flash.error}">
            <ul class="errors" role="alert">
                <g:message error="${flash.error}"/>
            </ul>
        </g:if>
        <g:form resource="${this.country}" method="POST" action="update" >
            <fieldset class="form" >
                <f:field bean="country" property="name" label="${g.message(code: "country.update.name")}" />
                <f:field bean="country" property="capital" label="${g.message(code: "country.update.capital")}"/>
                <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary" style="margin-top:20px; right:0;"><g:message code="button.update" /></button>
            </fieldset>
        </g:form>
    </div>
</div>

</body>
</html>