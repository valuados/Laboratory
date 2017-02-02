package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.laboratory.portlets.utils.json.JSONAwareClass;
import com.netcracker.laboratory.portlets.utils.json.JsonAwareRenderer;
import com.netcracker.laboratory.portlets.utils.json.JsonBean;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import org.springframework.stereotype.Component;

@View("custom/columns.order")
@Component
@JSONAwareClass(ColumnsOrderingRenderer.ColumnsOrdering.class)
public class ColumnsOrderingRenderer extends JsonAwareRenderer<ColumnsOrderingRenderer.ColumnsOrdering> {

    public class ColumnsOrdering extends JsonBean {
        private String columnName;

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }
    }
}
