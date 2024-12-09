import { Component } from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-ticket-activity',
  template: `
    <h2>Ticket Activity</h2>
    <ul>
      <li *ngFor="let activity of activities">{{ activity }}</li>
    </ul>
  `,
  standalone: true,
  imports: [
    NgForOf
  ]
})
export class TicketActivityComponent {
  activities: string[] = ['Vendor 1 released a ticket', 'Customer 1 retrieved a ticket'];
}
