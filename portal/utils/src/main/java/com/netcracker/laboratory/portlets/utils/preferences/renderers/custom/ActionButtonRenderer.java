package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.laboratory.portlets.utils.json.JSONAwareClass;
import com.netcracker.laboratory.portlets.utils.json.JsonAwareRenderer;
import com.netcracker.laboratory.portlets.utils.json.JsonBean;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import org.springframework.stereotype.Component;

@View("custom/action.button")
@Component
@JSONAwareClass(ActionButtonRenderer.ActionButton.class)
public class ActionButtonRenderer extends JsonAwareRenderer<ActionButtonRenderer.ActionButton> {

    public class ActionButton extends JsonBean {
        private String actionId;
        private String css;
        private String icon;
        private String nameKey;

        public String getActionId() {
            return actionId;
        }

        public void setActionId(String actionId) {
            this.actionId = actionId;
        }

        public String getCss() {
            return css;
        }

        public void setCss(String css) {
            this.css = css;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getNameKey() {
            return nameKey;
        }

        public void setNameKey(String nameKey) {
            this.nameKey = nameKey;
        }
    }
}
