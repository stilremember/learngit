package com.augurit.myproject.utils.plugin.schedule.core.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class SpringUtils implements BeanFactoryPostProcessor {
	//应用环境上下文
	private static ConfigurableListableBeanFactory beanFactory;
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {
		SpringUtils.beanFactory=beanFactory;
	}
	
	/**
	 * 获取对象
	 * */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) beanFactory.getBean(name);
	}

	/**
	 * 获取类型为requiredType对象
	 * */
	public static <T> T getBean(Class<T> clazz) throws BeansException {
		T result = beanFactory.getBean(clazz);
		return result;
	}
	/**
	 * 如果BeanFactory包含一个与所给定义名称匹配的bean定义， 则返回true
	 * */
	public static boolean containsBean(String name){
		return beanFactory.containsBean(name);
	}
	/**
	 * 判断以给定名字注册的bean定义是一个单例还是原型
	 * 如果与给定的名字相同的bean定义没有被找到，将会抛出一个异常(NoSuchBeanDefinitionException)
	 * */
	public static boolean isSingLeton(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.isSingleton(name);
	}
	
	/**
	 * 查找class对象
	 * params Class 注册对象类型
	 * */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.getType(name);
	}
	/**
	 *  如果给定的bean名字在bean定义中有别名，则返回这些别名
	 * */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.getAliases(name);
	}
}
