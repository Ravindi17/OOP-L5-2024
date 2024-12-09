import { Component } from '@angular/core';
import { TicketingSystemService } from '../ticketing-system.service';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-configuration-form',
  standalone: true,
  templateUrl: './configuration-form.component.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./configuration-form.component.css']
})
export class ConfigurationFormComponent {
  config = {
    totalTickets: 0,
    maxCapacity: 0,
    numVendors: 0,
    numCustomers: 0
  };

  constructor(private ticketService: TicketingSystemService) {}

  onSubmit(): void {
    this.ticketService.saveConfiguration(this.config).subscribe({
      next: () => alert('Configuration saved successfully.'),
      error: () => alert('Failed to save configuration.')
    });
  }
}
