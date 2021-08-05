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
package mx.buap.cs.labmngmnt.model.views;

import mx.buap.cs.labmngmnt.model.EntradaBitacora;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Carlos Montoya
 * @since 1.0
 */
@Projection(name = "entradaBitacoraView", types = EntradaBitacora.class)
public interface EntradaBitacoraView
{
    Integer getId();
    LocalDate getFecha();
    LocalTime getHoraEntrada();
    LocalTime getHoraSalida();

    @Value("#{target.materia != null? target.materia.nombre: ''}")
    String getMateria();

    @Value("#{target.profesor != null? target.profesor.getNombreCompleto(): ''}")
    String getProfesor();

    @Value("#{target.colaborador != null? target.colaborador.getNombreCompleto(): ''}")
    String getColaborador();

    @Value("#{target.incidentes != null? target.incidentes.size(): 0}")
    Integer getNumIncidentes();
}
