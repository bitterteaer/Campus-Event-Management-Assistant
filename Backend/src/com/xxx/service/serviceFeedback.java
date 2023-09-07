package com.xxx.service;

import com.xxx.bean.Ament;
import com.xxx.bean.Feedback;
import com.xxx.dao.AmentDao;
import com.xxx.dao.FeedbackDao;

import java.util.List;

/**
 * @author xzy
 * @create 2022/2/19 15:25
 */
public class serviceFeedback {
    public static int setData(Feedback feedback){
        FeedbackDao feedbackDao = new FeedbackDao();
        return feedbackDao.add(feedback);
    }
    public  static List getData(){
        FeedbackDao feedbackDao = new FeedbackDao();
        return feedbackDao.select();
    }
}
