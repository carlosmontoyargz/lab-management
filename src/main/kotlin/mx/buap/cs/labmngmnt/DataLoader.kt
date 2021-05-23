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

import mx.buap.cs.labmngmnt.model.*
import mx.buap.cs.labmngmnt.repository.DocumentoRepository
import mx.buap.cs.labmngmnt.repository.MateriaRepository
import mx.buap.cs.labmngmnt.repository.UsuarioRepository
import mx.buap.cs.labmngmnt.service.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.time.LocalDateTime

/**
 *
 * @author Carlos Montoya
 * @since 1.0
 */
@Component
class InitialDataLoader
    @Autowired constructor(
        val usuarioService: UsuarioService,
        val usuarioRepository: UsuarioRepository,
        val materiaRepository: MateriaRepository,
        val documentoRepository: DocumentoRepository
    )
    : ApplicationListener<ApplicationReadyEvent>
{
    @Transactional
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        usuarioRepository.save(
            usuarioService.preregistrar(Profesor().apply {
                nombre = "Carlos"
                apellidoPaterno = "Montoya"
                apellidoMaterno = "Rodriguez"
                matricula = "201325916"
                correo = "carlos.montoya@profesor.buap.mx"
                password = "admin"
                creado = LocalDateTime.now()
                telefono = "2125295121"
                isActivo = true
                isResponsable = true
            }))
        val colaborador = usuarioRepository.save(
            usuarioService.preregistrar(Colaborador().apply {
                nombre = "Juan"
                apellidoPaterno = "Lopez"
                apellidoMaterno = "Gomez"
                matricula = "201456145"
                correo = "juan.lopez@alumno.buap.mx"
                password = "user"
                creado = LocalDateTime.now()
                telefono = "3324697114"
                isActivo = true
                isResponsable = true
                carrera = "ICC"
                inicioServicio = LocalDateTime.now().minusMonths(4)
                conclusionServicio = LocalDateTime.now()
                tiempoPrestado = TiempoPrestado().apply {
                    incrementar(Duration.ofHours(122).plusMinutes(43))
                }
            }))

        documentoRepository.save(Documento().apply {
            nombre = "Carta de Aceptacion"
            fechaCreacion = LocalDateTime.now()
            this.colaborador = colaborador as Colaborador?
        })

        materiaRepository.save(Materia().apply {
            clave = "ICC002"
            nombre = "Bases de Datos"
        })
    }
}
