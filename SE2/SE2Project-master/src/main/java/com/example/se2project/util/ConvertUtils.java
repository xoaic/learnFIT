package com.example.se2project.util;

import com.example.se2project.exception.CustomException;
import org.springframework.beans.BeanUtils;


public class ConvertUtils {
    public static  <T, E> T convertEntityToDto(E entity, Class<T> tClass) throws CustomException {
        try {
            T dto = tClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        } catch (Exception e) {
            throw new CustomException("error");
        }
    }

    public static  <T, E> T convertDtoToEntity(E dto, Class<T> tClass) throws CustomException {
        try {
            T entity = tClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, entity);
            return entity;
        } catch (Exception e) {
            throw new CustomException("error");
        }
    }
}
