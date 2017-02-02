<#-- @ftlvariable name="salesOrder" type="java.util.Collection<netcracker.entities.SalesOrder>" -->

<#if salesOrder??><!-- && salesOrder.orders?has_content-->
<table class="orders__table _auto">
    <tbody class="orders__table_body">
        <#list salesOrder.orders as order>
            <tr class="thumbnail order-item" data-id-order="${order.id}" data-id-category="${order.offer.categoryId}">
                <td class="orders__cell">
                    <span class="orders__param orders__status _active">${order.offer.name}</span>
                </td>
                <td class="orders__cell">
                    <button class="btn btn-danger icon-remove js-click-remove-order"></button>
                </td>
            </tr>
        </#list>
    </tbody>
</table>
<#else>
There is no any orders in your cart
</#if>