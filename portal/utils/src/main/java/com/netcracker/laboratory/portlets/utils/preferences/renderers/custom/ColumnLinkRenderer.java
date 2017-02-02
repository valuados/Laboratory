package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.laboratory.portlets.utils.json.JSONAwareClass;
import com.netcracker.laboratory.portlets.utils.json.JsonAwareRenderer;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import com.netcracker.laboratory.portlets.utils.json.JsonBean;
import org.springframework.stereotype.Component;

@View("custom/column.link")
@Component
@JSONAwareClass(ColumnLinkRenderer.ColumnLink.class)
public class ColumnLinkRenderer extends JsonAwareRenderer<ColumnLinkRenderer.ColumnLink> {

    public class ColumnLink extends JsonBean {
        private String linkUrl;
        private String linkParam;
        private String linkTitle;
        private String columnName;

        public String getLinkParam() {
            return linkParam;
        }

        public void setLinkParam(String linkParam) {
            this.linkParam = linkParam;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getLinkTitle() {
            return linkTitle;
        }

        public void setLinkTitle(String linkTitle) {
            this.linkTitle = linkTitle;
        }
    }
}
