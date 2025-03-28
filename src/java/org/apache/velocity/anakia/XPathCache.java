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

import org.jdom2.Element;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Provides a cache for XPath expressions. Used by {@link NodeList} and
 * {@link AnakiaElement} to minimize XPath parsing in their
 * <code>selectNodes()</code> methods.
 */
class XPathCache {
    // Cache of already parsed XPath expressions, keyed by String representations
    private static final Map<String, XPathExpression<Element>> XPATH_CACHE = new WeakHashMap<>();

    private XPathCache() {
    }

    /**
     * Returns an XPathExpression object representing the requested XPath expression.
     * A cached object is returned if it already exists for the requested expression.
     *
     * @param xpathString the XPath expression to parse
     * @return the XPathExpression object that represents the parsed XPath expression.
     */
    static XPathExpression<Element> getXPath(String xpathString) {
        XPathExpression<Element> xpath = null;
        synchronized (XPATH_CACHE) {
            xpath = XPATH_CACHE.get(xpathString);
            if (xpath == null) {
                xpath = XPathFactory.instance().compile(xpathString, org.jdom2.filter.Filters.element());
                XPATH_CACHE.put(xpathString, xpath);
            }
        }
        return xpath;
    }
}
