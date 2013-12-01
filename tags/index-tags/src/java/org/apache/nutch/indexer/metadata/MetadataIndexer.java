/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
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

package org.apache.nutch.indexer.metadata;

import java.lang.Exception;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.avro.util.Utf8;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
// import org.apache.nutch.crawl.Inlinks;
import org.apache.nutch.indexer.IndexingException;
import org.apache.nutch.indexer.IndexingFilter;
import org.apache.nutch.indexer.NutchDocument;
import org.apache.nutch.parse.Parse;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.storage.WebPage.Field;
import org.apache.nutch.util.Bytes;

/**
 * Indexer which can be configured to extract metadata from the crawldb, parse metadata or content metadata.
 * You can specify the properties "index.db", "index.parse" or "index.content" who's values are
 * comma-delimited <value>key1, key2, key3</value>.
 *
 * @author jack
 */

public class MetadataIndexer implements IndexingFilter {
    private Configuration conf;

    public NutchDocument filter(NutchDocument doc, String url, WebPage page) throws IndexingException {
        System.out.println("Index Tags ~~~~~~~~~~~~~~~~~~~");
        // just in case
        if (doc == null)
            return doc;

        try {
            ByteBuffer bmetadatas = page.getFromMetadata(new Utf8("tags"));
            if (bmetadatas == null) return doc;

            String metadata = new String(bmetadatas.array());
            System.out.println(metadata);

            if (metadata != null)
                doc.add("tags", metadata.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;

        // TODO check conflict between field names e.g. could have same label
        // from different sources

    }

    public Configuration getConf() {
        return this.conf;
    }

    @Override
    public Collection<Field> getFields() {
        // TODO Auto-generated method stub
        return null;
    }
}

	