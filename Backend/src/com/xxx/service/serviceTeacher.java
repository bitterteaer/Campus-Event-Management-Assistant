package com.xxx.service;

import com.xxx.bean.Teacher;
import com.xxx.dao.TeacherDao;

import java.util.List;

/**
 * @author xzy
 * @create 2022/3/27 15:44
 */
public class serviceTeacher {
    public static Teacher login(Teacher teacher){
        List list = new TeacherDao().select();
        for (int i = 0; i < list.size(); i++) {
            Teacher teacher1 = (Teacher) list.get(i);
            if(teacher1.getUsername().equals(teacher.getUsername())
            && teacher1.getPassword().equals(teacher.getPassword())){
                return teacher1;
            }
        }

        return null;
    }
    public static String getData(){
        TeacherDao teacherDao = new TeacherDao();
        List<Teacher> list = (List) teacherDao.select();

        String json = list.toString();

        return json;
    }
    public Teacher getOneData(String id){
        TeacherDao teacherDao = new TeacherDao();
        Teacher tea = teacherDao.selectOne(id);

        return tea;
    }
//    public List getTheLoginSign(Teacher teacher){
//        List l = new SignUpDao().select();
//        for (int i = 0; i < l.size(); i++) {
//            SignUp s = (SignUp) l.get(i);
//            if(s.getUsername())
//        }
//    }
}
