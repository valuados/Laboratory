package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.laboratory.portlets.utils.json.JSONAwareClass;
import com.netcracker.laboratory.portlets.utils.json.JsonAwareRenderer;
import com.netcracker.laboratory.portlets.utils.json.JsonBean;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import org.springframework.stereotype.Component;

@View("custom/styled.value")
@Component
@JSONAwareClass(StyledValueRenderer.StyledValue.class)
public class StyledValueRenderer extends JsonAwareRenderer<StyledValueRenderer.StyledValue> {

    public class StyledValue extends JsonBean {
        private String cellValue;
        private String cellStyle;

        public String getCellStyle() {
            return cellStyle;
        }

        public void setCellStyle(String cellStyle) {
            this.cellStyle = cellStyle;
        }

        public String getCellValue() {
            return cellValue;
        }

        public void setCellValue(String cellValue) {
            this.cellValue = cellValue;
        }
    }
}
