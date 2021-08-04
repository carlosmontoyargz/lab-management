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
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, mergeMap} from 'rxjs/operators';
import {environment} from '../../environments/environment';
import { Observable } from 'rxjs';
import {UrlHelper} from './url.helper';

@Injectable({
  providedIn: 'root'
})
export class LaboratorioService {
  constructor(private http: HttpClient) {}

  urlLaboratorios(): Observable<string> {
    return this.http.get(environment.apiUrl)
      .pipe(
        map<any, string>(response =>
          UrlHelper.trim(
            response._links.laboratorios.href)));
  }

  getLaboratorio(): Observable<any> {
    return this.urlLaboratorios()
      .pipe(
        mergeMap((url: string) => this.http.get(url)),
        map<any, any>(labs => labs._embedded.laboratorios[0]));
  }
}
