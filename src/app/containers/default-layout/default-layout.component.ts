import {Component, OnInit} from '@angular/core';
import {navItems} from '../../_nav';
import {LaboratorioService} from '../../service/laboratorio.service';
import {UserService} from '../../service/user.service';
import {first} from 'rxjs/operators';
import {Usuario} from '../../model/usuario';
import {AuthenticationService} from '../../service/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent implements OnInit {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private laboratorioService: LaboratorioService,
    private usuarioService: UserService
  ) {
    this.authenticationService.currentUser
      .subscribe(r => this.usuarioActual = r);
  }

  public sidebarMinimized = false;
  public navItems = navItems;

  usuarioActual: Usuario;
  laboratorio = {};

  toggleMinimize(e) {
    this.sidebarMinimized = e;
  }

  ngOnInit(): void {
    this.laboratorioService.getLaboratorio().pipe(first())
      .subscribe(lab => {
        console.log('Se ha descargado informacion de laboratorio');
        console.log(lab);
        this.laboratorio = lab;
      });

    this.usuarioService.getAllColaboradores().pipe(first())
      .subscribe(colaboradores => {
        console.log('Se ha descargado informacion de colaboradores');
        console.log(colaboradores);
      });
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']).then(r => r);
  }
}
