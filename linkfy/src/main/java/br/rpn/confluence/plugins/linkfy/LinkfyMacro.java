package br.rpn.confluence.plugins.linkfy;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;

import java.util.Map;

public class LinkfyMacro implements Macro {
    public String execute(Map<String, String> parameters, String body, ConversionContext conversionContext) throws MacroExecutionException {
        if (parameters == null) {
            return "NO PARAM";
        }
        String uri = parameters.get("uri");
        if (uri == null) {
            return "NO URI";
        } else {
            String protocol = "";
            if (uri.contains("@")) {
                protocol = "emailTo:";
            } else {
                try {
                    Integer.parseInt(uri);
                    protocol = "tel:";
                } catch (NumberFormatException exception) {

                }
            }
            return String.format("<a href='%s%s'>%s</a>", protocol, uri, uri);
        }
    }

    public BodyType getBodyType() {
        return BodyType.NONE;
    }

    public OutputType getOutputType() {
        return OutputType.BLOCK;
    }
}