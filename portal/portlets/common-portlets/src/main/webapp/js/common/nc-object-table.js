var openedFilter;

function initTableFiltration (jqTableContainer) {
    jqTableContainer.find('.dataTables_scrollHead').css('position', 'static');

    jqTableContainer.find('.dashboardFilter').click(function () {
        jQuery(this).parents('th').blur();
        if (jQuery(this).hasClass('dashboardFilterOn')) {
            changeBack(jQuery(this).parent(), false);
        } else {
            openedFilter = jQuery(this).parent();
            changeBack(openedFilter, true);
            var filterStr = openedFilter.find('.filterString').get(0);
            var filterLen = filterStr.value.length;
            filterStr.setSelectionRange(filterLen, filterLen);
        }
        return false;
    });

    jqTableContainer.find('.filterContent').click(function(){
        jQuery(this).parents('th').blur();
        return false;
    });

    jqTableContainer.find('.filterDisabler').click(function(){
        var filterStr = jQuery(this).parents('.filterContent').find('.filterString');
        filterStr.val('');
        filterStr.keyup();
        changeBack(openedFilter, false);
    });

    jqTableContainer.find('.filterString').keyup(function () {
        filterColumn(this);
    });

    jqTableContainer.find('.filterString').blur(function () {
        filterColumn(this);
    });

    jqTableContainer.find('.tableSelectAll').click(function () {
        var oTable = jqTableContainer.find('.dataTables_scrollBody > table').dataTable();
        jQuery('input', oTable.fnGetNodes()).attr('checked', this.checked);
    });
}

function filterColumn(domFilterStr){
    var jqFilterStr = jQuery(domFilterStr);
    var filterStr = jqFilterStr.val();
    var tableContainer = jqFilterStr.parents('.tableContainer');
    var filterHolder = jqFilterStr.parents('.dashboardFilterHolder');
    var allTH = tableContainer.find('.dataTables_scrollHead th');
    var currentTH = jqFilterStr.parents('th');
    var colNumber = allTH.index(currentTH);
    var oTable = tableContainer.find('.dataTables_scrollBody > table').dataTable();
    var preFilterStr = oTable.fnSettings().aoPreSearchCols[colNumber].sSearch;
    if(preFilterStr !== filterStr) {
        oTable.fnFilter(filterStr, colNumber, false, true, true, true);
        filterHolder.toggleClass('dashboardFilterApplied', filterStr !== '');
    }
}

function changeBack(source, filterOn) {
    if (filterOn) {
        source.children(".dashboardFilter").addClass('dashboardFilterOn');
        source.children(".filterContent").show('fast');
    }
    else {
        source.children(".dashboardFilter").removeClass('dashboardFilterOn');
        source.children(".filterContent").hide('fast');
    }
}

jQuery (document).ready(function () {
    jQuery(document).mouseup(function (e) {
        if (openedFilter != undefined && openedFilter.has(e.target).length === 0) {
            changeBack(openedFilter, false);
        }
        return false;
    });
});


//Custom sorting type functions:
jQuery.fn.dataTableExt.oSort['objectId-asc']  = function(x,y) {
    var xId = jQuery(x).attr("objectId");
    var yId = jQuery(y).attr("objectId");
    for(var i=0; i<xId.length; i++){
        var charX = parseInt(xId.charAt(i));
        var charY = parseInt(yId.charAt(i));
        if(charX < charY) return -1;
        if(charX > charY) return 1;
    }
    return 0;
};

jQuery.fn.dataTableExt.oSort['objectId-desc'] = function(x,y) {
    var xId = jQuery(x).attr("objectId");
    var yId = jQuery(y).attr("objectId");
    for(var i=0; i<xId.length; i++){
        var charX = parseInt(xId.charAt(i));
        var charY = parseInt(yId.charAt(i));
        if(charX < charY) return 1;
        if(charX > charY) return -1;
    }
    return 0;
};