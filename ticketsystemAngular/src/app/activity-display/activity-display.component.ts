import { Component, OnInit } from '@angular/core';
import { TicketingSystemService } from '../ticketing-system.service';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-activity-display',
  standalone: true,
  templateUrl: './activity-display.component.html',
  imports: [
    NgForOf
  ],
  styleUrls: ['./activity-display.component.css']
})
export class ActivityDisplayComponent implements OnInit {
  activities: string[] = [];

  constructor(private ticketService: TicketingSystemService) {}

  ngOnInit(): void {
    this.ticketService.getActivities().subscribe({
      next: (activity: string) => this.activities.push(activity),
      error: () => alert('Failed to fetch activities.')
    });
  }
}

