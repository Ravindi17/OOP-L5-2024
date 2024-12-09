import { Component, OnInit } from '@angular/core';
import { TicketingSystemService } from '../ticketing-system.service';

@Component({
  selector: 'app-summary-display',
  standalone: true,
  templateUrl: './summary-display.component.html',
  styleUrls: ['./summary-display.component.css']
})
export class SummaryDisplayComponent implements OnInit {
  summary: any;

  constructor(private ticketService: TicketingSystemService) {}

  ngOnInit(): void {
    this.ticketService.getSummary().subscribe({
      next: (data) => (this.summary = data),
      error: () => alert('Failed to fetch summary.')
    });
  }
}

