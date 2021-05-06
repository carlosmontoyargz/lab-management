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

package mx.buap.cs.labmngmnt.rest

import mx.buap.cs.labmngmnt.rest.dto.AutenticacionRequest
import mx.buap.cs.labmngmnt.rest.dto.AutenticacionResponse
import mx.buap.cs.labmngmnt.security.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.authentication.BadCredentialsException

import org.springframework.security.authentication.DisabledException

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import java.lang.Exception
import kotlin.jvm.Throws

@RestController
@CrossOrigin
class ControladorAutenticacion
    @Autowired constructor(
        val authenticationManager: AuthenticationManager,
        val jwtTokenUtil: JwtTokenUtil,
        val userDetailsService: UserDetailsService)
{
    @PostMapping(value = ["/autenticar"])
    @Throws(Exception::class)
    fun crearTokenAutenticacion(@RequestBody request: AutenticacionRequest)
    : ResponseEntity<AutenticacionResponse>
    {
        autenticar(request.username!!, request.password!!)
        return ResponseEntity.ok(
            AutenticacionResponse(
                token = jwtTokenUtil.generateToken(
                    userDetailsService.loadUserByUsername(request.username!!))))
    }

    @Throws(Exception::class)
    private fun autenticar(username: String, password: String) {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}
