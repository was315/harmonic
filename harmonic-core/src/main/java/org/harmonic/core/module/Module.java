package org.harmonic.core.module;

import org.harmonic.core.process.Processor;

import java.lang.reflect.Method;

/**
 * A module is defined as a basic element of this project (harmonic). Class and Process
 * composed of {@link Processor} is used as a module.
 * The type of input parameters and output (return parameter) should be defined here as well as
 * the entry method. An entry method is the entrance of this module.
 * If an instance of the module is injected into a {@link Processor} instance, it
 * can be executed.
 *
 * @author Shuai Wang
 * @date 2019/5/6
 */
public interface Module<T> {

    /**
     * Set module a name, use default if not set.
     * @param name
     */
    void setModuleName(String name);

    /**
     * Get the name of this module.
     * @return {@code String} Module name
     */
    String getModuleName();

    /**
     * Set the instance of Module.
     * @param instance a Instance of Class or Process.
     */
    void setInstance(T instance);

    /**
     * Get the instance.
     * @return
     */
    T getInstance();

    /**
     * Set the entry method of module.
     * @param entryMethod
     */
    void setEntryMethod(Method entryMethod);

    /**
     * Get the entry method.
     * @return
     */
    Method getEntryMethod();

    /**
     * Get the Input Parameters type.
     * @return
     */
    Class[] getInputTypes();

    /**
     * Get the output (return parameter) type.
     * @return
     */
    Class getOutputType();


}
