<div class="table-row" id="${hotel?.id}">

    <td class="custom">
        ${hotel?.name}
        <g:if test="${hotel.site != null}">
            <g:link target="_blank" base="${hotel.site}"><g:message code="table.site" /></g:link>
        </g:if>
    </td>

    <td class="custom">
        <g:each var="c" in="${1..hotel?.stars}">
            <img alt="Image placeholder"
                 src="https://cdn-icons-png.flaticon.com/512/2107/2107957.png" width="24" height="24">
        </g:each>
    </td>

</div>