import { INavData } from '@coreui/angular';

export const navItems: INavData[] = [
  /*{
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer',
    badge: {
      variant: 'info',
      text: 'NEW'
    }
  },*/
  {
    title: true,
    name: 'Menú'
  },
  {
    name: 'Perfil',
    url: '/perfil',
    icon: 'icon-user'
  },
  {
    name: 'Bitácora',
    url: '/bitacora',
    icon: 'icon-notebook'
  },
  {
    name: 'Solicitudes',
    url: '/charts',
    icon: 'icon-pencil'
  },
  {
    name: 'Equipos',
    url: '/equipos',
    icon: 'icon-screen-desktop'
  },
  {
    name: 'Usuarios',
    url: '/usuarios',
    icon: 'icon-people'
  },
  {
    name: 'Foro',
    url: '/icons/simple-line-icons',
    icon: 'icon-speech'
  },
  /*{
    name: 'Base',
    url: '/base',
    icon: 'icon-puzzle',
    children: [
      {
        name: 'Cards',
        url: '/base/cards',
        icon: 'icon-puzzle'
      }
    ]
  }*/
];
