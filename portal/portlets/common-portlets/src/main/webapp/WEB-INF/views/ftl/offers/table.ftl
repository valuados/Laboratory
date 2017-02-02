<#if offers??>
    <div class="row">
        <#list offers as offer>
            <#if offer.name?has_content>
                <div class="span3">
                    <div class="thumbnail <#if salesOrder??><#list salesOrder.orders as order><#if order.offer.id == offer.id>selected-order</#if></#list></#if>">
                        <a class="offer-item" data-id-offer="${offer.id}">
                            <img src="/common-portlets/images/${offer.name}.png" alt="Pulpit Rock" style="width:100%">
                            <div class="offer-title">
                                <h4>${offer.name}</h4>
                            </div>
                        </a>
                    </div>
                </div>
            </#if>
        </#list>
    </div>
<#else>
There're no offers to display.
</#if>