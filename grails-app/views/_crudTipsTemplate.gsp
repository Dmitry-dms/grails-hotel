<g:if test="${flash.save}">
    <div class="message" role="status">${flash.save}</div>
</g:if>
<g:if test="${flash.update}">
    <div class="message" role="status">${flash.update}</div>
</g:if>
<g:if test="${flash.delete}">
    <div class="message" role="status">${flash.delete}</div>
</g:if>
<g:if test="${flash.delete_error}">
    <ul class="errors" role="alert">
        <g:message error="${flash.delete_error}"/>
    </ul>
</g:if>