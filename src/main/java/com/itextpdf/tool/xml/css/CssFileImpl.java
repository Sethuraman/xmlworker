/*
 * $Id: CssFileImpl.java 412 2013-07-28 14:29:18Z blowagie $
 *
 * This file is part of the iText (R) project.
 * Copyright (c) 1998-2013 1T3XT BVBA
 * Authors: Balder Van Camp, Emiel Ackermann, et al.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License version 3
 * as published by the Free Software Foundation with the addition of the
 * following permission added to Section 15 as permitted in Section 7(a):
 * FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY 1T3XT,
 * 1T3XT DISCLAIMS THE WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA, 02110-1301 USA, or download the license from the following URL:
 * http://itextpdf.com/terms-of-use/
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License,
 * a covered work must retain the producer line in every PDF that is created
 * or manipulated using iText.
 *
 * You can be released from the requirements of the license by purchasing
 * a commercial license. Buying such a license is mandatory as soon as you
 * develop commercial activities involving the iText software without
 * disclosing the source code of your own applications.
 * These activities include: offering paid services to customers as an ASP,
 * serving PDFs on the fly in a web application, shipping iText with a closed
 * source product.
 *
 * For more information, please contact iText Software Corp. at this
 * address: sales@itextpdf.com
 */
package com.itextpdf.tool.xml.css;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of CssFile, the CSS is stored in a map.
 * @author redlab_b
 *
 */
public class CssFileImpl implements CssFile {

    private final Map<String, Map<String, String>> map;
	private boolean persistent;

    /**
     * Constructs a new CssFileImpl.
     */
    public CssFileImpl() {
    	persistent = false;
        map = new ConcurrentHashMap<String, Map<String, String>>();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itextpdf.tool.xml.css.CssFile#add(java.lang.String,
     * java.util.Map)
     */
    public void add(final String selector, final Map<String, String> props) {
        Map<String, String> currVal = map.get(selector);
        if (currVal != null) {
            currVal.putAll(props);
        } else {
            map.put(selector, props);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itextpdf.tool.xml.css.CssFile#get(java.lang.String)
     */
    public Map<String, String> get(final String selector) {
        Map<String, String> map = this.map.get(selector);
		return null == map?new ConcurrentHashMap<String, String>(0):map;
    }

	/* (non-Javadoc)
	 * @see com.itextpdf.tool.xml.css.CssFile#isPersistent()
	 */
	public boolean isPersistent() {
		return persistent;
	}

	/**
	 * Indicates that this file should be kept.
	 * @param isPeristent set to true if this file should be kept.
	 */
	public void isPersistent(final boolean isPeristent) {
		this.persistent = isPeristent;
	}
}
