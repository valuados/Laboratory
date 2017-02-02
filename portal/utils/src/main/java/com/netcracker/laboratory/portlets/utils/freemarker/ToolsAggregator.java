package com.netcracker.laboratory.portlets.utils.freemarker;

import com.netcracker.laboratory.portlets.utils.freemarker.tools.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 */
@Component("freemarkerToolsAggregator")
public class ToolsAggregator {

    @Autowired
    private Map<String, Tool> tools;

    public Map<String, Tool> getTools() {
        return tools;
    }
}
