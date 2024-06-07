package com.yjkj.service_recoder.java.bean;

import java.util.List;

public class CfbgBean {


    /**
     * msg : 操作成功
     * code : 200
     * data : {"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":2,"answerOne":"今天心情怎么样？","dictTypeOne":"inspection_answer_one","answerOneList":["很好","还行","一般","不太好"],"answerTwo":"昨天排便次数","dictTypeTwo":"inspection_answer_two","answerTwoList":["1次","2次","3次","无"],"answerThree":"昨天状态怎么样？","dictTypeThree":"inspection_answer_three","answerThreeList":["低迷","一般","巅峰"],"answerFour":"昨天伙食满意吗？","dictTypeFour":"inspection_answer_four","answerFourList":["不满意","一般","非常满意"]}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * id : 2
         * answerOne : 今天心情怎么样？
         * dictTypeOne : inspection_answer_one
         * answerOneList : ["很好","还行","一般","不太好"]
         * answerTwo : 昨天排便次数
         * dictTypeTwo : inspection_answer_two
         * answerTwoList : ["1次","2次","3次","无"]
         * answerThree : 昨天状态怎么样？
         * dictTypeThree : inspection_answer_three
         * answerThreeList : ["低迷","一般","巅峰"]
         * answerFour : 昨天伙食满意吗？
         * dictTypeFour : inspection_answer_four
         * answerFourList : ["不满意","一般","非常满意"]
         */

        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private int id;
        private String answerOne;
        private String dictTypeOne;
        private String answerTwo;
        private String dictTypeTwo;
        private String answerThree;
        private String dictTypeThree;
        private String answerFour;
        private String dictTypeFour;
        private List<String> answerOneList;
        private List<String> answerTwoList;
        private List<String> answerThreeList;
        private List<String> answerFourList;

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAnswerOne() {
            return answerOne;
        }

        public void setAnswerOne(String answerOne) {
            this.answerOne = answerOne;
        }

        public String getDictTypeOne() {
            return dictTypeOne;
        }

        public void setDictTypeOne(String dictTypeOne) {
            this.dictTypeOne = dictTypeOne;
        }

        public String getAnswerTwo() {
            return answerTwo;
        }

        public void setAnswerTwo(String answerTwo) {
            this.answerTwo = answerTwo;
        }

        public String getDictTypeTwo() {
            return dictTypeTwo;
        }

        public void setDictTypeTwo(String dictTypeTwo) {
            this.dictTypeTwo = dictTypeTwo;
        }

        public String getAnswerThree() {
            return answerThree;
        }

        public void setAnswerThree(String answerThree) {
            this.answerThree = answerThree;
        }

        public String getDictTypeThree() {
            return dictTypeThree;
        }

        public void setDictTypeThree(String dictTypeThree) {
            this.dictTypeThree = dictTypeThree;
        }

        public String getAnswerFour() {
            return answerFour;
        }

        public void setAnswerFour(String answerFour) {
            this.answerFour = answerFour;
        }

        public String getDictTypeFour() {
            return dictTypeFour;
        }

        public void setDictTypeFour(String dictTypeFour) {
            this.dictTypeFour = dictTypeFour;
        }

        public List<String> getAnswerOneList() {
            return answerOneList;
        }

        public void setAnswerOneList(List<String> answerOneList) {
            this.answerOneList = answerOneList;
        }

        public List<String> getAnswerTwoList() {
            return answerTwoList;
        }

        public void setAnswerTwoList(List<String> answerTwoList) {
            this.answerTwoList = answerTwoList;
        }

        public List<String> getAnswerThreeList() {
            return answerThreeList;
        }

        public void setAnswerThreeList(List<String> answerThreeList) {
            this.answerThreeList = answerThreeList;
        }

        public List<String> getAnswerFourList() {
            return answerFourList;
        }

        public void setAnswerFourList(List<String> answerFourList) {
            this.answerFourList = answerFourList;
        }
    }
}
