package org.apache.velocity.anakia;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */

import java.io.IOException;
import java.io.StringWriter;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

/**
 * This class extends XMLOutputter in order to provide
 * a way to walk an Element tree into a String.
 *
 * @author <a href="mailto:jon@latchkey.com">Jon S. Stevens</a>
 * @author <a href="mailto:rubys@us.ibm.com">Sam Ruby</a>
 * @version $Id$
 */
public class OutputWrapper
{
    private XMLOutputter outputter;

    /**
     * Empty constructor
     */
    public OutputWrapper()
    {
        outputter = new XMLOutputter();
    }

    /**
     * @param f
     */
    public OutputWrapper(Format f)
    {
        outputter = new XMLOutputter(f);
    }

    /**
     * This method walks an Element tree into a String. The cool
     * thing about it is that it will strip off the first Element.
     * For example, if you have:
     * <p>
     * &lt;td&gt; foo &lt;strong&gt;bar&lt;/strong&gt; ack &lt;/td&gt;
     * </p>
     * It will output
     * <p>
     *  foo &lt;strong&gt;bar&lt;/strong&gt; ack &lt;/td&gt;
     * </p>
     * @param element
     * @param strip
     * @return The output string.
     */
    public String outputString(Element element, boolean strip)
    {
        StringWriter buff = new StringWriter();

        try
        {
            outputter.outputElementContent(element, buff);
        }
        catch (IOException e)
        {
        }
        return buff.toString();
    }
}
