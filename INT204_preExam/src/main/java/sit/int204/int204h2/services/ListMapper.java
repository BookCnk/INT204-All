package sit.int204.int204h2.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    private static final ListMapper listMapper = new ListMapper();

    public static ListMapper getInstance() {
        return listMapper;
    }

    public <S, T> List<T> mapList(List<S> objectList, Class<T> dtoClass, ModelMapper modelMapper) {
        return objectList.stream().map(entities -> modelMapper.map(entities, dtoClass)).collect(Collectors.toList());
    }

    public <S, T> Page<T> mapListPage(Page<S> page, Class<T> dtoClass, ModelMapper modelMapper) {
        return page.map(entities -> modelMapper.map(entities, dtoClass));
    }
}
