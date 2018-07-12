package com.soupthatisthick.util.string;

import java.util.Map;

public class MyXml {

    public static final String open(final String tagName) {
        return open(tagName, null);
    }

    public static final String open(
        final String tagName,
        final Map<String, String> attributes
    ) {
        if (tagName==null) {
            throw new IllegalArgumentException("tagName can't be null");
        }
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        sb.append(tagName);
        if (attributes!=null) {
            for(String attributeName : attributes.keySet()) {
                if (attributeName==null) {
                    throw new IllegalArgumentException("attributeName can't be null");
                }
                final String attributeValue = attributes.get(attributeName);

                sb.append(' ').append(attributeName).append("=\"");
                if (attributeValue != null) {
                    sb.append(attributeValue);
                }
                sb.append("\"");
            }
        }
        sb.append('>');
        return sb.toString();
    }

    public static final String close(final String tagName) {
        StringBuilder sb = new StringBuilder();
        sb.append('<')
            .append(tagName)
            .append("/>");
        return sb.toString();
    }

    public static final String element(
        final String tagName,
        final String content
    ) {
        return element(tagName, null, content);
    }

    public static final String element(
        final String tagName,
        final Map<String, String> attributes,
        final String content
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append(open(tagName, attributes));
        if (content!=null) {
            sb.append(content);
        }
        sb.append(close(tagName));
        return sb.toString();
    }
}
