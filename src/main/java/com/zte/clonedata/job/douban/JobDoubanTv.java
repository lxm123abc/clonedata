package com.zte.clonedata.job.douban;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zte.clonedata.contanst.Contanst;
import com.zte.clonedata.dao.CloneDoubanTvDetailMapper;
import com.zte.clonedata.model.CloneDoubanTvDetail;
import com.zte.clonedata.util.HttpUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * ProjectName: clonedata-com.zte.clonedata.job.douban
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 13:58 2020/6/4
 * @Description:
 */
@Slf4j
public class JobDoubanTv extends Thread {

    private List<CloneDoubanTvDetail> list;
    private CloneDoubanTvDetailMapper cloneDoubanTvDetailMapper;
    private String name;

    public JobDoubanTv(List<CloneDoubanTvDetail> list, CloneDoubanTvDetailMapper cloneDoubanTvDetailMapper, int n) {
        this.cloneDoubanTvDetailMapper = cloneDoubanTvDetailMapper;
        this.list = list;
        this.name = "DoubanMovieDetail-".concat(String.valueOf(n));
    }

    @SneakyThrows
    @Override
    public void run() {
        //long start = System.currentTimeMillis();
        for (CloneDoubanTvDetail tv : list) {
            /**
             * 判断是否存在数据库
             * 1. 不存在正常查询数据
             * 2. 存在
             *  2.1 如果相同则不处理
             *  2.2 如果不相同则查询,做修改操作
             */
            CloneDoubanTvDetail cloneDoubanTvDetail = cloneDoubanTvDetailMapper.selectByPrimaryKey(tv.getTvid());
            if (cloneDoubanTvDetail == null){
                getMovice(tv);
                cloneDoubanTvDetailMapper.insertSelective(tv);
                Thread.sleep(200);
            }else {
                String ratingnum = tv.getRatingnum();
                if (!ratingnum.equals(tv.getRatingnum())){
                    getMovice(tv);
                    cloneDoubanTvDetailMapper.updateByPrimaryKeySelective(tv);
                    Thread.sleep(200);
                }
            }
        }
        //log.info("任务: {} 已完成,耗时: {}  >>>",name,System.currentTimeMillis()-start);
    }
    private int c = 0;
    private void getMovice(CloneDoubanTvDetail tvDetail) throws InterruptedException {
        String url = "https://douban.uieee.com/v2/movie/subject/".concat(tvDetail.getTvid());
        try {
            String result = HttpUtils.getJson(url, Contanst.DOUBAN_HOST2);
            JSONObject json = JSONObject.parseObject(result);
            // 导演
            JSONArray directors = json.getJSONArray("directors");
            if (directors != null) {
                for (int i = 0; i < directors.size(); i++) {
                    JSONObject jsonObject = directors.getJSONObject(i);
                    tvDetail.setDirector(jsonObject.get("name").toString());
                }
            }

            //编剧
            JSONArray writers = json.getJSONArray("writers");
            if (writers != null) {
                for (int i = 0; i < writers.size(); i++) {
                    JSONObject jsonObject = writers.getJSONObject(i);
                    tvDetail.setScenarist(jsonObject.get("name").toString());
                }
            }
            //主演
            JSONArray casts = json.getJSONArray("casts");
            if (casts != null) {
                for (int i = 0; i < casts.size(); i++) {
                    JSONObject jsonObject = casts.getJSONObject(i);
                    tvDetail.setActors(jsonObject.get("name").toString());
                }
            }
            //类型
            JSONArray genres = json.getJSONArray("genres");
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    tvDetail.setType(genres.getString(i));
                }
            }
            //制片国家/地区
            JSONArray countries = json.getJSONArray("countries");
            if (countries != null) {
                for (int i = 0; i < countries.size(); i++) {
                    tvDetail.setCountry(countries.getString(i));
                }
            }
            //语言
            JSONArray languages = json.getJSONArray("languages");
            if (languages != null) {
                for (int i = 0; i < languages.size(); i++) {
                    tvDetail.setLanguage(languages.getString(i));
                }
            }
            //上映日期
            JSONArray pubdates = json.getJSONArray("pubdates");
            if (pubdates != null) {
                for (int i = 0; i < pubdates.size(); i++) {
                    tvDetail.setReleasedata(pubdates.getString(i));
                }
            }
            //片长
            JSONArray durations = json.getJSONArray("durations");
            if (durations != null) {
                for (int i = 0; i < durations.size(); i++) {
                    tvDetail.setRuntime(durations.getString(i));
                }
            }
            //集数
            tvDetail.setEpisodesCount(json.getString("episodes_count"));
            //豆瓣评分
            JSONObject rating = json.getJSONObject("rating");
            tvDetail.setRatingnum(rating.getBigDecimal("average").toString());
            //标签
            JSONArray tags = json.getJSONArray("tags");
            if (tags != null) {
                for (int i = 0; i < tags.size(); i++) {
                    tvDetail.setTags(tags.getString(i));
                }
            }
            //简介
            tvDetail.setTvdesc(json.getString("summary"));
            //又名
            JSONArray aka = json.getJSONArray("aka");
            if (aka != null) {
                for (int i = 0; i < aka.size(); i++) {
                    tvDetail.setAka(aka.getString(i));
                }
            }
            c = 0;
        } catch (Exception e) {
            log.error("发生错误url >>> {}", url);
            log.error("获取详单错误 >>> {}", e.getMessage());
            if (c++ < 10) {
                log.error("三秒后再次尝试获取详单  >>>{}<<<", c);
                Thread.sleep(3000);
                getMovice(tvDetail);
            } else {
                c = 0;
            }
        }
    }
}
