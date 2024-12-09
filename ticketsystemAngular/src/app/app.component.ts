import { Component } from '@angular/core';
import {ConfigurationFormComponent} from './configuration-form/configuration-form.component';
import {ControlPanelComponent} from './control-panel/control-panel.component';
import {ActivityDisplayComponent} from './activity-display/activity-display.component';
import {SummaryDisplayComponent} from './summary-display/summary-display.component';
import {RouterLink, RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [
    ConfigurationFormComponent,
    ControlPanelComponent,
    ActivityDisplayComponent,
    SummaryDisplayComponent,
    RouterOutlet,
    RouterLink
  ],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ticketsystemAngular';
}

