package com.xu.service;

/**
 * @author xuhongda on 2018/5/15
 * com.xu.service
 * load-service-parent
 */

public interface MusicCrawler {
    /**
     * @author xuhongda
     * 关键字试听功能
     * @param keyWords
     * @return
     */
    String listen(String keyWords);

    /**
     *@author xuhongda
     *
     * @param keyWords
     * @return
     */
    String download(String keyWords);
}
