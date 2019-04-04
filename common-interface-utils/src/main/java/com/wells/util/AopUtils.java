package com.wells.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * Created by zb on 2019/3/23
 */
public class AopUtils {
    /**
     * 获取被拦截方法对象
     * MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
     * 而缓存的注解在实现类的方法上
     * 所以应该使用反射获取当前对象的方法对象
     * @param pjp
     * @return
     * @throws NoSuchMethodException
     */
    public static Method getMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        //--------------------------------------------------------------------------
        // 获取参数的类型
        //--------------------------------------------------------------------------
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        String methodName = pjp.getSignature().getName();
        Class<?> targetClass = pjp.getTarget().getClass();
        Method[] methods = targetClass.getMethods();

        //--------------------------------------------------------------------------
        // 查找Class<?>里函数名称、参数数量、参数类型（相同或子类）都和拦截的method相同的Method
        //--------------------------------------------------------------------------
        Method method = null;
        for (int i = 0; i < methods.length; i++){
            if (methods[i].getName() == methodName){
                Class<?>[] parameterTypes = methods[i].getParameterTypes();
                boolean isSameMethod = true;

                // 如果相比较的两个method的参数长度不一样，则结束本次循环，与下一个method比较
                if (args.length != parameterTypes.length) {
                    continue;
                }

                //--------------------------------------------------------------------------
                // 比较两个method的每个参数，是不是同一类型或者传入对象的类型是形参的子类
                //--------------------------------------------------------------------------
                for (int j = 0;parameterTypes != null && j < parameterTypes.length ;j++) {
                    if (parameterTypes[j] != argTypes[j] && !parameterTypes[j].isAssignableFrom(argTypes[j])) {
                        isSameMethod = false;
                        break;
                    }
                }
                if (isSameMethod) {
                    method = methods[i];
                    break;
                }
            }
        }
        return method;
    }

    /**
     * 获取缓存的key
     * key 定义在注解上，支持SPEL表达式
     * 注： method的参数支持Javabean和Map
     *      method的基本类型要定义为对象，否则没法读取到名称
     *
     * example1:
     *      Phone phone = new Phone();
     *      "#{phone.cpu}"  为对象的取值
     * example2:
     *      Map apple = new HashMap(); apple.put("name","good apple");
     *      "#{apple[name]}"  为map的取值
     * example3:
     *      "#{phone.cpu}_#{apple[name]}"
     *
     * @param key
     * @param method
     * @param args
     * @return
     */
    public static String parseKey(String key, Method method, Object[] args) {
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u =
                new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        ParserContext parserContext = new TemplateParserContext();

        //----------------------------------------------------------
        // 把 #{ 替换成 #{# ,以适配SpEl模板的格式
        //----------------------------------------------------------
        // 把 #{ 替换成 #{# ,以适配SpEl模板的格式
        // ----------------------------------------------------------
        //例如，@注解名称(key="#{player.userName}",expire = 200)
        //#{phone[cpu]}_#{phone[ram]}
        //#{player.userName}_#{phone[cpu]}_#{phone[ram]}_#{pageNo}_#{pageSize}
        System.out.println(parserContext.toString());
        System.out.println(context.toString());

        Object returnVal =
                parser.parseExpression(key.replace("#{","#{#"), parserContext).getValue(context, Object.class);
        //这块这么做，是为了Object和String都可以转成String类型的，可以作为key
//        String return_data_key = JsonUtils.objectToJson(returnVal);
//        //转换成md5，是因为redis的key过长，并且这种大key的数量过多，就会占用内存，影响性能
//        if(keyTransformMd5) {
//            return_data_key = MD5Util.digest(return_data_key);
//        }
        System.out.println(returnVal.toString());
        return returnVal == null ? null: returnVal.toString();
    }
}
