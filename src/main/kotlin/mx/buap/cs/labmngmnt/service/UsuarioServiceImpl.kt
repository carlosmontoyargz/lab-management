/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021. Carlos Alberto Montoya Rodríguez.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package mx.buap.cs.labmngmnt.service

import mx.buap.cs.labmngmnt.rest.UsuarioRepository
import mx.buap.cs.labmngmnt.error.SignUpException
import mx.buap.cs.labmngmnt.model.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws

/**
 *
 * @author Carlos Montoya
 * @since 1.0
 */
@Service
class UsuarioServiceImpl
    @Autowired constructor(
        val usuarioRepository: UsuarioRepository,
        val passwordEncoder: PasswordEncoder
    )
    : UsuarioService
{
    @Throws(SignUpException::class)
    override fun registrar(usuario: Usuario): Usuario {
        if (usuarioRepository.existsByCorreo(usuario.correo!!))
            throw SignUpException("El usuario ya existe")

        usuario.password = passwordEncoder.encode(usuario.password!!)

        return usuarioRepository.save(usuario)
    }

    override fun obtenerUsuario(correo: String, password: String): Optional<Usuario> =
        usuarioRepository
            .findByCorreo(correo)
            .filter { u -> u.password.equals(password) }
}
