package by.itacademy.profiler.usecasses.util;

import by.itacademy.profiler.api.exception.BadRequestException;
import by.itacademy.profiler.api.exception.EmptyFileException;
import by.itacademy.profiler.api.exception.WrongMediaTypeException;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static by.itacademy.profiler.util.ImageTestData.createMultipartImageFileWithNoContent;
import static by.itacademy.profiler.util.ImageTestData.createMultipartImageFileWithUnsupportedType;
import static by.itacademy.profiler.util.ImageTestData.createValidMultipartImageFile;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidateImageTest {

    @Test
    void shouldNotThrowExceptionWhenInvokeValidateAndPassValidFile() {

        assertDoesNotThrow(() -> ValidateImage.validate(createValidMultipartImageFile()));
    }

    @Test
    void shouldThrowBadRequestExceptionWhenInvokeValidateAndPassNull() {

        assertThrows(BadRequestException.class, () -> ValidateImage.validate(null));
    }

    @Test
    void shouldThrowEmptyFileExceptionWhenInvokeValidateAndPassEmptyFile() {

        MockMultipartFile emptyFile = createMultipartImageFileWithNoContent();
        assertThrows(EmptyFileException.class, () -> ValidateImage.validate(emptyFile));
    }

    @Test
    void shouldThrowWrongMediaTypeExceptionWhenInvokeValidateAndPassWrongMediaTypeFile() {

        MockMultipartFile wrongMediaTypeFile = createMultipartImageFileWithUnsupportedType();
        assertThrows(WrongMediaTypeException.class, () -> ValidateImage.validate(wrongMediaTypeFile));
    }
}
