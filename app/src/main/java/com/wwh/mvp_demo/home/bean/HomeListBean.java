package com.wwh.mvp_demo.home.bean;

import com.google.gson.annotations.JsonAdapter;
import com.wwh.mvp_demo.api.net.ListTypeAdapterFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangWeiHao on 2020-05-28.
 */
public class HomeListBean implements Serializable {


    /**
     * resultcode : 200
     * reason : Success
     * result : {"data":[{"id":"78532","title":"西红柿丝瓜汤","tags":"家常菜;汤;美容;通乳;延缓衰老;防辐射;感冒;防癌;抗癌;10-20分钟;煮;简单;蚝油;消化不良;夏季;解暑;开胃;熬;补水;抗衰老;抗氧化;补铁;促消化;口味菜;汤锅;全菜系;1-2人;2-3人;防晒;凉血;清热解暑;催乳;下奶;番茄味;中等难度;保湿;缓解压力;清肺化痰;口臭","imtro":"这道菜是偶然得到的 我给孩子洗了个大大的西红柿，他只吃了一半，看到那么好的柿子不想浪费，就入在正做着的丝瓜汤里面啦，调出来的味道还真好。。。","ingredients":"丝瓜,100g;西红柿,100g;干贝,5g","burden":"蒜粒,适量;香油,2ml;盐,2g;鸡精,1g;蚝油,3ml;醋,2ml;生抽,2ml","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/79/78532_938615.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_6c1a369a0d3d0521.jpg","step":"1.热锅爆香蒜粒碎"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_f273f436d5bc2400.jpg","step":"2.放入干贝丝丝瓜翻炒至丝瓜软身"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_a7b88f698d87fcce.jpg","step":"3.放入西红柿，翻炒后加入一碗水，加盐，鸡精，醋，蚝油，生抽调味出锅"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_502cfbe0f52c9c8b.jpg","step":"4.滴入香油即可"}]}],"totalNum":"656","pn":0,"rn":"10"}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean implements Serializable {
        /**
         * data : [{"id":"78532","title":"西红柿丝瓜汤","tags":"家常菜;汤;美容;通乳;延缓衰老;防辐射;感冒;防癌;抗癌;10-20分钟;煮;简单;蚝油;消化不良;夏季;解暑;开胃;熬;补水;抗衰老;抗氧化;补铁;促消化;口味菜;汤锅;全菜系;1-2人;2-3人;防晒;凉血;清热解暑;催乳;下奶;番茄味;中等难度;保湿;缓解压力;清肺化痰;口臭","imtro":"这道菜是偶然得到的 我给孩子洗了个大大的西红柿，他只吃了一半，看到那么好的柿子不想浪费，就入在正做着的丝瓜汤里面啦，调出来的味道还真好。。。","ingredients":"丝瓜,100g;西红柿,100g;干贝,5g","burden":"蒜粒,适量;香油,2ml;盐,2g;鸡精,1g;蚝油,3ml;醋,2ml;生抽,2ml","albums":["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/79/78532_938615.jpg"],"steps":[{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_6c1a369a0d3d0521.jpg","step":"1.热锅爆香蒜粒碎"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_f273f436d5bc2400.jpg","step":"2.放入干贝丝丝瓜翻炒至丝瓜软身"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_a7b88f698d87fcce.jpg","step":"3.放入西红柿，翻炒后加入一碗水，加盐，鸡精，醋，蚝油，生抽调味出锅"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_502cfbe0f52c9c8b.jpg","step":"4.滴入香油即可"}]}]
         * totalNum : 656
         * pn : 0
         * rn : 10
         */

        private String totalNum;
        private int pn;
        private String rn;
        @JsonAdapter(ListTypeAdapterFactory.class)
        private List<DataBean> data;

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public int getPn() {
            return pn;
        }

        public void setPn(int pn) {
            this.pn = pn;
        }

        public String getRn() {
            return rn;
        }

        public void setRn(String rn) {
            this.rn = rn;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable {
            /**
             * id : 78532
             * title : 西红柿丝瓜汤
             * tags : 家常菜;汤;美容;通乳;延缓衰老;防辐射;感冒;防癌;抗癌;10-20分钟;煮;简单;蚝油;消化不良;夏季;解暑;开胃;熬;补水;抗衰老;抗氧化;补铁;促消化;口味菜;汤锅;全菜系;1-2人;2-3人;防晒;凉血;清热解暑;催乳;下奶;番茄味;中等难度;保湿;缓解压力;清肺化痰;口臭
             * imtro : 这道菜是偶然得到的 我给孩子洗了个大大的西红柿，他只吃了一半，看到那么好的柿子不想浪费，就入在正做着的丝瓜汤里面啦，调出来的味道还真好。。。
             * ingredients : 丝瓜,100g;西红柿,100g;干贝,5g
             * burden : 蒜粒,适量;香油,2ml;盐,2g;鸡精,1g;蚝油,3ml;醋,2ml;生抽,2ml
             * albums : ["http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/79/78532_938615.jpg"]
             * steps : [{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_6c1a369a0d3d0521.jpg","step":"1.热锅爆香蒜粒碎"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_f273f436d5bc2400.jpg","step":"2.放入干贝丝丝瓜翻炒至丝瓜软身"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_a7b88f698d87fcce.jpg","step":"3.放入西红柿，翻炒后加入一碗水，加盐，鸡精，醋，蚝油，生抽调味出锅"},{"img":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_502cfbe0f52c9c8b.jpg","step":"4.滴入香油即可"}]
             */

            private String id;
            private String title;
            private String tags;
            private String imtro;
            private String ingredients;
            private String burden;
            private List<String> albums;
            @JsonAdapter(ListTypeAdapterFactory.class)
            private List<StepsBean> steps;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getImtro() {
                return imtro;
            }

            public void setImtro(String imtro) {
                this.imtro = imtro;
            }

            public String getIngredients() {
                return ingredients;
            }

            public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
            }

            public String getBurden() {
                return burden;
            }

            public void setBurden(String burden) {
                this.burden = burden;
            }

            public List<String> getAlbums() {
                return albums;
            }

            public void setAlbums(List<String> albums) {
                this.albums = albums;
            }

            public List<StepsBean> getSteps() {
                return steps;
            }

            public void setSteps(List<StepsBean> steps) {
                this.steps = steps;
            }

            public static class StepsBean implements Serializable{
                /**
                 * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/786/78532_6c1a369a0d3d0521.jpg
                 * step : 1.热锅爆香蒜粒碎
                 */

                private String img;
                private String step;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getStep() {
                    return step;
                }

                public void setStep(String step) {
                    this.step = step;
                }
            }
        }
    }
}
