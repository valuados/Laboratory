<#if salesOrder??>
<div class="row">
    <#list salesOrder.orders as order>
        <#if order.offer.name?has_content>
            <div class="span3">
                <div class="thumbnail">
                    <a class="offer-item" >
                        <img src="/common-portlets/images/${order.offer.name}.png" alt="Pulpit Rock" style="width:100%">
                        <div class="offer-title">
                            <h4>${order.offer.name}</h4>
                        </div>
                    </a>
                </div>
            </div>
        </#if>
    </#list>
</div>
<div class="row" style="margin-top: 30px;">
    <div class="span2">
        <button class="btn btn-large btn-info js-click-save-so" style="width: 100px; height: 50px">Submit</button>
    </div>
</div>
<#else>
    Your cart is empty.
</#if>