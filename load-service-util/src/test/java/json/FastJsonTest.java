package json;

import bean.Student;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuhongda on 2018/5/24
 * json
 * load-service-parent
 */

public class FastJsonTest {
    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";

    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
    @Before
    public void before(){

    }
    @Test
    public void test(){

    }

    /**
     * json字符串-简单对象型到JSONObject的转换
     */
    @Test
    public void testJSONStrToJSONObject() {

        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);

        System.out.println("studentName:  " + jsonObject.getString("studentName") + ":" + "  studentAge:  "
                + jsonObject.getInteger("studentAge"));

    }

    /**
     * JSONObject到json字符串-简单对象型的转换
     */
    @Test
    public void testJSONObjectToJSONStr() {

        //已知JSONObject,目标要转换为json字符串
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
        // 第一种方式
       // String jsonString = JSONObject.toJSONString(jsonObject);

        // 第二种方式
        String jsonString = jsonObject.toJSONString();
        System.out.println(jsonString);
    }

    /**
     * json字符串-数组类型到JSONArray的转换
     */
    @Test
    public void testJSONStrToJSONArray() {

        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

        //遍历方式1
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println("studentName:  " + jsonObject.getString("studentName") + ":" + "  studentAge:  "
                    + jsonObject.getInteger("studentAge"));
        }

        //遍历方式2
        for (Object obj : jsonArray) {

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("studentName:  " + jsonObject.getString("studentName") + ":" + "  studentAge:  "
                    + jsonObject.getInteger("studentAge"));
        }
    }

    /**
     * JSONArray到json字符串-数组类型的转换
     */
    @Test
    public void testJSONArrayToJSONStr() {

        //已知JSONArray,目标要转换为json字符串
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);
        //第一种方式
        String jsonString = JSONArray.toJSONString(jsonArray);

        // 第二种方式
        //String jsonString = jsonArray.toJSONString(jsonArray);
        System.out.println(jsonString);
    }

    /**
     * 复杂json格式字符串到JSONObject的转换
     */
    @Test
    public void testComplexJSONStrToJSONObject() {

        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);

        String teacherName = jsonObject.getString("teacherName");
        Integer teacherAge = jsonObject.getInteger("teacherAge");

        System.out.println("teacherName:  " + teacherName + "   teacherAge:  " + teacherAge);

        JSONObject jsonObjectcourse = jsonObject.getJSONObject("course");
        //获取JSONObject中的数据
        String courseName = jsonObjectcourse.getString("courseName");
        Integer code = jsonObjectcourse.getInteger("code");

        System.out.println("courseName:  " + courseName + "   code:  " + code);

        JSONArray jsonArraystudents = jsonObject.getJSONArray("students");

        //遍历JSONArray
        for (Object object : jsonArraystudents) {

            JSONObject jsonObjectone = (JSONObject) object;
            String studentName = jsonObjectone.getString("studentName");
            Integer studentAge = jsonObjectone.getInteger("studentAge");

            System.out.println("studentName:  " + studentName + "   studentAge:  " + studentAge);
        }
    }

    /**
     * 复杂JSONObject到json格式字符串的转换
     */
    @Test
    public void testJSONObjectToComplexJSONStr() {

        //复杂JSONObject,目标要转换为json字符串
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);

        //第一种方式
        //String jsonString = JSONObject.toJSONString(jsonObject);

        //第二种方式
        String jsonString = jsonObject.toJSONString();
        System.out.println(jsonString);

    }

    /**
     * json字符串-简单对象到JavaBean之间的转换
     */
    @Test
    public void testJSONStrToJavaBeanObj() {

        //第一种方式
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);

        String studentName = jsonObject.getString("studentName");
        Integer studentAge = jsonObject.getInteger("studentAge");

        //Student student = new Student(studentName, studentAge);

        //第二种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        //Student student = JSONObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});

        //第三种方式,使用Gson的思想
        Student student = JSONObject.parseObject(JSON_OBJ_STR, Student.class);

        System.out.println(student);
    }

    /**
     * JavaBean到json字符串-简单对象的转换
     */
    @Test
    public void testJavaBeanObjToJSONStr() {

        Student student = new Student("lily", 12);
        String jsonString = JSONObject.toJSONString(student);
        System.out.println(jsonString);
    }


    /**
     * json字符串-数组类型到JavaBean_List的转换
     */
    @Test
    public void testJSONStrToJavaBeanList() {

        //第一种方式
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);

        //遍历JSONArray
        List<Student> students = new ArrayList<Student>();
        Student student = null;
        for (Object object : jsonArray) {

            JSONObject jsonObjectone = (JSONObject) object;
            String studentName = jsonObjectone.getString("studentName");
            Integer studentAge = jsonObjectone.getInteger("studentAge");

            student = new Student(studentName,studentAge);
            students.add(student);
        }

        System.out.println("students:  " + students);


        //第二种方式,使用TypeReference<T>类,由于其构造方法使用protected进行修饰,故创建其子类
        List<Student> studentList = JSONArray.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});
        System.out.println("studentList:  " + studentList);

        //第三种方式,使用Gson的思想
        List<Student> studentList1 = JSONArray.parseArray(JSON_ARRAY_STR, Student.class);
        System.out.println("studentList1:  " + studentList1);

    }

    /**
     * JavaBean_List到json字符串-数组类型的转换
     */
    @Test
    public void testJavaBeanListToJSONStr() {

        Student student = new Student("lily", 12);
        Student studenttwo = new Student("lucy", 15);

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(studenttwo);

        String jsonString = JSONArray.toJSONString(students);
        System.out.println(jsonString);

    }


}
