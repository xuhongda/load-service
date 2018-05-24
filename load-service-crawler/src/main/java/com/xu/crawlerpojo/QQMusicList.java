package com.xu.crawlerpojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author xuhongda on 2018/5/16
 * com.xu.crawlerpojo
 * load-service-parent
 */
@Data
@ToString
public class QQMusicList {
    private String code;
    private QQMusic data;
    private String message;
    private String notice;
    private String subcode;
    private String time;
    private String tips;

}
