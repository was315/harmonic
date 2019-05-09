package org.harmonic.core.process;

import org.harmonic.core.module.Module;

/**
 * @author Shuai Wang
 * @date 2019/5/10
 */
public class SerialProcessor extends AbstractProcessor {



    @Override
    public boolean invoke() {

        return false;
    }



    @Override
    public boolean setIfNextLegal(Processor processor) {
        return false;
    }
}
