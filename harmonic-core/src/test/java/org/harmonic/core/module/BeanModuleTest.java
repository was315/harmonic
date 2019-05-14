package org.harmonic.core.module;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Shuai Wang
 * @date 2019/5/15
 */
public class BeanModuleTest {

    @Test
    public void testModule() throws NoSuchMethodException {
        Module<Integer> integerModule = new BeanModule<>();
        Integer i = 1;
        integerModule.setInstance(i);
        assert "Module[java.lang.Integer]".equals(integerModule.getModuleName());

        integerModule.setModuleName("TestInt");
        assert "TestInt".equals(integerModule.getModuleName());

        Integer instance = integerModule.getInstance();
        assert instance == i;

        Method m = i.getClass().getMethod("valueOf", int.class);
        integerModule.setEntryMethod(m);

        Method entryMethod = integerModule.getEntryMethod();
        assert m == entryMethod;
        assert Arrays.equals(integerModule.getInputTypes(), new Class[]{int.class});
        assert integerModule.getOutputType().equals(Integer.class);
    }
}
