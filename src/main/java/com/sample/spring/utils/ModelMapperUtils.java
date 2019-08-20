package com.sample.spring.utils;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ModelMapperUtils {

	private static ModelMapper modelMapper = new ModelMapper();

	public static <S, D> D map(final S entity, final Class<D> outClass) {
		return modelMapper.map(entity, outClass);
	}

	public static <S, D> List<D> mapAll(final Collection<S> entityList, final Class<D> outCLass) {
		return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
	}
}