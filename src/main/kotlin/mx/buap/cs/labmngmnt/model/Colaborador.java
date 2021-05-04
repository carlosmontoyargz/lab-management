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

package mx.buap.cs.labmngmnt.model;

import com.vladmihalcea.hibernate.type.interval.PostgreSQLIntervalType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Carlos Montoya
 * @since 1.0
 */
@Entity
@TypeDef(
        typeClass = PostgreSQLIntervalType.class,
        defaultForType = Duration.class
)
public class Colaborador extends Usuario
{
    @Column(length = 60)
    private String carrera;

    private LocalDateTime inicioServicio;
    private LocalDateTime conclusionServicio;

    @Column(columnDefinition = "interval")
    private Duration tiempoPrestado;

    @Column(nullable = false)
    private boolean responsable;

    @OneToMany(
            mappedBy = "colaborador",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Documento> documentos = new LinkedList<>();

    public void agregarDocumento(Documento documento) {
        documentos.add(documento);
        documento.setColaborador(this);
    }

    public void removerDocumento(Documento documento) {
        documentos.remove(documento);
        documento.setColaborador(null);
    }

    public Duration incrementarTiempoPrestado(Duration tiempo) {
        tiempoPrestado = tiempoPrestado.plus(tiempo);
        return tiempoPrestado;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public LocalDateTime getInicioServicio() {
        return inicioServicio;
    }

    public void setInicioServicio(LocalDateTime inicioServicio) {
        this.inicioServicio = inicioServicio;
    }

    public LocalDateTime getConclusionServicio() {
        return conclusionServicio;
    }

    public void setConclusionServicio(LocalDateTime conclusionServicio) {
        this.conclusionServicio = conclusionServicio;
    }

    public Duration getTiempoPrestado() {
        return tiempoPrestado;
    }

    public void setTiempoPrestado(Duration tiempoPrestado) {
        this.tiempoPrestado = tiempoPrestado;
    }

    public boolean isResponsable() {
        return responsable;
    }

    public void setResponsable(boolean responsable) {
        this.responsable = responsable;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }
}
