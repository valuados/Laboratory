package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.laboratory.portlets.utils.json.JSONAwareClass;
import com.netcracker.laboratory.portlets.utils.json.JsonAwareRenderer;
import com.netcracker.laboratory.portlets.utils.json.JsonBean;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import org.springframework.stereotype.Component;

@View("custom/column.alias")
@Component
@JSONAwareClass(ColumnAliasRenderer.ColumnAlias.class)
public class ColumnAliasRenderer extends JsonAwareRenderer<ColumnAliasRenderer.ColumnAlias> {

    public class ColumnAlias extends JsonBean {
        private String columnName;
        private String columnAlias;

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnAlias() {
            return columnAlias;
        }

        public void setColumnAlias(String columnAlias) {
            this.columnAlias = columnAlias;
        }
    }
}
