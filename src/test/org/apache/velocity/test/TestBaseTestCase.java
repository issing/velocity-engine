package org.apache.velocity.test;

/*
 * Copyright 2001-2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * I keep breaking the getFileName method all the time...
 */
public class TestBaseTestCase
        extends BaseTestCase
{
    public TestBaseTestCase(final String name)
    {
        super(name);
    }

    public static Test suite()
    {
        return new TestSuite(TestBaseTestCase.class);
    }

    public void testGetFileName()
    	throws Exception
    {
        String fs = System.getProperty("file.separator");
	String pwd = System.getProperty("user.dir");

        String root = new File("/").getCanonicalPath();

        assertEquals(pwd + fs + "baz" + fs + "foo.bar", getFileName("baz",        "foo",  "bar"));
        assertEquals(root     + "baz" + fs + "foo.bar", getFileName(root + "baz", "foo",  "bar"));
        assertEquals(root + "foo.bar",                  getFileName("baz",        root + "foo", "bar"));
        assertEquals(root + "foo.bar",                  getFileName(root + "baz", root + "foo", "bar"));
        assertEquals(".",                               getFileName(null,         "",     ""));
        assertEquals(root + ".",                        getFileName("",           "",     ""));
        assertEquals("foo.bar",                         getFileName(null,         "foo",  "bar"));
        assertEquals(root + "foo.bar",                  getFileName(null,         root + "foo", "bar"));
        assertEquals(root + "foo.bar",                  getFileName("",           "foo",  "bar"));
        assertEquals(root + "foo.bar",                  getFileName("",           root + "foo", "bar"));
    }
}
