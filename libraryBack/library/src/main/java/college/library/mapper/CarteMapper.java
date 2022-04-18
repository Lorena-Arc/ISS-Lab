package college.library.mapper;

import college.library.dto.CarteRequestDto;
import college.library.model.Carte;
import college.library.model.StatusCarte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarteMapper {

    @Mapping(source = "statusCarte", target = "statusCarte")
    Carte carteRequestDtoToCarte(CarteRequestDto carteRequestDto, StatusCarte statusCarte);
}
