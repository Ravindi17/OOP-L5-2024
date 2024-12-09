import { Component } from '@angular/core';
import { TicketingSystemService } from '../ticketing-system.service';

@Component({
  selector: 'app-log-display',
  templateUrl: './log-display.component.html',
  styleUrls: ['./log-display.component.css'],
  standalone: true,
})
export class LogDisplayComponent {
  logs: string[] = [];

  constructor(private ticketService: TicketingSystemService) {}

  ngOnInit() {
    this.ticketService.getLogs().subscribe((data) => {
      this.logs = data;
    });
  }
}


