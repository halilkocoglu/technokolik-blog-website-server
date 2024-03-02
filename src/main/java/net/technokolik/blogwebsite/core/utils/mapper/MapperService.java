package net.technokolik.blogwebsite.core.utils.mapper;

import org.modelmapper.ModelMapper;

public interface MapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
