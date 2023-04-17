package com.minis.web;


import com.minis.ioc.core.Resource;
import com.minis.ioc.exception.BeanException;
import com.minis.web.servlet.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.*;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/4 21:19 
 */
public class DispatcherServlet extends HttpServlet {


    public static final String WEB_APPLICATION_CONTEXT_ATTRIBUTE = DispatcherServlet.class.getName() + ".CONTEXT";

    public static final String HANDLER_ADAPTER_BEAN_NAME = "handlerAdapter";

    public static final String VIEW_RESOLVER_BEAN_NAME = "viewResolver";

    private List<String> packageNames = new ArrayList<>();
    private Map<String,Object> controllerObjs = new HashMap<>();
    private List<String> controllerNames = new ArrayList<>();
    private Map<String,Class<?>> controllerClasses = new HashMap<>();

    private WebApplicationContext webApplicationContext;
    private WebApplicationContext parentApplicationContext;

    private HandlerMapping handlerMapping;

    private HandlerAdapter handlerAdapter;
    private ViewResolver viewResolver;



    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.parentApplicationContext = (WebApplicationContext) this.getServletContext()
                .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

        String sContextConfigLocation = config.getInitParameter("contextConfigLocation");
        URL xmlPath = null;
        try {
            xmlPath = this.getServletContext().getClassLoader().getResource(sContextConfigLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resource rs = new ClassPathXmlResource(xmlPath);
        this.packageNames = XmlScanComponentHelper.getNodeValue(xmlPath);
        //mappingValues = reader.loadConfig(rs);
        this.webApplicationContext = new AnnotationConfigWebApplicationContext(sContextConfigLocation, this.parentApplicationContext);
        Refresh();
    }

    private void initController(){
        this.controllerNames = Arrays.asList(this.webApplicationContext.getBeanDefinitionNames());
        for (String controllerName : this.controllerNames) {
            try {
                this.controllerClasses.put(controllerName,Class.forName(controllerName));
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                this.controllerObjs.put(controllerName,this.webApplicationContext.getBean(controllerName));
                System.out.println("controller : "+controllerName);
            } catch ( BeanException e) {
                e.printStackTrace();
            }
        }
    }



    //对所有的mappingValues中注册的类进行实例化，默认构造函数
    protected void Refresh() {
       initController();
       initHandlerMappings(this.webApplicationContext);
       initHandlerAdapters(this.webApplicationContext);
       initViewResolvers(this.webApplicationContext);
    }

    protected void initViewResolvers(WebApplicationContext wac) {
        try {
            this.viewResolver = (ViewResolver) wac.getBean(VIEW_RESOLVER_BEAN_NAME);
        } catch (BeanException e) {
            e.printStackTrace();
        }
    }

    protected void initHandlerMappings(WebApplicationContext wac) {
        this.handlerMapping = new RequestMappingHandlerMapping(wac);
    }
    protected void initHandlerAdapters(WebApplicationContext wac) {
        try {
            this.handlerAdapter = (HandlerAdapter) wac.getBean(HANDLER_ADAPTER_BEAN_NAME);
        } catch (BeanException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response) {
        request.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE,
                this.webApplicationContext);
        try {
            doDispatch(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        }
    }
    protected void doDispatch(HttpServletRequest request, HttpServletResponse
            response) throws Exception{
        HandlerMethod handlerMethod = null;
        handlerMethod = this.handlerMapping.getHandler(request);
        if (handlerMethod == null) {
            return;
        }
        HandlerAdapter ha = this.handlerAdapter;
        ModelAndView mv = ha.handle(request, response, handlerMethod);
        render(request, response, mv);
    }

    protected void render( HttpServletRequest request, HttpServletResponse response,ModelAndView mv) throws Exception {
        if (mv == null) {
            response.getWriter().flush();
            response.getWriter().close();
            return;
        }

        String sTarget = mv.getViewName();
        Map<String, Object> modelMap = mv.getModel();
        View view = resolveViewName(sTarget, modelMap, request);
        view.render(modelMap, request, response);

    }

    protected View resolveViewName(String viewName, Map<String, Object> model,
                                   HttpServletRequest request) throws Exception {
        if (this.viewResolver != null) {
            return viewResolver.resolveViewName(viewName);
        }
        return null;
    }

}
