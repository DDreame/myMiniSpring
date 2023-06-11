import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/20 11:50 
 */


public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        byte a = 126;byte b =125;b+= a;
        System.out.println(b);

    }
    public static int test(int num){
        try{
            num += 10;
            return num;
        }catch (RuntimeException e){

        }catch (Exception e){

        }finally {
            num+=10;
            return num;
        }
    }
}
class B extends  Object{
    static {
        System.out.println("load B");
    }
    public B(){
        System.out.println("create B");
    }
}
class A extends B{
    static {
        System.out.println("load A");
    }
    public A(){
        System.out.println("create A");
    }
}