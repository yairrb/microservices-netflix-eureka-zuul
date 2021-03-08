package com.formacionbdi.springboot.app.oauth.services;

import com.formacionbdi.springboot.app.commons.usuarios.models.entity.Usuario;
import com.formacionbdi.springboot.app.oauth.clients.UsuarioFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService,IUsuarioService {

    @Autowired
    private UsuarioFeignClient client;

    private Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = client.findByUsername(username);

        if ( usuario == null ) {
            log.error("error en el login, no existe el usuario "+username);
            throw new UsernameNotFoundException("error en el login, no existe el usuario "+username);
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .peek(authority -> log.info("ROLE: "+ authority.getAuthority()))
                .collect(Collectors.toList());

        log.info("Usuario autenticado: "+username);

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true,authorities );

    }

    @Override
    public Usuario findByUsername(String username) {
        return this.client.findByUsername(username);
    }
}
