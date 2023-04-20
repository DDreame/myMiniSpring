import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/20 11:50 
 */
public class SomTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Class<?> cls = int.class;
        Object obj = cls.newInstance();
        System.out.println(obj);
    }
}
