package com.amendola.api_park.web.controller.dto.mapper;

import com.amendola.api_park.entity.Usuario;
import com.amendola.api_park.web.controller.dto.UsuarioCreateDto;
import com.amendola.api_park.web.controller.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto){
        // Método que converte um objeto UsuarioCreateDto em um objeto Usuario
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        // Método que converte um objeto Usuario em um objeto UsuarioResponseDto
        // Extrai o papel (role) do usuário e converte para uma string, removendo o prefixo "ROLE_"
        String role = usuario.getRole().name().substring("ROLE_".length());

        // Define um mapeamento personalizado entre Usuario e UsuarioResponseDto
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role); // Mapeia a propriedade 'role' do UsuarioResponseDto com o valor obtido acima
            }
        };

        // Cria um objeto ModelMapper e adiciona o mapeamento personalizado
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);

        // Retorna um objeto UsuarioResponseDto mapeado a partir do objeto Usuario usando o ModelMapper
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios) {
        // Método que converte uma lista de objetos Usuario em uma lista de objetos UsuarioResponseDto
        // Utiliza streams para mapear cada usuário para um objeto UsuarioResponseDto usando o método toDto
        // e então coleta os resultados em uma lista
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }


}
