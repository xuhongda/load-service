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
public class QQMusic {
    private String keyword;
    private String priority;
    private List qc;
    private Semantic semantic;
    private QQSong song;
    private String tab;
    private String taglist;
    private String totaltime;
    private String zhida;

}
