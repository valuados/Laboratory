package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.laboratory.portlets.utils.json.JSONAwareClass;
import com.netcracker.laboratory.portlets.utils.json.JsonAwareRenderer;
import com.netcracker.laboratory.portlets.utils.json.JsonBean;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import org.springframework.stereotype.Component;

/**
 *
 */
@View("custom/bread.crumb")
@Component
@JSONAwareClass(BreadcrumbsRenderer.BreadCrumbItem.class)
public class BreadcrumbsRenderer extends JsonAwareRenderer<BreadcrumbsRenderer.BreadCrumbItem> {

    public class BreadCrumbItem extends JsonBean {
        private String url;
        private String name;
        private boolean isActive;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean isActive) {
            this.isActive = isActive;
        }
    }
}