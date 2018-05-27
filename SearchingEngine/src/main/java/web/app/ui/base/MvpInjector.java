package web.app.ui.base;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

public class MvpInjector {
    public static ApplicationContext context;
    
    public static void Inject(final Object object){
        if(context==null){
            throw new RuntimeException("MvpInjector => Spring ApplicationContext not init yet or MvpInjector.context is ...");
        } 
        final AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(object);
    }
}
