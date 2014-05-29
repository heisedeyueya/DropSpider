package com.kankan.tutopic.model;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kankan.logging.Logger;
import com.kankan.tutopic.spider.ISpider;

// http://www.4493.com/
public class Spider4493 implements ISpider {
    private static final Logger LOG = Logger.getLogger(Spider4493.class);

    private static final String HOME_PAGE = "http://www.4493.com/";

    @Override
    public Featured catupre() {
        try {
            Document doc = Jsoup.connect(HOME_PAGE).get();

            Elements media = doc.select("[src]");
            for (Element src : media) {
                if (src.tagName().equals("img"))
                    print(" * %s: <%s> %sx%s (%s)",
                            src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                            trim(src.attr("alt"), 20));
                else
                    print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }

}
