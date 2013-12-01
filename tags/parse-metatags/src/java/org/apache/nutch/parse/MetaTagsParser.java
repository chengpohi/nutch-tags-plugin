/**
 * Lice
 nsed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.nutch.parse;

import java.lang.StringBuffer;
import java.lang.StringBuilder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.avro.util.Utf8;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.metadata.Metadata;
import org.apache.nutch.protocol.Content;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.storage.WebPage.Field;
import org.apache.nutch.util.Bytes;
import org.springframework.web.util.TagUtils;
import org.w3c.dom.DocumentFragment;

/**
 * Parse HTML meta tags (keywords, description) and store them in the parse metadata so that
 * they can be indexed with the index-metadata plugin with the prefix 'metatag.'
 * *
 */

public class MetaTagsParser implements ParseFilter {

    private static final Log LOG = LogFactory.getLog(MetaTagsParser.class
            .getName());

    private Configuration conf;

    private Set<String> metatagset = new HashSet<String>();

    public void setConf(Configuration conf) {
        this.conf = conf;
        // specify whether we want a specific subset of metadata
        // by default take everything we can find
    }

    public Configuration getConf() {
        return this.conf;
    }

    public Parse filter(String url, WebPage page, Parse parse,
                        HTMLMetaTags metaTags, DocumentFragment doc) {
        page.putToMetadata(new Utf8("tags"),
                ByteBuffer.wrap(Bytes.toBytes(
                        TagProcessor.parseStringToTags(parse.getText()).toString())
                ));
        return parse;
    }

    @Override
    public Collection<Field> getFields() {
        // TODO Auto-generated method stub
        return null;
    }

}
