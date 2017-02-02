package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.laboratory.portlets.utils.json.JSONAwareClass;
import com.netcracker.laboratory.portlets.utils.json.JsonAwareRenderer;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import com.netcracker.laboratory.portlets.utils.json.JsonBean;
import org.springframework.stereotype.Component;

@View("custom/url.parameters")
@Component
@JSONAwareClass(UrlParametersRenderer.UrlParametersItem.class)
public class UrlParametersRenderer extends JsonAwareRenderer<UrlParametersRenderer.UrlParametersItem> {

    public class UrlParametersItem extends JsonBean {
        private String name;
        private String parameter;
        private String url;
        private String pageId;

        private boolean isActive;
        private boolean isHidden;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean isActive) {
            this.isActive = isActive;
        }

        public String getPageId() {
            return pageId;
        }

        public void setPageId(String pageId) {
            this.pageId = pageId;
        }

        public boolean isHidden() {
            return isHidden;
        }

        public void setIsHidden(boolean isHidden) {
            this.isHidden = isHidden;
        }

    }
}