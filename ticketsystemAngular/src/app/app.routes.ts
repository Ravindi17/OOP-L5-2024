import { Routes } from '@angular/router';
import { LogDisplayComponent } from './log-display/log-display.component';
import {ControlPanelComponent} from './control-panel/control-panel.component';

import { ConfigurationComponent } from './components/configuration/configuration.component';
import { TicketActivityComponent } from './components/ticket-activity/ticket-activity.component';
import { SummaryComponent } from './components/summary/summary.component';

export const appRoutes: Routes = [
  { path: 'log-display', component: LogDisplayComponent },
  { path: '', redirectTo: '/configuration', pathMatch: 'full' },
  { path: 'configuration', component: ConfigurationComponent },
  { path: 'activity', component: TicketActivityComponent },
  { path: 'summary', component: SummaryComponent },
  { path: '**', redirectTo: '/configuration' },
  {path: 'controlpanel', component: ControlPanelComponent },
];





