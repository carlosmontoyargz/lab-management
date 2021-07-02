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
package mx.buap.cs.labmngmnt.api

import mx.buap.cs.labmngmnt.api.dto.AutenticacionRequest
import mx.buap.cs.labmngmnt.api.dto.TokenDto
import mx.buap.cs.labmngmnt.security.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import kotlin.jvm.Throws

/**
 * Controlador web para autenticación.
 *
 * @author Carlos Montoya
 * @since 1.0
 */
@RestController
@CrossOrigin
class AuthenticationController
    @Autowired constructor(
        val authManager: AuthenticationManager,
        val jwtTokenUtil: JwtTokenUtil)
{
    @PostMapping("/autenticar")
    @Throws(AuthenticationException::class)
    fun autenticar(@RequestBody request: AutenticacionRequest):
            ResponseEntity<TokenDto>
    {
        val authentication = authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username!!, request.password!!))

        return ResponseEntity
            .ok(crearUsuarioDto(authentication))
    }

    private fun crearUsuarioDto(auth: Authentication) = TokenDto()
        .apply {
            token = jwtTokenUtil.generateToken(auth.principal as UserDetails)
            roles = auth
                .authorities.stream()
                .map { it.authority }
                .toArray { arrayOfNulls(it) }
//            id = null
//            correo = null
//            matricula = null
//            nombre = null
//            apellidoPaterno = null
//            apellidoMaterno = null
//            telefono = null
//            creado = null
//            token = null
        }
}
