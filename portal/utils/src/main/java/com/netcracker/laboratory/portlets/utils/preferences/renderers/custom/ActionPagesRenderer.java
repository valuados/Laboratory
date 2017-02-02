package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.laboratory.portlets.utils.json.JSONAwareClass;
import com.netcracker.laboratory.portlets.utils.json.JsonAwareRenderer;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import com.netcracker.laboratory.portlets.utils.json.JsonBean;
import org.springframework.stereotype.Component;

@View("custom/action.page")
@Component
@JSONAwareClass(ActionPagesRenderer.ActionPage.class)
public class ActionPagesRenderer extends JsonAwareRenderer<ActionPagesRenderer.ActionPage> {

    public class ActionPage extends JsonBean {
        private String offerId;
        private String pageId;

        public String getOfferId() {
            return offerId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }

        public String getPageId() {
            return pageId;
        }

        public void setPageId(String pageId) {
            this.pageId = pageId;
        }
    }
}
