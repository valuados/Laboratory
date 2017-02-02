package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.laboratory.portlets.utils.json.JSONAwareClass;
import com.netcracker.laboratory.portlets.utils.json.JsonAwareRenderer;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import com.netcracker.laboratory.portlets.utils.json.JsonBean;
import org.springframework.stereotype.Component;

@View("custom/hidden.columns")
@Component
@JSONAwareClass(HiddenColumnsRenderer.HiddenColumns.class)
public class HiddenColumnsRenderer extends JsonAwareRenderer<HiddenColumnsRenderer.HiddenColumns> {

    public class HiddenColumns extends JsonBean {
        private String columnName;

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }
    }
}
