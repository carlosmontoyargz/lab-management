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

import mx.buap.cs.labmngmnt.model.Documento
import mx.buap.cs.labmngmnt.model.DocumentoLob
import mx.buap.cs.labmngmnt.repository.DocumentoLobRepository
import mx.buap.cs.labmngmnt.rest.DocumentoRestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DocumentoServiceImpl
    @Autowired constructor(
        val documentoRepository: DocumentoRestRepository,
        val documentoLobRepository: DocumentoLobRepository)
    : DocumentoService
{
    @Transactional
    override fun guardar(documento: Documento, bytes: ByteArray): Documento {
        val documentoDb = documentoRepository.save(documento)
        documentoLobRepository.save(
            DocumentoLob().apply {
                this.documento = documentoDb
                this.contenido = bytes
            })
        return documentoDb
    }
}
