package com.netcracker.laboratory.portlets.utils.preferences.renderers.custom;

import com.netcracker.laboratory.portlets.utils.json.JSONAwareClass;
import com.netcracker.laboratory.portlets.utils.json.JsonAwareRenderer;
import com.netcracker.laboratory.portlets.utils.json.JsonBean;
import com.netcracker.laboratory.portlets.utils.preferences.annotations.View;
import org.springframework.stereotype.Component;

@View("custom/config.characteristic")
@Component
@JSONAwareClass(ServiceConfigRenderer.ConfigItem.class)
public class ServiceConfigRenderer extends JsonAwareRenderer<ServiceConfigRenderer.ConfigItem> {

    public class ConfigItem extends JsonBean {
        private String characteristicId;
        private String renderer;

        public String getCharacteristicId() {
            return characteristicId;
        }

        public void setCharacteristicId(String characteristicId) {
            this.characteristicId = characteristicId;
        }

        public String getRenderer() {
            return renderer;
        }

        public void setRenderer(String renderer) {
            this.renderer = renderer;
        }
    }
}
