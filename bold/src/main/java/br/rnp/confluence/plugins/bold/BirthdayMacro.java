package br.rnp.confluence.plugins.bold;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;

import java.util.Map;

public class BirthdayMacro implements Macro {
    public String execute(Map<String, String> parameters,
                          String body,
                          ConversionContext conversionContext)
            throws MacroExecutionException {
        String [][] birth = new String[][] {{"João Janeiro", "01/01"},{"João Fevereiro", "01/02"}};
        if (parameters == null) {
            return "N/A";
        } else {
            String month = parameters.get("month");
            if (month == null) {
                return "N/A";
            } else {
                int m = Integer.parseInt(month);
                String line = String.format("<tr><td>%s</td></tr>", birth[m - 1]);
                return String.format("<table>%s</table>", line);
            }
        }
    }

   public BodyType getBodyType() {
        return BodyType.NONE;
   }

   public OutputType getOutputType() {
        return OutputType.BLOCK;
   }
}
