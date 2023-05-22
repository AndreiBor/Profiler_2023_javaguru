package by.itacademy.profiler.usecasses.mapper;

import by.itacademy.profiler.persistence.model.Experience;
import by.itacademy.profiler.usecasses.SphereService;
import by.itacademy.profiler.usecasses.dto.ExperienceRequestDto;
import by.itacademy.profiler.usecasses.dto.ExperienceResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public abstract class ExperienceMapper {

    @Autowired
    protected SphereService sphereService;

    @Mapping(target = "sphereId", source = "experience.sphere.id")
    @Mapping(target = "sphereName", source = "experience.sphere.name")
    public abstract ExperienceResponseDto fromEntityToDto(Experience experience);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sphere", expression = "java(sphereService.getSphereById(experienceRequestDto.sphereId()))")
    public abstract Experience fromDtoToEntity(ExperienceRequestDto experienceRequestDto);
}
