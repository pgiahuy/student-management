package com.pgh.student_management.utils;

import org.springframework.beans.BeanUtils;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

public class EntityUtils {
    public static void copyNoNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private static String[] getNullPropertyNames(Object source) {
        final var props = BeanUtils.getPropertyDescriptors(source.getClass());
        return Stream.of(props).map(FeatureDescriptor::getName)
                .filter(name ->{
                    try{
                        return BeanUtils.getPropertyDescriptor(source.getClass(),name)
                                .getReadMethod()
                                .invoke(source)==null;
                    }catch (Exception e){
                        return false;
                    }

                }).toArray(String[]::new);
    }
}
