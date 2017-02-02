<#-- @ftlvariable name="students" type="java.util.Collection<netcracker.entities.Student>" -->

<#if students??>
<table class="orders__table _auto" id="orders__table" paging="${paging!'0'}">
    <thead class="orders__table_head">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Remove</th>
        <th>Edit</th>
    </tr>
    </thead>
    <tbody class="orders__table_body">
        <#list students as student>
            <#if student.name?has_content>
            <tr class="orders__row student-item" data-id-student="${student.id}">
                <td class="orders__cell">
                    <span class="orders__param">${student.id!'0'}</span>
                </td>
                <td class="orders__cell">
                    <span class="orders__param">${student.name!'Name'}</span>
                </td>
                <td class="orders__cell">
                    <span class="orders__param orders__status _active">${student.surname!'Surname'}</span>
                </td>
                <td class="orders__cell">
                    <button class="btn btn-danger icon-remove js-click-remove-student"></button>
                </td>
                <td class="orders__cell">
                    <button class="btn btn-info icon-edit js-click-edit-student"></button>
                </td>
            </tr>
            </#if>
        </#list>
    </tbody>
</table>
<#else>
There're no students to display.
</#if>