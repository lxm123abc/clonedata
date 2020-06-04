package com.zte.clonedata.job.douban;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zte.clonedata.contanst.Contanst;
import com.zte.clonedata.dao.DoubanMovieMapper;
import com.zte.clonedata.model.DoubanMovie;
import com.zte.clonedata.model.DoubanMovieExample;
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
public class JobDoubanMovie extends Thread {

    private List<DoubanMovie> list;
    private DoubanMovieMapper doubanMovieMapper;

    public JobDoubanMovie(List<DoubanMovie> list,DoubanMovieMapper doubanMovieMapper) {
        this.doubanMovieMapper = doubanMovieMapper;
        this.list = list;
        this.start();
    }

    @SneakyThrows
    @Override
    public void run() {

        for (DoubanMovie movie : list) {
            /**
             * 判断是否存在数据库
             * 1. 不存在正常查询数据
             * 2. 存在
             *  2.1 如果相同则不处理
             *  2.2 如果不相同则查询,做修改操作
             */
            DoubanMovie doubanMovie = doubanMovieMapper.selectByPrimaryKey(movie.getMovieid());
            if (doubanMovie == null){
                getMovice(movie);
                doubanMovieMapper.insertSelective(movie);
                Thread.sleep(200);
            }else {
                String ratingnum = doubanMovie.getRatingnum();
                if (!ratingnum.equals(movie.getRatingnum())){
                    getMovice(movie);
                    doubanMovieMapper.updateByPrimaryKeySelective(movie);
                    Thread.sleep(200);
                }
            }
        }
    }
    private int c = 0;
    private void getMovice(DoubanMovie movie) throws InterruptedException {
        String url = "https://douban.uieee.com/v2/movie/subject/".concat(movie.getMovieid());
        try {
            String result = HttpUtils.getJson(url, Contanst.DOUBAN_HOST2);
            JSONObject json = JSONObject.parseObject(result);
            // 导演
            JSONArray directors = json.getJSONArray("directors");
            if (directors != null) {
                for (int i = 0; i < directors.size(); i++) {
                    JSONObject jsonObject = directors.getJSONObject(i);
                    movie.setDirector(jsonObject.get("name").toString());
                }
            }

            //编剧
            JSONArray writers = json.getJSONArray("writers");
            if (writers != null) {
                for (int i = 0; i < writers.size(); i++) {
                    JSONObject jsonObject = writers.getJSONObject(i);
                    movie.setScenarist(jsonObject.get("name").toString());
                }
            }
            //主演
            JSONArray casts = json.getJSONArray("casts");
            if (casts != null) {
                for (int i = 0; i < casts.size(); i++) {
                    JSONObject jsonObject = casts.getJSONObject(i);
                    movie.setActors(jsonObject.get("name").toString());
                }
            }
            //类型
            JSONArray genres = json.getJSONArray("genres");
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    movie.setType(genres.getString(i));
                }
            }
            //制片国家/地区
            JSONArray countries = json.getJSONArray("countries");
            if (countries != null) {
                for (int i = 0; i < countries.size(); i++) {
                    movie.setCountry(countries.getString(i));
                }
            }
            //语言
            JSONArray languages = json.getJSONArray("languages");
            if (languages != null) {
                for (int i = 0; i < languages.size(); i++) {
                    movie.setLanguage(languages.getString(i));
                }
            }
            //上映日期
            JSONArray pubdates = json.getJSONArray("pubdates");
            if (pubdates != null) {
                for (int i = 0; i < pubdates.size(); i++) {
                    movie.setReleasedata(pubdates.getString(i));
                }
            }
            //片长
            JSONArray durations = json.getJSONArray("durations");
            if (durations != null) {
                for (int i = 0; i < durations.size(); i++) {
                    movie.setRuntime(durations.getString(i));
                }
            }
            //豆瓣评分
            JSONObject rating = json.getJSONObject("rating");
            movie.setRatingnum(rating.getBigDecimal("average").toString());
            //标签
            JSONArray tags = json.getJSONArray("tags");
            if (tags != null) {
                for (int i = 0; i < tags.size(); i++) {
                    movie.setTags(tags.getString(i));
                }
            }
            //简介
            movie.setMoviedesc(json.getString("summary"));
            //又名
            JSONArray aka = json.getJSONArray("aka");
            if (aka != null) {
                for (int i = 0; i < aka.size(); i++) {
                    movie.setAka(aka.getString(i));
                }
            }
            c = 0;
        } catch (Exception e) {
            log.error("发生错误url >>> {}", url);
            log.error("获取详单错误 >>> {}", e.getMessage());
            if (c++ < 10) {
                log.error("三秒后再次尝试获取详单  >>>{}<<<", c);
                Thread.sleep(3000);
                getMovice(movie);
            } else {
                c = 0;
            }
        }
    }
}
