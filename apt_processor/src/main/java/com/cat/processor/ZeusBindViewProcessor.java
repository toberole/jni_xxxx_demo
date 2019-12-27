package com.cat.processor;

import com.cat.zeus.annotation.ZeusAPTBindView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

@AutoService(Process.class)
public class ZeusBindViewProcessor extends AbstractProcessor {
    @Override
    /**
     * 指定AbstractProcessor支持的目标注解对象
     */
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(ZeusAPTBindView.class.getCanonicalName());
        printInfo(set);
        return set;
    }

    @Override
    /**
     * 处理逻辑
     */
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        printInfo(set);
        // 获取所有包含BindView注解的元素
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ZeusAPTBindView.class);
        Map<TypeElement, Map<Integer, VariableElement>> typeElementMapMap = new HashMap<>();

        // Element 用于代表程序的一个元素，这个元素可以是：包、类、接口、变量、方法等多种概念。
        for (Element e : elements) {
            // ZeusAPTBindView作用于FIELD 所以Element可以直接转化为VariableElement
            VariableElement variableElement = (VariableElement) e;

            //getEnclosingElement 方法返回封装此 Element 的最里层元素
            //如果 Element 直接封装在另一个元素的声明中，则返回该封装元素
            //此处表示的即 Activity 类对象
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();

            Map<Integer, VariableElement> variableElementMap = typeElementMapMap.get(typeElement);
            if (variableElementMap == null) {
                variableElementMap = new HashMap<>();
                typeElementMapMap.put(typeElement, variableElementMap);
            }

            // 获取注解值 viewId
            ZeusAPTBindView zeusAPTBindView = variableElement.getAnnotation(ZeusAPTBindView.class);
            int viewID = zeusAPTBindView.value();

            variableElementMap.put(viewID, variableElement);
        }


        return true;
    }

    /**
     * 生成方法
     * MethodSpec 是 JavaPoet 提供的一个概念，用于抽象出生成一个函数时需要的基础元素
     *
     * @param typeElement        注解对象上层元素对象
     * @param variableElementMap 包含的注解对象及注解的目标对象
     * @return
     */
    /*
        public class MainActivityViewBinding {
            public static void bind(MainActivity _mainActivity) {
                _mainActivity.btn_serializeSingle = (android.widget.Button) (_mainActivity.findViewById(2131165221));
                _mainActivity.tv_hint = (android.widget.TextView) (_mainActivity.findViewById(2131165333));
                _mainActivity.btn_serializeAll = (android.widget.Button) (_mainActivity.findViewById(2131165220));
                _mainActivity.btn_remove = (android.widget.Button) (_mainActivity.findViewById(2131165219));
                _mainActivity.btn_print = (android.widget.Button) (_mainActivity.findViewById(2131165218));
                _mainActivity.et_userName = (android.widget.EditText) (_mainActivity.findViewById(2131165246));
                _mainActivity.et_userAge = (android.widget.EditText) (_mainActivity.findViewById(2131165245));
                _mainActivity.et_singleUserName = (android.widget.EditText) (_mainActivity.findViewById(2131165244));
                _mainActivity.et_bookName = (android.widget.EditText) (_mainActivity.findViewById(2131165243));
            }
        }
    */
    private MethodSpec generateMethodByPoet(TypeElement typeElement, Map<Integer, VariableElement> variableElementMap) {
        ClassName className = ClassName.bestGuess(typeElement.getQualifiedName().toString());
        // 方法参数
        String parameter = "_" + StringUtils.toLowerCaseFirstChar(className.simpleName());


        return null;
    }


    private void printInfo(Set set) {
        for (Object o : set) {
            System.out.println("************ value: " + o);
        }
    }
}
