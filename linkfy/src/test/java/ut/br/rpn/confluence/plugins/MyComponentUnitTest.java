package ut.br.rpn.confluence.plugins;

import org.junit.Test;
import br.rpn.confluence.plugins.api.MyPluginComponent;
import br.rpn.confluence.plugins.impl.MyPluginComponentImpl;

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