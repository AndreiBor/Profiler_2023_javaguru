package by.itacademy.profiler.usecasses.impl;

import by.itacademy.profiler.persistence.model.AdditionalInformation;
import by.itacademy.profiler.persistence.model.CurriculumVitae;
import by.itacademy.profiler.persistence.repository.AdditionalInformationRepository;
import by.itacademy.profiler.persistence.repository.CurriculumVitaeRepository;
import by.itacademy.profiler.usecasses.dto.AdditionalInformationRequestDto;
import by.itacademy.profiler.usecasses.dto.AdditionalInformationResponseDto;
import by.itacademy.profiler.usecasses.mapper.AdditionalInformationMapper;
import by.itacademy.profiler.util.AdditionalInfoTestData;
import by.itacademy.profiler.util.CurriculumVitaeTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static by.itacademy.profiler.util.CurriculumVitaeTestData.CV_UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdditionalInformationServiceImplTest {



    @Mock
    private AdditionalInformationMapper additionalInformationMapper;

    @Mock
    private CurriculumVitaeRepository curriculumVitaeRepository;

    @Mock
    private AdditionalInformationRepository additionalInformationRepository;

    @InjectMocks
    private AdditionalInformationServiceImpl additionalInformationService;

    @Test
    void shouldSaveAdditionalInformationAndInvokeBusinessLogicWhenInvokeSave() {
        AdditionalInformationRequestDto requestDto = AdditionalInfoTestData.createAdditionalInformationRequestDto().build();
        CurriculumVitae curriculumVitae = CurriculumVitaeTestData.getValidCurriculumVitae();

        AdditionalInformation additionalInformation = AdditionalInfoTestData.createAdditionalInformationEntity().build();
        AdditionalInformationResponseDto responseDto = AdditionalInfoTestData.createAdditionalInformationResponseDto().build();

        when(curriculumVitaeRepository.getReferenceByUuid(CV_UUID)).thenReturn(curriculumVitae);
        when(additionalInformationMapper.fromDtoToEntity(requestDto)).thenReturn(additionalInformation);
        when(additionalInformationRepository.save(additionalInformation)).thenReturn(additionalInformation);
        when(additionalInformationMapper.fromEntityToDto(additionalInformation)).thenReturn(responseDto);

        AdditionalInformationResponseDto result = additionalInformationService.save(requestDto, CV_UUID);

        verify(curriculumVitaeRepository, times(1)).getReferenceByUuid(CV_UUID);
        verify(additionalInformationMapper, times(1)).fromDtoToEntity(requestDto);
        verify(additionalInformationRepository, times(1)).save(additionalInformation);
        verify(additionalInformationMapper, times(1)).fromEntityToDto(additionalInformation);
        assertEquals(responseDto, result);
    }
}