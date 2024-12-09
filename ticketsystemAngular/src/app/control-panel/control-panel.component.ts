import { Component } from '@angular/core';
import { TicketingSystemService } from '../ticketing-system.service';

@Component({
  selector: 'app-control-panel',
  standalone: true,
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.css']
})
export class ControlPanelComponent {
  constructor(private ticketService: TicketingSystemService) {}

  startSystem(): void {
    this.ticketService.startSystem().subscribe({
      next: () => alert('Simulation started.'),
      error: () => alert('Failed to start simulation.')
    });
  }

  stopSystem(): void {
    this.ticketService.stopSystem().subscribe({
      next: () => alert('Simulation stopped.'),
      error: () => alert('Failed to stop simulation.')
    });
  }
}
