package com.amendola.api_park.service;

import com.amendola.api_park.entity.Usuario;
import com.amendola.api_park.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional()// @Transaction(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return  usuarioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario não encontrado")
        );
    }

    @Transactional()
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {

        if (!novaSenha.equals(confirmaSenha)){
            throw new RuntimeException("Nova Senha não confere com confirmação de senha");
        }

        Usuario user = buscarPorId(id);

        if(!user.getPassword().equals(senhaAtual)){
            throw new RuntimeException("Sua senha não confere");
        }

        user.setPassword(novaSenha);

        return user;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
