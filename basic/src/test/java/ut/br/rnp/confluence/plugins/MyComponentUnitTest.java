package ut.br.rnp.confluence.plugins;

import org.junit.Test;
import br.rnp.confluence.plugins.api.MyPluginComponent;
import br.rnp.confluence.plugins.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}