import {Component, OnInit} from '@angular/core';
import {navItems} from '../../_nav';
import {LaboratorioService} from '../../service/laboratorio.service';

@Component({
    selector: 'app-dashboard',
    templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent implements OnInit {
    public sidebarMinimized = false;
    public navItems = navItems;

    laboratorio = {};

    constructor(private laboratorioService: LaboratorioService) {}

    toggleMinimize(e) {
        this.sidebarMinimized = e;
    }

    ngOnInit(): void {
        this.laboratorioService
            .getLaboratorio()
            .subscribe(lab => {
                console.log('Se ha descargado informacion de laboratorio');
                console.log(lab);
                this.laboratorio = lab;
            });
    }
}
