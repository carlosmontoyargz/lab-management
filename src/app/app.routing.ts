import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

// Import Containers
import {DefaultLayoutComponent} from './containers';

import {P404Component} from './views/error/404.component';
import {P500Component} from './views/error/500.component';
import {LoginComponent} from './views/login/login.component';
import {RegisterComponent} from './views/register/register.component';
import {AuthGuard} from './helpers/auth.guard';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: '',
    component: DefaultLayoutComponent,
    canActivate: [AuthGuard],
    data: {
      title: ''
    },
    children: [
      {
        path: 'dashboard',
        loadChildren: () => import('./views/dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'perfil',
        loadChildren: () =>
          import('./views/perfil/perfil.module')
            .then(m => m.PerfilModule)
      },
      {
        path: 'equipos',
        loadChildren: () =>
          import('./views/equipos/equipos.module')
            .then(m => m.EquiposModule)
      },
      {
        path: 'bitacora',
        loadChildren: () =>
          import('./views/bitacora/bitacora.module')
            .then(m => m.BitacoraModule)
      },
      {
        path: 'solicitudes',
        loadChildren: () =>
          import('./views/solicitudes/solicitudes.module')
            .then(m => m.SolicitudesModule)
      },
      {
        path: 'usuarios',
        loadChildren: () =>
          import('./views/usuarios/usuarios.module')
            .then(m => m.UsuariosModule)
      },
      {
        path: 'foro',
        loadChildren: () =>
          import('./views/foro/foro.module')
            .then(m => m.ForoModule)
      },
      {
        path: 'base',
        loadChildren: () => import('./views/base/base.module').then(m => m.BaseModule)
      },
      {
        path: 'buttons',
        loadChildren: () => import('./views/buttons/buttons.module').then(m => m.ButtonsModule)
      },
      {
        path: 'charts',
        loadChildren: () => import('./views/chartjs/chartjs.module').then(m => m.ChartJSModule)
      },
      {
        path: 'icons',
        loadChildren: () => import('./views/icons/icons.module').then(m => m.IconsModule)
      },
      {
        path: 'notifications',
        loadChildren: () => import('./views/notifications/notifications.module').then(m => m.NotificationsModule)
      },
      {
        path: 'theme',
        loadChildren: () => import('./views/theme/theme.module').then(m => m.ThemeModule)
      },
      {
        path: 'widgets',
        loadChildren: () => import('./views/widgets/widgets.module').then(m => m.WidgetsModule)
      }
    ]
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Inicio de sesión'
    }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Registro'
    }
  },
  {
    path: '404',
    component: P404Component,
    data: {
      title: '404'
    }
  },
  {
    path: '500',
    component: P500Component,
    data: {
      title: '500'
    }
  },
  {path: '**', component: P404Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {relativeLinkResolution: 'legacy'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
