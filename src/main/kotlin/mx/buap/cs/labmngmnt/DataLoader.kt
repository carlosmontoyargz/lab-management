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

package mx.buap.cs.labmngmnt

import mx.buap.cs.labmngmnt.model.Profesor
import mx.buap.cs.labmngmnt.service.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 *
 * @author Carlos Montoya
 * @since 1.0
 */
@Component
class DataLoader
    @Autowired constructor(
        val usuarioService: UsuarioService)
    : ApplicationListener<ApplicationReadyEvent>
{
    @Transactional
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        usuarioService.registrar(Profesor().apply {
            nombre = "Carlos"
            apellidoPaterno = "Montoya"
            apellidoMaterno = "Rodriguez"
            matricula = "201325916"
            correo = "carlos.montoya@alumno.buap.mx"
            password = "admin"
            creado = LocalDateTime.now()
            telefono = "2125295121"
            isActivo = true
            isResponsable = true
            isConfirmado = true
        })
    }
}
