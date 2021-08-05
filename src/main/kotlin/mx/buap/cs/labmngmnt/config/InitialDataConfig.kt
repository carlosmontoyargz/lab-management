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

import mx.buap.cs.labmngmnt.api.*
import mx.buap.cs.labmngmnt.model.*
import mx.buap.cs.labmngmnt.repository.UsuarioRepository
import mx.buap.cs.labmngmnt.service.DocumentoService
import mx.buap.cs.labmngmnt.service.UsuarioService
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Configuration
class InitialDataConfig
    @Autowired constructor(
        val usuarioService: UsuarioService,
        val usuarioRepository: UsuarioRepository,
        val documentoService: DocumentoService,
        val laboratorioRepository: LaboratorioRestRepository,
        val equipoRepository: EquipoRestRepository,
        val materiaRepository: MateriaRestRepository,
        val bitacoraRestRepository: BitacoraRestRepository,
        val incidenteRepository: IncidenteRestRepository,
        val solicitudesRepository: SolicitudesRestRepository,
        val mensajeRepository: MensajeRepository)
{
    private val log = LogManager.getLogger(InitialDataConfig::class.java)

    @Bean
    fun cargaInicialDatos() = CommandLineRunner {
        log.info("Comienza carga inicial de datos")
        val laboratorio = laboratorio()
        val colaborador = colaborador()
        val profesor = profesor()
        guardarMaterias()
        guardarDocumentos(colaborador)
        guardarEquipos(laboratorio)
        guardarEntradas(colaborador)
        guardarSolicitudes()
        guardarMensajes(profesor)
        log.info("Finaliza carga inicial de datos")
    }

    private fun laboratorio() =
        laboratorioRepository.save(Laboratorio().apply {
            nombre = "Laboratorio de Bases de Datos de la FCC"
            edificio = "CC02"
            salon = "003"
        })

    private fun guardarMaterias() {
        materiaRepository.save(Materia().apply {
            clave = "ICC002"; nombre = "Bases de Datos"
        })
        materiaRepository.save(Materia().apply {
            clave = "ICC003"; nombre = "Mineria de Datos"
        })
        materiaRepository.save(Materia().apply {
            clave = "ICC004"; nombre = "Procesamiento de la Informacion"
        })
    }

    private fun profesor(): Usuario = usuarioRepository
        .save(usuarioService.preregistrar(
            Profesor().apply {
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

    private fun guardarDocumentos(colaborador: Usuario) {
        val bytes = Files.readAllBytes(Paths.get("api/files/documento.docx"))
        for (i in 1..10) {
            documentoService.guardar(
                Documento().apply {
                    nombre = "documento" //path.fileName.toString()
                    fechaCreacion = LocalDateTime.now()
                    this.colaborador = colaborador as Colaborador?
                },
                bytes)
        }
    }

    private fun guardarEquipos(laboratorio: Laboratorio) {
        val marcas = arrayOf("Lenovo", "Samsung", "Apple")
        for (i in 1..50) {
            equipoRepository.save(Equipo().apply {
                serial = "${i * 100 + 12345}CC-VRR"
                marca = marcas[i % 3]
                descripcion = "Equipo $i"
                numeroInventario = i
                this.laboratorio = laboratorio
            })
        }
    }

    private fun guardarEntradas(colaborador: Usuario) {
        val materias = arrayOf(
            materiaRepository.findByClave("ICC002").orElse(null),
            materiaRepository.findByClave("ICC003").orElse(null),
            materiaRepository.findByClave("ICC004").orElse(null))

        for (i in 0..60) {
            val entradaBitacora = bitacoraRestRepository.save(
                EntradaBitacora().apply {
                    fecha = LocalDate.now().minusDays(i.toLong())
                    horaEntrada = LocalTime.of(i % 8 + 7, 0)
                    horaSalida = horaEntrada.plusHours(2)
                    materia = materias[i % 3]
                    this.colaborador = colaborador as Colaborador?
                })

            val numInicidentes = i % 4
            for (j in 0..numInicidentes) {
                incidenteRepository.save(
                    Incidente().apply {
                        id = IncidenteId().apply {
                            entradaId = entradaBitacora.id
                            numeroIncidente = j
                        }
                        descripcion = "Incidente de prueba #$j"
                    })
            }
        }
    }

    private fun guardarSolicitudes() {
        for (i in 1..60) {
            solicitudesRepository.save(Solicitud().apply {
                folio = "00000$i"
                descripcion = "Solicitud de prueba #$i"
                costo = BigDecimal.valueOf(i.toLong()) * BigDecimal.valueOf(10.01)
                estado = EstadoSolicitud.values()[i % 5]
                tipo = TipoSolicitud.values()[i % 5]
            })
        }
    }

    private fun guardarMensajes(usuario: Usuario) {
        for (i in 1..60) {
            mensajeRepository.save(Mensaje().apply {
                titulo = "Mensaje de prueba #$i"
                contenido = "Este es un mensaje de prueba."
                enviado = LocalDateTime.now().minusDays(i.toLong())
                enviadoPor = usuario
            })
        }
    }
}
