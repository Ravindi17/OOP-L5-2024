import { Component } from '@angular/core';

@Component({
  selector: 'app-summary',
  template: `
    <h2>Ticket Summary</h2>
    <p>Total Tickets Released: {{ totalTicketsReleased }}</p>
    <p>Total Tickets Sold: {{ totalTicketsSold }}</p>
    <p>Total Tickets Remaining: {{ totalTicketsRemaining }}</p>
  `,
  standalone: true,
})
export class SummaryComponent {
  totalTicketsReleased = 50;
  totalTicketsSold = 45;
  totalTicketsRemaining = 5;
}
