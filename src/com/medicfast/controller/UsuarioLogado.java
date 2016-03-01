
package com.medicfast.controller;

import com.medicfast.model.Usuario;


public class UsuarioLogado {
    
    public static Usuario usuarioLogado;

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(Usuario usuarioLogado) {
        UsuarioLogado.usuarioLogado = usuarioLogado;
    }
    
}
