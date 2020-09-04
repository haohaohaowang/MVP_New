package com.wwh.mvp_demo.home.bean;

import com.google.gson.annotations.JsonAdapter;
import com.wwh.mvp_demo.api.net.ListTypeAdapterFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangWeiHao on 2020-05-30.
 */
public class HomeBean implements Serializable {
    /**
     * status : 1
     * msg : 获取成功
     * result : {"goods_list":[{"goods_id":2371,"cat_id":390,"goods_sn":"AB0002371","goods_name":"斐色耐LED化妆镜 YOYO镜梳妆镜女生生日礼物","shop_price":"199.00","comment_count":0,"sales_sum":2,"is_virtual":0}]}
     */

    private int status;
    private String msg;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HomeBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

    public static class ResultBean implements Serializable {
        @JsonAdapter(ListTypeAdapterFactory.class)
        private List<GoodsListBean> goods_list;

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "goods_list=" + goods_list +
                    '}';
        }

        public static class GoodsListBean  implements Serializable{
            /**
             * goods_id : 2371
             * cat_id : 390
             * goods_sn : AB0002371
             * goods_name : 斐色耐LED化妆镜 YOYO镜梳妆镜女生生日礼物
             * shop_price : 199.00
             * comment_count : 0
             * sales_sum : 2
             * is_virtual : 0
             */

            private int goods_id;
            private int cat_id;
            private String goods_sn;
            private String goods_name;
            private String shop_price;
            private int comment_count;
            private int sales_sum;
            private int is_virtual;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public int getComment_count() {
                return comment_count;
            }

            public void setComment_count(int comment_count) {
                this.comment_count = comment_count;
            }

            public int getSales_sum() {
                return sales_sum;
            }

            public void setSales_sum(int sales_sum) {
                this.sales_sum = sales_sum;
            }

            public int getIs_virtual() {
                return is_virtual;
            }

            public void setIs_virtual(int is_virtual) {
                this.is_virtual = is_virtual;
            }


            @Override
            public String toString() {
                return "GoodsListBean{" +
                        "goods_id=" + goods_id +
                        ", cat_id=" + cat_id +
                        ", goods_sn='" + goods_sn + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        ", shop_price='" + shop_price + '\'' +
                        ", comment_count=" + comment_count +
                        ", sales_sum=" + sales_sum +
                        ", is_virtual=" + is_virtual +
                        '}';
            }
        }
    }
//

}
