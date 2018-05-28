package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xuhongda on 2018/5/24
 * bean
 * load-service-parent
 */
@Data
@ToString
//空参构造器
@NoArgsConstructor
//包含所有变量的构造函数
@AllArgsConstructor
public class Student {
    private String name;

    private int age;
}
