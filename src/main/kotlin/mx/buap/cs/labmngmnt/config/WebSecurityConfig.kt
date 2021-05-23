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

import mx.buap.cs.labmngmnt.security.JwtAuthenticationEntryPoint
import mx.buap.cs.labmngmnt.security.JwtRequestFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension

/**
 *
 * @author Carlos Montoya
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig
    @Autowired constructor(
        val authEntryPoint: JwtAuthenticationEntryPoint,
        val userDetailsService: UserDetailsService,
        val jwtRequestFilter: JwtRequestFilter
    )
    : WebSecurityConfigurerAdapter()
{
    /**
     * Configura AuthenticationManager con el componente de detalles de
     * usuario y el codificador de contraseñas BCrypt
     *
     * @param auth
     */
    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager =
        super.authenticationManagerBean()

    @Bean
    fun securityEvaluationContextExtension(): SecurityEvaluationContextExtension? =
        SecurityEvaluationContextExtension()

    override fun configure(httpSecurity: HttpSecurity?) {
        httpSecurity!!
            .csrf().disable()
            .antMatcher("/api/**")
            .authorizeRequests()
            .anyRequest().authenticated() // todas las solicitudes a /api/ necesitan ser autenticadas
            .and()
            // Add a filter to validate the tokens with every request
            .addFilterBefore(
                jwtRequestFilter,
                UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling()
            .authenticationEntryPoint(authEntryPoint)
            .and()
            .sessionManagement() // stateless session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors()
    }
}
