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
import {BehaviorSubject, Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {User} from '../model/user';
import {environment} from '../../environments/environment';
import {UserService} from '../service/user.service';

/**
 * @author Carlos Montoya
 * @since 1.0
 */
// @ts-ignore
@Injectable({providedIn: 'root'})
export class AuthenticationService {
    public currentUser: Observable<User>;
    private currentUserSubject: BehaviorSubject<User>;

    constructor(private http: HttpClient,
                private userService: UserService) {
        this.currentUserSubject = new BehaviorSubject<User>(
                JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    public get currentUserLocation(): string {
        return '';
        // FIXME usar el valor de la respuesta rest
        // return `/${this.userService.usersEndpoint}/${this.currentUserValue.id}`;
    }

    /**
     * Autentica al usuario en la API.
     *
     * @param username
     * @param password
     */
    public getToken(username: string, password: string) {
        return this.http
                .post<any>(
                        `${environment.baseUrl}/authenticate`,
                        {
                            username: username,
                            password: password
                        })
                .pipe(map(res => res.token));
    }

    // fixme cambiar a cargarDatosUsuario()
    login(username, token) {
        const user = new User();
        user.token = token;

        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSubject.next(user);

        return user;

        // FIXME mover a servicio de usuarios
        // return this.http
        //         .get<User>(
        //                 `${environment.apiUrl}/usuarios/search/findByUsername`,
        //                 {
        //                     params: {
        //                         "username": username,
        //                         //"projection": "datos"
        //                     },
        //                     headers: {
        //                         Authorization: `Bearer ${token}`
        //                     }
        //                 })
        //         .pipe(map(user => {
        //             user.token = token;
        //             // store user details and jwt token in local storage to keep user logged in between page refreshes
        //             localStorage.setItem('currentUser', JSON.stringify(user));
        //             this.currentUserSubject.next(user);
        //             return user;
        //         }));
    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}
