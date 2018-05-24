package com.xu.crawlerpojo;

import lombok.Data;
import lombok.ToString;

/**
 * @author xuhongda on 2018/5/15
 * com.xu.crawlerpojo
 * load-service-parent
 */
@Data
@ToString
public class MusicPojo {
    /**
     * 歌名
     */
    private String songlist__header_name;
    /**
     * 作者
     */
    private String songlist__header_author;
    /**
     * 专辑
     */
    private String songlist__header_album;
    /**
     * 时长
     */
    private String songlist__header_time;
}
