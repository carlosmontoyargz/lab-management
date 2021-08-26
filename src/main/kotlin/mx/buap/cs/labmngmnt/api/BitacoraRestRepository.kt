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

import mx.buap.cs.labmngmnt.model.EntradaBitacora
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "entradas", collectionResourceRel = "entradas")
interface BitacoraRestRepository : PagingAndSortingRepository<EntradaBitacora, Int>
{
    @Query("""
        select e from EntradaBitacora e
        where
            (e.materia is not null
                and lower(coalesce(e.materia.nombre, ''))
                    like lower(concat('%', :q, '%'))
            )
            or (e.profesor is not null
                and lower(concat(
                    coalesce(e.profesor.nombre, ''),
                    coalesce(e.profesor.apellidoPaterno, ''),
                    coalesce(e.profesor.apellidoMaterno, '')
                )) like lower(concat('%', :q, '%'))
            )
            or lower(concat(
                coalesce(e.colaborador.nombre, ''),
                coalesce(e.colaborador.apellidoPaterno, ''),
                coalesce(e.colaborador.apellidoMaterno, '')
            )) like lower(concat('%', :q, '%'))
    """)
    fun searchBy(@Param("q") q: String, pageable: Pageable): List<EntradaBitacora>
}
