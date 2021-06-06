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

package mx.buap.cs.labmngmnt.config

import mx.buap.cs.labmngmnt.api.EquipoRestRepository
import mx.buap.cs.labmngmnt.api.LaboratorioRestRepository
import mx.buap.cs.labmngmnt.api.MateriaRestRepository
import mx.buap.cs.labmngmnt.model.*
import mx.buap.cs.labmngmnt.repository.UsuarioRepository
import mx.buap.cs.labmngmnt.service.DocumentoService
import mx.buap.cs.labmngmnt.service.UsuarioService
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

@Configuration
class InitialDataConfig
    @Autowired constructor(
        val usuarioService: UsuarioService,
        val usuarioRepository: UsuarioRepository,
        val documentoService: DocumentoService,
        val laboratorioRepository: LaboratorioRestRepository,
        val equipoRepository: EquipoRestRepository,
        val materiaRepository: MateriaRestRepository)
{
    private val log = LogManager.getLogger(InitialDataConfig::class.java)

    @Bean
    fun cargaInicialDatos() = CommandLineRunner {
        log.info("Comenzando carga inicial de datos")

        val colaborador = colaborador()
        val profesor = profesor()
        val documento = documento(colaborador)
        val laboratorio = laboratorio()

        equipoRepository.save(Equipo().apply {
            serial = "23CC-VRR"
            marca = "Lenovo"
            descripcion = "Equipo 10"
            numeroInventario = 10
            this.laboratorio = laboratorio
        })

        materiaRepository.save(Materia().apply {
            clave = "ICC002"
            nombre = "Bases de Datos"
        })
    }

    private fun profesor(): Usuario = usuarioRepository.save(
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
        })
    )

    private fun colaborador(): Usuario = usuarioRepository.save(
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
            inicioServicio = LocalDate.now().minusMonths(4)
            conclusionServicio = LocalDate.now()
            tiempoPrestado = TiempoPrestado().apply {
                incrementar(Duration.ofHours(0).plusMinutes(0))
            }
        })
    )

    private fun documento(colaborador: Usuario): Documento {
        val path = Paths.get("api/files/documento.docx")
        return documentoService.guardar(
            Documento().apply {
                nombre = path.fileName.toString()
                fechaCreacion = LocalDateTime.now()
                this.colaborador = colaborador as Colaborador?
            },
            Files.readAllBytes(path))
    }

    private fun laboratorio() =
        laboratorioRepository.save(Laboratorio().apply {
            nombre = "Laboratorio de Bases de Datos de la FCC"
            edificio = "CC02"
            salon = "003"
        })
}
